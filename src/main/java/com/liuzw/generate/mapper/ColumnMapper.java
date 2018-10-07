package com.liuzw.generate.mapper;

import com.liuzw.generate.bean.ColumnBean;
import com.liuzw.generate.bean.TableBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liuzw
 */
public interface ColumnMapper {


	/**
     * 获取表的所有字段
	 *
	 * @param tableNames 表名
	 * @return List
	 */
	List<ColumnBean> getTableAllColumns(@Param("tableNames") List<String> tableNames);

	/**
     * 获取表的主键
	 *
	 * @param tableNames 表名
	 * @return List
	 */
	List<ColumnBean> getTablePkColumns(@Param("tableNames") List<String> tableNames);

	/**
     * 获取数据库中的表的信息
	 *
	 * @param tableName 表名
	 * @return List
	 */
	List<TableBean> getList(@Param("tableName") String tableName);

	/**
	 * 获取数据库中的表的备注
	 *
	 * @param tableNames 表名
	 * @return List
	 */
	List<TableBean> getTableInfoByTableName(@Param("tableNames") List<String> tableNames);


}