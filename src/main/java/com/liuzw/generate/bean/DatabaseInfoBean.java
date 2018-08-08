package com.liuzw.generate.bean;

import com.liuzw.generate.valid.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
     * id
     */
    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;


    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;


    /**
     * 数据库驱动
     */
    private String dbDriver;


    /**
     * 数据库地址
     */
    @NotBlank(message = "数据库地址不能为空")
    private String dbUrl;


    /**
     * 数据库账户
     */
    @NotBlank(message = "数据库账户不能为空")
    private String dbUsername;


    /**
     * 连接密码
     */
    @NotBlank(message = "数据库密码不能为空")
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