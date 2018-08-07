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
public class GenQueryBean extends BasePage {


    /**
     * id
     */
    private Long id;

    /**
     * 表名
     */
    private String tableName;


}