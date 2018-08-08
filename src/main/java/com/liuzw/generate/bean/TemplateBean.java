package com.liuzw.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TABLE_NAME:(t_code_template)
 * 模板信息
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateBean {


    /**
     *
     */

    private Long id;


    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板类型
     */
    private String templateType;


    /**
     * 模板描述
     */
    private String templateDesc;

    /**
     * 模板内容
     */
    private String templateContent;

    /**
     * 组
     */
    private Long groupId;


    /**
     * 组
     */
    private String groupName;

    /**
     * 模板路径 如 /service
     */
    private String templatePath;

    /**
     * 生成模板名称 如 /service
     */
    private String templateFileName;


    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 修改时间
     */
    private String updateTime;


}