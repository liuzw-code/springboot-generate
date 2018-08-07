package com.liuzw.generate.bean;

import lombok.Data;

/**
 * 表信息
 * @author liuzw
 * @date 2018/4/20 13:04
 **/
@Data
public class TableBean {
    /**
     * 数据库引擎
     */
    private String engine;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表 注释
     */
    private String tableComment;

    /**
     * 创建时间
     */
    private String createTime;
}