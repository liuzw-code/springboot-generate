package com.liuzw.generate.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 存放数据库和表的一些基本信息
 *
 * @author liuzw
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicDataBean {

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
	private String classVarName;

	/**
	 * 包名
	 */
	private String packageName;

	/**
	 * 类路径
	 */
	private String classPath;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 版权信息
	 */
	private String copyright;

	/**
	 * 字段列(包含主键)
	 */
	private List<ColumnBean> columns;

	/**
	 * 主键列表
	 */
	private List<ColumnBean> pkColumns;


	public static BasicDataBean get(ParamsBean bean) {
		return BasicDataBean.builder()
				.author(bean.getAuthor())
				.copyright(bean.getCopyright())
				.packageName(bean.getPackageName())
				.build();
	}

}
