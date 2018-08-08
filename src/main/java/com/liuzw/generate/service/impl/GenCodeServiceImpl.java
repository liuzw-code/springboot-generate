package com.liuzw.generate.service.impl;

import com.liuzw.generate.bean.BasicDataBean;
import com.liuzw.generate.bean.ColumnBean;
import com.liuzw.generate.bean.GenCodeBean;
import com.liuzw.generate.bean.TemplateBean;
import com.liuzw.generate.config.DynamicDataSourceContextHolder;
import com.liuzw.generate.enums.TemplateEnum;
import com.liuzw.generate.mapper.TemplateMapper;
import com.liuzw.generate.parse.ParseFactory;
import com.liuzw.generate.service.ColumnService;
import com.liuzw.generate.service.GenCodeService;
import com.liuzw.generate.utils.JavaTypeUtils;
import com.liuzw.generate.utils.StringUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成
 *
 * @author liuzw
 * @date 2018/8/6 20:29
 **/
@Slf4j
@Service
public class GenCodeServiceImpl implements GenCodeService {

    @Autowired
    private ColumnService columnService;

    @Autowired
    private TemplateMapper templateMapper;


    @Override
    public Boolean genCode(HttpServletResponse response, GenCodeBean bean) {

        List<String> tableNames = bean.getTableNames();
        //设置基本参数
        BasicDataBean basicData = BasicDataBean.get(bean.getParams());

        DynamicDataSourceContextHolder.setDataSourceKey("default");
        //1.获取模板内容
        List<TemplateBean> templateList = templateMapper.getTemplateComment(bean.getTemplateIds());
        if (templateList == null || templateList.size() == 0) {
            return false;
        }

        DynamicDataSourceContextHolder.setDataSourceKey("dynamic");
        List<TemplateBean> list = new ArrayList<>();
        //2.获取要生成代码的表的信息
        for (String tableName : tableNames) {
            basicData.setTableName(tableName);
            basicData.setClassName(StringUtility.getCamelCaseString(tableName, true, true));
            basicData.setClassVarName(StringUtility.getCamelCaseString(tableName, false, true));
            //获取表的字段信息
            getTableInfo(basicData, tableName);
            //获取解析后的模板内容
            list.addAll(getTemplateComment(basicData, templateList, TemplateEnum.FREEMARKER));
        }

        //代码生成到本地路径
        String localPath = bean.getParams().getLocalPath();

        if (StringUtils.isNotEmpty(localPath) && response == null) {
            writeThisFileList(localPath, list, basicData);
        } else {
            downThisFileList(response, list, basicData);
        }

        return true;
    }


    /**
     * 获取表的字段信息
     */
    private void getTableInfo(BasicDataBean basicData, String tableNames) {
        log.info("------------->获取表的字段信息");
        //表中所有字段的全部信息
        List<ColumnBean> columns = columnService.getTableAllColumns(tableNames);
        getList(columns);
        basicData.setColumns(columns);
        //获取表的主键
        List<ColumnBean> pkColumns = columnService.getTablePkColumns(tableNames);
        getList(pkColumns);
        basicData.setPkColumns(pkColumns);
    }

    /**
     * 获取模板解析后内容
     *
     * @param basicData    封装参数
     * @param templateList 模板内容
     * @return 模板内容
     */
    private List<TemplateBean> getTemplateComment(BasicDataBean basicData,
                                                  List<TemplateBean> templateList,
                                                  TemplateEnum templateEnum) {
        return ParseFactory.getParse(templateEnum).parse(basicData, templateList);
    }


    private void getList(List<ColumnBean> list) {
        for (ColumnBean column : list) {
            column.setColumnName(column.getColumnName().toLowerCase());
            column.setJdbcType(column.getColumnType());
            column.setJavaType(JavaTypeUtils.readValue(column.getColumnType()));
            column.setJavaFieldName(StringUtility.getCamelCaseString(column.getColumnName(), false, false));
            column.setJavaFieldComment(column.getColumnComment());
        }
    }

    /**
     * 本地输出代码
     */
    private void writeThisFileList(String localPath, List<TemplateBean> templateList, BasicDataBean bean) {
        log.info("----------->开始创建文件");
        for (TemplateBean template : templateList) {
            // 文件路径包括 本地项目路径  + 包路径 + 类的自我路径
            String filePath = localPath + File.separator + getPackagePath(template, bean);

            File path = new File(filePath + File.separator);

            if (!path.exists()) {
                if (path.mkdirs()) {
                    log.info("---------->创建文件路径成功");
                }
            }

            //文件路径
            String pathName = filePath + File.separator + bean.getClassName() + template.getTemplateFileName();

            log.info("-------> 生成文件路径{}", pathName);
            try {
                File file = new File(pathName);
                FileUtils.writeStringToFile(file, template.getTemplateContent(), "UTF-8");
            } catch (IOException e) {
                log.info("----------->生成文件失败");
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 下载代码
     */
    private void downThisFileList(HttpServletResponse res, List<TemplateBean> templateList, BasicDataBean bean) {
        ZipOutputStream out = null;
        try {
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;filename=code.zip");
            out = new ZipOutputStream(res.getOutputStream());
            for (TemplateBean templateBean : templateList) {
                out.putNextEntry(new ZipEntry(getPackagePath(templateBean, bean) + File.separator + getFileName(templateBean, bean.getClassName())));
                out.write(templateBean.getTemplateContent().getBytes(), 0, templateBean.getTemplateContent().getBytes().length);
                out.closeEntry();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                res.getOutputStream().flush();
                res.getOutputStream().close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private String getFileName(TemplateBean templateBean, String fileName) {
        return fileName + templateBean.getTemplateFileName();
    }


    private String getPackagePath(TemplateBean templateModel, BasicDataBean bean) {
        return bean.getPackageName().replaceAll("\\.", "\\/")
                + (StringUtils.isNotEmpty(templateModel.getTemplatePath()) ? "/"
                + templateModel.getTemplatePath().replaceAll("\\.", "\\/") : "");
    }

}
