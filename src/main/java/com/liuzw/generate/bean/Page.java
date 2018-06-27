package com.liuzw.generate.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * @author liuzw
 */
@Data
public class Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

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
	 * 总条数
	 */
	private Long total;

	/**
	 * 总页数
	 */
	private Long totalPage;


	public Page() {

	}

	/**
	 * 分页
	 * @param list        列表数据
	 * @param total       总记录数
	 * @param pageSize    每页记录数
	 * @param pageNum     当前页数
	 */
	public Page(List<T> list, Long total, Integer pageSize, Integer pageNum) {
		this.data = list;
		this.total = total;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.totalPage = (long) Math.ceil((double)total / pageSize);
	}


}
