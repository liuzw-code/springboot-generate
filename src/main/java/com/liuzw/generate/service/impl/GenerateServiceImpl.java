package com.liuzw.generate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.liuzw.generate.bean.BasicData;
import com.liuzw.generate.bean.Column;
import com.liuzw.generate.bean.Query;
import com.liuzw.generate.bean.Table;
import com.liuzw.generate.mapper.ColumnMapper;
import com.liuzw.generate.service.IGenerateService;
import com.liuzw.generate.utils.JavaTypeUtils;
import com.liuzw.generate.utils.JdbcTypeUtils;
import com.liuzw.generate.utils.StringUtil;
import com.liuzw.generate.utils.ZipUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzw
 */

@Service
public class GenerateServiceImpl implements IGenerateService {

    private static Logger logger = LoggerFactory.getLogger(GenerateServiceImpl.class);

    private static BasicData basicData;

    private static Configuration cfg;

    /**
     * 文件输出路径
     */
    @Value("${filePath}")
    private String filePath;

    /**
     * 模板路径
     */
    @Value("${templatePath}")
    private String templatePath;

    /**
     * 包路径
     */
    @Value("${packagePath}")
    private String packagePath;

    /**
     * 模块名称
     */
    @Value("${module}")
    private String module;

    /**
     * 数据库名称
     */
    @Value("${dataBaseName}")
    private String dataBaseName;

    /**
     * 数据库类型
     */
    @Value("${dataBaseType}")
    private String dataBaseType;

    /**
     * 用于存放表的字段信息到内存中
     */
    private static Map<String, List<Column>> tempMap = new HashMap<>();


    @Autowired
    private ColumnMapper columnMapper;

    @Override
    public void generateController(String tableNames) {
        generate(tableNames,"controller.ftl","Controller.java","/controller");
    }

    @Override
    public void generateBean(String tableNames) {
        generate(tableNames,"bean.ftl",".java","/bean");
    }

    @Override
    public void generateModel(String tableNames) {
        generate(tableNames,"model.ftl",".java","/model");
    }

    @Override
    public void generateService(String tableNames) {
        generate(tableNames,"service.ftl","Service.java","/service");
    }
    @Override
    public void generateServiceImpl(String tableNames) {
        generate(tableNames,"serviceImpl.ftl","ServiceImpl.java","/service/impl");
    }
    @Override
    public void generateMapper(String tableNames) {
        generate(tableNames,"mapper.ftl","Mapper.java","/mapper");
    }

    @Override
    public void generateMapperXml(String tableNames) {
        generate(tableNames,"mapper_xml.ftl","Mapper.xml","/mapper");
        generate(tableNames,"mapper_xml2.ftl","Mapper2.xml","/mapper");
    }

    @Override
    public void generateVue(String tableNames) {
        generate(tableNames,"vue.ftl",".vue","/vue");
    }

    @Override
    public void generateJs(String tableNames) {
        generate(tableNames,"js.ftl",".txt","/js");
    }

    @Override
    public void generateMenuSql(String tableNames) {
        generate(tableNames,"menuSql.ftl",".txt","/sql");
    }

    @Override
    public void generateAll(String tableNames) {
       // generateController(tableNames);
        generateBean(tableNames);
        generateModel(tableNames);
        generateService(tableNames);
        generateServiceImpl(tableNames);
        generateMapper(tableNames);
        generateMapperXml(tableNames);
        //generateVue(tableNames);
        //generateMenuSql(tableNames);
        //generateJs(tableNames);
    }

    @Override
    public void generatorAll(HttpServletResponse response, String tableNames) {
        generateAll(tableNames);
        try {
            ZipUtils.toZip(filePath, response.getOutputStream(), true);
            ZipUtils.deleteDir(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<Table> queryList(Query query) {
        return columnMapper.queryList(query);
    }

    @Override
    public Integer queryTotal(Query query) {
        return columnMapper.queryTotal(query);
    }

    @Override
    public void generatorCode(String tableNames, String path) {
        filePath = path;
        generateAll(tableNames);
    }


    /**
     *
     * @param tableName     表名
     * @param templateName   模板名
     * @param suffix         输出的文件的后缀
     * @param filepath       文件的路径
     */
    private void generate(String tableName, String templateName, String suffix, String filepath){

        if (StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(templateName)
                && StringUtils.isNotEmpty(suffix) && StringUtils.isNotEmpty(filepath)) {
            try {
                String[] tableNames = tableName.split(",");

                if (basicData == null) {
                    basicData = BasicData.builder().packagePath(packagePath)
                            .module(module).dataBaseName(dataBaseName)
                            .dataBaseType(dataBaseType)
                            .build();
                }

                if (cfg == null) {
                    cfg = new Configuration();
                    cfg.setClassForTemplateLoading(GenerateServiceImpl.class, templatePath);
                }

                Template temp;

                for (String table : tableNames) {
                    basicData.setTableName(table.toLowerCase());
                    String name = StringUtil.getCamelCaseString(table.toLowerCase(), true, true);
                    basicData.setVarName(StringUtil.getCamelCaseString(table.toLowerCase(), false, true));
                    basicData.setClassName(name);
                    //输出的文件名
                    String outFileName = name + suffix;

                    if (StringUtils.isEmpty(filePath)) {
                        filePath = getPath();
                    }
                    //文件的输出路径
                    String outDir = filePath + "/" + packagePath.replaceAll("\\.", "/") + "/" + basicData.getModule() + filepath;
                    logger.info("========outdir=====" + outDir);
                    //获取表中字段和主键的信息
                    processBasicData();
                    temp = cfg.getTemplate(templateName);
                    //生成相应的文件
                    generate(temp, outFileName, outDir);
                }
            }catch (Exception e) {
                logger.info("异常信息：{}", e);
            }
        }
    }

    /**
     * 获取表的信息
     */
    private void processBasicData(){

        //普通字段 key
        String columnsKey = dataBaseName + "_" + basicData.getTableName() + "_columns";
        //主键 key
        String pkColumnsKey = dataBaseName + "_" + basicData.getTableName() +"_pkColumns";

        if (MapUtils.isEmpty(tempMap) || MapUtils.getObject(tempMap,columnsKey) == null
                || MapUtils.getObject(tempMap,pkColumnsKey) == null) {

            Map<String,String> params = new HashMap<>(10);
            //数据库名字
            params.put("dataBaseName", dataBaseName);
            //表名
            params.put("tableName", basicData.getTableName());
            //数据库类型
            params.put("dataBaseType", dataBaseType);
            //表中所有字段的全部信息
            List<Column> columns = columnMapper.getTableAllColumns(params);
            getList(columns);
            //获取表的主键
            List<Column> pkColumns = columnMapper.getTablePkColumns(params);
            getList(pkColumns);
            tempMap.put(columnsKey,columns);

            tempMap.put(pkColumnsKey,pkColumns);
        }

        //从内存中获取数据
        basicData.setColumns(tempMap.get(columnsKey));

        basicData.setPkColumns(tempMap.get(pkColumnsKey));
        logger.info("=========basicData:" + JSONObject.toJSON(basicData));
    }


    /**
     * 通过模板生成文件
     */

    private void generate(Template temp,String outFileName, String outdir){
        try {
            Boolean flag = true;
            File f  =  new File(outdir);
            if (!f.exists()) {
                flag = f.mkdirs();
            }
            File fn  =  new File(f.getPath()  +  "\\"  +  outFileName);
            logger.info("======================:" + f.getPath()  +  "\\"  +  outFileName);
            if (!fn.exists()) {
                flag = fn.createNewFile();
            }
            System.out.println(flag);
            FileOutputStream fos  =  new FileOutputStream(fn);

            Writer out  =  new OutputStreamWriter(fos);

            Map<String,Object> root  =  new HashMap<>(10);
            root.put("data", basicData);
            temp.process(root, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     *  获取输出文件的路径
     */
    private static String getPath() {
        String path = System.getProperty("user.dir") + "/out/";
        File file  =  new File(path);
        if (file.exists()) {
            System.out.println(file.delete());
        }
        return file.getPath();
    }


    private void getList(List<Column> list){
        for (Column column : list) {
            column.setColumnName(column.getColumnName().toLowerCase());
            column.setJdbcType(JdbcTypeUtils.readValue(column.getDataType().toUpperCase()));
            column.setPropertyType(JavaTypeUtils.readValue(column.getJdbcType().toUpperCase()));
            column.setPropertyName(StringUtil.getCamelCaseString(column.getColumnName(),false,false));
            column.setPropertyCname(column.getColumnCname());
            column.setColumnCname(column.getColumnName());
        }
    }
}
