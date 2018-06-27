package com.liuzw.generate.mapper;

import com.liuzw.generate.bean.Column;
import com.liuzw.generate.bean.Query;
import com.liuzw.generate.bean.Table;

import java.util.List;
import java.util.Map;

/**
 * @author liuzw
 */
public interface ColumnMapper {


	/**
	 * 获取表的所有字段
	 *
	 * @param params 需要传入 dataBaseName 和 tableName
	 * @return       List<Column>
	 */
	List<Column> getTableAllColumns(Map<String, String> params);

	/**
	 * 获取表的主键
	 *
	 * @param params 需要传入 dataBaseName 和 tableName
	 * @return       List<Column>
	 */
	List<Column> getTablePkColumns(Map<String, String> params);

	/**
	 * 获取数据库中的表的信息
	 *
	 * @param map     map
	 * @return        List<Table>
	 */
	List<Table> queryList(Map<String, Object> map);

	/**
	 * 统计数据库中的表的数量
	 *
	 * @param query   Query
	 * @return        List
	 */
	Long queryTotal(Query query);

}
