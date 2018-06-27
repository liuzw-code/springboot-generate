package com.liuzw.generate.bean;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author liuzw
 */
@Data
@Builder
public class Query implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页条数
     */
    private int pageSize;


    /**
     * 表名
     */
    private String tableName;

}
