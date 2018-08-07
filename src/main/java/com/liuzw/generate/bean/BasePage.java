package com.liuzw.generate.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页基础类
 *
 * @author liuzw
 * @date 2018/5/28 14:22
 **/
@Setter
@Getter
public class BasePage {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页数
     */
    private Integer pageSize;

    public Integer getPageNum() {
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        return pageNum;
    }


    public Integer getPageSize() {
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        return pageSize;
    }

}

