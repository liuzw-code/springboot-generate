package com.liuzw.generate.bean;

import lombok.*;

/**
 * TABLE_NAME:(t_code_params)
 * 生成代码的一些基本参数
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamsQueryBean extends BasePage {


    /**
     * 别名
     */
    private String name;


}