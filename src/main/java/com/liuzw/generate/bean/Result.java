package com.liuzw.generate.bean;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 封装返回结果
 *
 * @author liuzw
 */
@Data
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 返回内容
	 */
	private String message;

	/**
	 * 返回结果集
	 */
	private T data;

	/**
	 * 返回错误信息
	 *
	 * @return         Result
	 */
	public static <E> Result<E> createErrorResult() {
		return createErrorResult(null);
	}

	/**
	 * 返回错误信息
	 *
	 * @param message  返回内容
	 * @return         Result
	 */
	public static <E> Result<E> createErrorResult(String message) {
		if (StringUtils.isEmpty(message)) {
			message = "error";
		}
		return new Result<>(2, message, null);
	}



	/**
	 * 返回成功信息
	 *
	 * @param data     返回结果集
	 * @return         Result
	 */
	public static <E> Result<E> createSuccessResult(E data) {
		return createSuccessResult(data, null);
	}
	/**
	 * 返回成功信息
	 *
	 * @param data     返回结果集
	 * @param message      返回内容
	 * @return         Result
	 */
	public static <E> Result<E> createSuccessResult(E data, String message) {
		if (StringUtils.isEmpty(message)) {
			message = "success";
		}
		return new Result<>(1, message, data);
	}


	private Result(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

}
