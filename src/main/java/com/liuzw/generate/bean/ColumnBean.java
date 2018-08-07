package com.liuzw.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字段的基本信息
 *
 * @author liuzw
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnBean {


    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段描述内容
     */
    private String columnComment;

    /**
     * java属性名称
     */
    private String javaFieldName;

    /**
     * java属性注释内容
     */
    private String javaFieldComment;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * java类型
     */
    private String javaType;

    /**
     * 数据库字段类型
     */
    private String jdbcType;

    /**
     * 是否必填
     */
    private String isNullable;

}