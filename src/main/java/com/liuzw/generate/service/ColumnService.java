package com.liuzw.generate.service;

import com.liuzw.generate.bean.ColumnBean;
import com.liuzw.generate.bean.GenQueryBean;
import com.liuzw.generate.bean.TableBean;

import java.util.List;

/**
 * @author liuzw
 */
public interface ColumnService {


	/**
     * 获取表的所有字段
	 *
	 * @param tableName 表名
	 * @return List
	 */
	List<ColumnBean> getTableAllColumns(String tableName);

	/**
     * 获取表的主键
	 *
	 * @param tableName 表名
	 * @return List
	 */
	List<ColumnBean> getTablePkColumns(String tableName);

	/**
     * 获取数据库中的表的信息
	 *
	 * @param bean 表名
	 * @return List
	 */
	List<TableBean> getList(GenQueryBean bean);

}