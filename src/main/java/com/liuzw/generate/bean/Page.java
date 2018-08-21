package com.liuzw.generate.bean;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;


/**
 * 封装分页信息
 *
 * @author liuzw
 */
@Data
public class Page<T> {

    /**
     * 结果集
     */
    private List<T> data;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Long total;

    public Page() {

    }

    /**
     * 包装Page对象
     *
     * @param data List<T>
     */
    public Page(List<T> data) {
        if (data instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page page = (com.github.pagehelper.Page) data;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.data = data;
        } else {
            this.pageNum = 1;
            this.pageSize = data.size();
            this.total = (long) data.size();
            this.data = data;
        }
    }

    /**
     * 包装Page对象
     *
     * @param data List<T>
     */
    public Page(List<T> data, int navigatePages) {
        PageInfo<T> pageInfo = new PageInfo<>(data, navigatePages);
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.total = pageInfo.getTotal();
        this.data = data;
    }
}
