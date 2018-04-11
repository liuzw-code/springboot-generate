package com.liuzw.generate.service;


/**
 *@author liuzw
 *
 *  生成各种java文件，如service mapper。
 */
public interface IGenerateService {


    /**
     * 生成实体类
     *
     * @param tableNames 表名
     */
    void generateEntity(String tableNames);

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
    void generateDao(String tableNames);

    /**
     * 生成Mapper文件
     *
     * @param tableNames 表名
     */
    void generateMapper(String tableNames);

    /**
     * 生成所有文件
     *
     * @param tableNames 表名
     */
    void generateAll(String tableNames);
}
