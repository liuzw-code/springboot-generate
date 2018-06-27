package com.liuzw.generate.service;


import com.liuzw.generate.bean.Page;
import com.liuzw.generate.bean.Query;
import com.liuzw.generate.bean.Table;

import javax.servlet.http.HttpServletResponse;

/**
 *@author liuzw
 *
 *  生成各种java文件，如service mapper。
 */
public interface IGenerateService {

    /**
     * 生成控制层
     *
     * @param tableNames 表名
     */
    void generateController(String tableNames);

    /**
     * 生成实体类
     *
     * @param tableNames 表名
     */
    void generateBean(String tableNames);

    /**
     * 生成实体类
     *
     * @param tableNames 表名
     */
    void generateModel(String tableNames);

    /**
     * 生成Servicr类
     *
     * @param tableNames 表名
     */
    void generateService(String tableNames);

    /**
     * 生成Servicr实现类
     *
     * @param tableNames 表名
     */
    void generateServiceImpl(String tableNames);

    /**
     * 生成dao
     *
     * @param tableNames 表名
     */
    void generateMapper(String tableNames);

    /**
     * 生成Mapper文件
     *
     * @param tableNames 表名
     */
    void generateMapperXml(String tableNames);

    /**
     * 生成 vue文件
     *
     * @param tableNames 表名
     */
    void generateVue(String tableNames);

    /**
     * 生成 js文件
     *
     * @param tableNames 表名
     */
    void generateJs(String tableNames);

    /**
     * 生成 menuSql文件
     *
     * @param tableNames 表名
     */
    void generateMenuSql(String tableNames);

    /**
     * 生成所有文件
     *
     * @param tableNames 表名
     */
    void generateAll(String tableNames);

    /**
     * 生成代码（web端使用）
     *
     * @param tableNames 表名
     * @param response   HttpServletResponse
     */
    void generatorAll(HttpServletResponse response, String tableNames);

    /**
     * 获取数据库中的表的信息
     *
     * @param query   Query
     * @return        List<Table>
     */
    Page<Table> queryList(Query query);

    /**
     * 生成代码（web端使用）
     *
     * @param tableNames 表名
     * @param path       文件生成路径
     */
    void generatorCode(String tableNames, String path);
}
