package com.liuzw.generate.service.impl;

import com.liuzw.generate.bean.*;
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
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
        //切换数据源，查询模板信息
        DynamicDataSourceContextHolder.setDataSourceKey("default");
        //1.获取模板内容
        List<TemplateBean> templateList = templateMapper.getTemplateComment(bean.getTemplateIds());
        if (templateList == null || templateList.isEmpty()) {
            return false;
        }
        //切换数据源，查询选择的表的信息
        DynamicDataSourceContextHolder.setDataSourceKey("dynamic");
        //2.获取要生成代码的表的信息
        log.info("------------->获取表的字段信息");
        //表中所有字段的全部信息
        List<ColumnBean> columns = columnService.getTableAllColumns(tableNames);
        //处理数据
        getList(columns);
        //获取表的主键
        List<ColumnBean> pkColumns = columnService.getTablePkColumns(tableNames);

        //获取表的备注信息
        List<TableBean> tableInfoList = columnService.getTableInfoByTableName(tableNames);

        Map<String, String> tableInfoMap = tableInfoList.stream().collect(Collectors.toMap(
                TableBean::getTableName, TableBean::getTableComment));

        //处理数据
        getList(pkColumns);

        List<TemplateBean> list = new ArrayList<>();
        //3.开始处理模板生成代码
        for (String tableName : tableNames) {
            basicData.setColumns(columns.stream().filter(columnBean -> columnBean.getTableName().equals(tableName)).collect(Collectors.toList()));
            basicData.setPkColumns(pkColumns.stream().filter(columnBean -> columnBean.getTableName().equals(tableName)).collect(Collectors.toList()));
            basicData.setTableName(tableName);
            basicData.setClassName(StringUtility.getCamelCaseString(tableName, true, true));
            basicData.setClassVarName(StringUtility.getCamelCaseString(tableName, false, true));
            basicData.setTableComment(tableInfoMap.get(tableName));
            //获取解析后的模板内容
            list.addAll(getTemplateComment(basicData, templateList, TemplateEnum.FREEMARKER));
        }

        //代码生成到本地路径
        String localPath = bean.getParams().getLocalPath();

        Boolean flag = StringUtils.isNotEmpty(localPath) && isWindows();

        if (flag) {
            writeThisFileList(localPath, list, basicData.getPackageName());
        } else {
            if (response != null) {
                downThisFileList(response, list, basicData.getPackageName());
            }
        }

        return true;
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
    private void writeThisFileList(String localPath, List<TemplateBean> templateList, String packageName) {
        log.info("----------->开始创建文件");
        for (TemplateBean template : templateList) {
            // 文件路径包括 本地项目路径  + 包路径 + 类的自我路径
            String filePath = localPath + File.separator + getPackagePath(template, packageName);

            File path = new File(filePath + File.separator);

            if (!path.exists() && path.mkdirs()) {
                log.info("---------->创建文件路径成功");
            }

            //文件路径
            String pathName = filePath + File.separator + template.getClassName() + template.getTemplateFileName();

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
    private void downThisFileList(HttpServletResponse res, List<TemplateBean> templateList, String packageName) {
        try (ZipOutputStream out = new ZipOutputStream(res.getOutputStream())) {
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;filename=code.zip");
            for (TemplateBean templateBean : templateList) {
                out.putNextEntry(new ZipEntry(getPackagePath(templateBean, packageName) + File.separator + getFileName(templateBean)));
                out.write(templateBean.getTemplateContent().getBytes(), 0, templateBean.getTemplateContent().getBytes().length);
                out.closeEntry();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private String getFileName(TemplateBean templateBean) {
        return templateBean.getClassName() + templateBean.getTemplateFileName();
    }


    private String getPackagePath(TemplateBean templateModel, String packageName) {
        return packageName.replaceAll("\\.", "\\/")
                + (StringUtils.isNotEmpty(templateModel.getTemplatePath()) ? "/"
                + templateModel.getTemplatePath().replaceAll("\\.", "\\/") : "");
    }


    /**
     * 判断当前系统是不是windows系统
     *
     * @return Boolean
     */
    private Boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS");
    }


}
