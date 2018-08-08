package com.liuzw.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class GenCodeBean {

    /**
     * 数据库连接信息 id
     */
    private Long databaseId;

    /**
     * 基本参数
     */
    private ParamsBean params;

    /**
     * 模板id
     */
    private List<Long> templateIds;

    /**
     * 表
     */
    private List<String> tableNames;

}