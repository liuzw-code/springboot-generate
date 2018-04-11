package com.liuzw.generate.mapper;

import com.liuzw.generate.bean.Column;

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
	
	

}
