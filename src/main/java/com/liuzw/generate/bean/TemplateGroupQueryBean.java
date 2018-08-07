package com.liuzw.generate.bean;

import lombok.*;

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
@EqualsAndHashCode(callSuper = true)
public class TemplateGroupQueryBean extends BasePage {


    /**
     * 组名称
     */
    private String groupName;


    /**
     * 描述
     */
    private String groupDesc;


}