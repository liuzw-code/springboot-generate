package com.liuzw.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuzw
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicData {

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 类名
	 */
	private String className;

	/**
	 * 类变量名
	 */
	private String varName;

	/**
	 * 数据库名
	 */
	private String dataBaseName;

	/**
	 * 字段列(包含主键)
	 */
	private List<Column> columns;

	/**
	 * 主键列表
	 */
	private List<Column> pkColumns;

	/**
	 * 包路径
	 */
	private String packagePath;

	/**
	 * 模块名称
	 */
	private String module;

	/**
	 * 数据库类型
	 */
	private String dataBaseType;

}
