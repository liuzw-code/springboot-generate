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
 * TABLE_NAME:(t_code_params)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_code_params")
public class ParamsModel {


    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 别名
     */
    @Column(name = "name")
    private String name;


    /**
     * 作者
     */
    @Column(name = "author")
    private String author;


    /**
     * 包名
     */
    @Column(name = "package_name")
    private String packageName;


    /**
     * 本地路径
     */
    @Column(name = "local_path")
    private String localPath;


    /**
     * 版权信息
     */
    @Column(name = "copyright")
    private String copyright;


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