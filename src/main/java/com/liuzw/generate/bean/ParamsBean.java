package com.liuzw.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
public class ParamsBean {


    /**
     *
     */
    private Long id;


    /**
     * 别名
     */
    private String name;


    /**
     * 作者
     */
    private String author;


    /**
     * 包名
     */
    private String packageName;


    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 本地路径
     */
    private String localPath;


    /**
     * 版权信息
     */
    private String copyright;


    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 创建时间
     */
    private String updateTime;

}