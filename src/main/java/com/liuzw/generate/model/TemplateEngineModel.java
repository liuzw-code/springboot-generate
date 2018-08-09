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
 * TABLE_NAME:(t_code_template_engine)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_code_template_engine")
public class TemplateEngineModel {


    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 组名称
     */
    @Column(name = "engine_name")
    private String engineName;


    /**
     * 描述
     */
    @Column(name = "engine_desc")
    private String engineDesc;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
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