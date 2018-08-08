package com.liuzw.generate.bean;

import com.liuzw.generate.valid.Update;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
     * id
     */

    private Long id;


    /**
     * 名称
     */

    private String name;


    /**
     * 作者
     */
    @NotBlank(message = "作者不能为空")
    private String author;


    /**
     * 包名
     */
    @NotBlank(message = "包名不能为空")
    private String packageName;

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