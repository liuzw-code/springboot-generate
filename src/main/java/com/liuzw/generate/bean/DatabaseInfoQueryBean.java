package com.liuzw.generate.bean;

import lombok.*;

/**
 * 数据库信息查询
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DatabaseInfoQueryBean extends BasePage {


    /**
     * 名称
     */
    private String name;

    /**
     * 数据库地址
     */
    private String dbUrl;

}