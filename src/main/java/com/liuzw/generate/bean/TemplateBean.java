package com.liuzw.generate.bean;

import com.liuzw.generate.valid.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;


    /**
     * 模板名称
     */
    @NotBlank(message = "模板名称不能为空")
    private String templateName;

    /**
     * 模板类型
     */
    @NotBlank(message = "模板类型不能为空")
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
    @NotNull(message = "模板组不能为空")
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
    @NotNull(message = "生成模板名称不能为空")
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