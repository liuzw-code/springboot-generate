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
 * TABLE_NAME:(t_code_template)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_code_template")
public class TemplateModel {


    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 模板名称
     */
    @Column(name = "template_name")
    private String templateName;

    /**
     * 模板类型
     */
    @Column(name = "template_type")
    private String templateType;

    /**
     * 模板引擎类型
     */
    @Column(name = "template_engine_type")
    private String templateEngineType;

    /**
     * 模板描述
     */
    @Column(name = "template_desc")
    private String templateDesc;

    /**
     * 模板内容
     */
    @Column(name = "template_content")
    private String templateContent;

    /**
     * 组ID
     */
    @Column(name = "group_id")
    private Long groupId;


    /**
     * 模板路径 如 /service
     */
    @Column(name = "template_path")
    private String templatePath;

    /**
     * 模板路径 如 /service
     */
    @Column(name = "template_file_name")
    private String templateFileName;

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