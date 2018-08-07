package com.liuzw.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TABLE_NAME:(t_code_database_info)
 * 数据库基础信息
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseInfoBean {


    /**
     *
     */
    private Long id;


    /**
     * 名称
     */
    private String name;


    /**
     * 数据库驱动
     */
    private String dbDriver;


    /**
     * 数据库地址
     */
    private String dbUrl;


    /**
     * 数据库账户
     */
    private String dbUsername;


    /**
     * 连接密码
     */
    private String dbPassword;


    /**
     * 数据库类型
     */
    private Integer dbType;


    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 修改时间
     */
    private String updateTime;


}