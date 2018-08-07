package com.liuzw.generate.model;

import com.liuzw.generate.handler.TimeTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.*;

/**
 * TABLE_NAME:(t_code_database_info)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_code_database_info")
public class DatabaseInfoModel {


    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 名称
     */
    @Column(name = "name")
    private String name;


    /**
     * 数据库驱动
     */
    @Column(name = "db_driver")
    private String dbDriver;


    /**
     * 数据库地址
     */
    @Column(name = "db_url")
    private String dbUrl;


    /**
     * 数据库账户
     */
    @Column(name = "db_username")
    private String dbUsername;


    /**
     * 连接密码
     */
    @Column(name = "db_password")
    private String dbPassword;


    /**
     * 数据库类型
     */
    @Column(name = "db_type")
    private Integer dbType;


    /**
     * 创建时间
     */
    @ColumnType(column = "create_time",
            jdbcType = JdbcType.VARCHAR,
            typeHandler = TimeTypeHandler.class)
    private String createTime;


    /**
     * 修改时间
     */
    @ColumnType(column = "update_time",
            jdbcType = JdbcType.VARCHAR,
            typeHandler = TimeTypeHandler.class)
    private String updateTime;


}