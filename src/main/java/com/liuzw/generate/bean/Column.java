package com.liuzw.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzw
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Column {
	
	/**
	 * 字段名称
	 */
	private String columnName;
	/**
	 * 字段中文名
	 */
	private String columnCname;
	/**
	 * 字段属性名称
	 */
	private String propertyName;
	/**
	 * 字段属性中文名
	 */
	private String propertyCname;
	/**
	 * 字段长度
	 */
	private Integer dataLength;
	/**
	 * 字段类型
	 */
	private String dataType;
	/**
	 * 类中属性类型
	 */
	private String propertyType;
	/**
	 * 字段类型
	 */
	private String jdbcType;

}
