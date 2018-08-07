package com.liuzw.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
     * 基本参数
     */
    @NotNull(message = "基本参数不能为空")
    private ParamsBean params;

    /**
     * 模板id
     */
    @NotNull(message = "模板id不能为空")
    private List<Long> templateIds;

    /**
     * 表
     */
    @NotNull(message = "表名不能为空")
    private List<String> tableNames;

    /**
     * 数据库连接信息 id
     */
    @NotNull(message = "数据库连接信息id不能为空")
    private Long databaseId;

}