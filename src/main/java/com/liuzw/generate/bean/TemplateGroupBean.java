package com.liuzw.generate.bean;

import com.liuzw.generate.valid.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TABLE_NAME:(t_code_template_group)
 * 模板组
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateGroupBean {


    /**
     *
     */
    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;


    /**
     * 组名称
     */
    @NotBlank(message = "组名称不能为空")
    private String groupName;


    /**
     * 描述
     */
    private String groupDesc;


    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 修改时间
     */
    private String updateTime;


}