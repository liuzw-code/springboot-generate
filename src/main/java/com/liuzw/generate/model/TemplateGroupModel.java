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
 * TABLE_NAME:(t_code_template_group)
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_code_template_group")
public class TemplateGroupModel {


    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * 组名称
     */
    @Column(name = "group_name")
    private String groupName;


    /**
     * 描述
     */
    @Column(name = "group_desc")
    private String groupDesc;


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