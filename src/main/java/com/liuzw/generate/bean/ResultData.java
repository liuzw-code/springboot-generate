package com.liuzw.generate.bean;

import com.liuzw.generate.constants.ResultDataConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 返回类型
 *
 * @author liuzw
 **/

@Data
public class ResultData<T> implements Serializable {

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
     * 创建新增返回结果
     *
     * @param flag boolean
     * @return ResultData
     */
    public static <E> ResultData<E> createInsertResult(Boolean flag) {
        if (flag) {
            return createSuccessResult(null, ResultDataConstants.MSG_INSERT_SUCCESS);
        } else {
            return createErrorResult(ResultDataConstants.MSG_INSERT_ERROR);
        }
    }


    /**
     * 创建新增返回结果
     *
     * @param flag    boolean
     * @param message 返回内容
     * @return ResultData
     */
    public static <E> ResultData<E> createInsertResult(Boolean flag, String message) {
        if (flag) {
            return createSuccessResult(null, message);
        } else {
            return createErrorResult(message);
        }
    }


    /**
     * 创建删除返回结果
     *
     * @param flag boolean
     * @return ResultData
     */
    public static <E> ResultData<E> createDeleteResult(Boolean flag) {
        if (flag) {
            return createSuccessResult(null, ResultDataConstants.MSG_DELETE_SUCCESS);
        } else {
            return createErrorResult(ResultDataConstants.MSG_DELETE_ERROR);
        }
    }

    /**
     * 创建删除返回结果
     *
     * @param flag    boolean
     * @param message 错误信息
     * @return ResultData
     */
    public static ResultData createDeleteResult(Boolean flag, String message) {
        if (flag) {
            return createSuccessResult(null, message);
        } else {
            return createErrorResult(message);
        }
    }


    /**
     * 创建修改成功返回结果
     *
     * @param flag boolean
     * @return ResultData
     */
    public static <E> ResultData<E> createUpdateResult(Boolean flag) {
        if (flag) {
            return createSuccessResult(null, ResultDataConstants.MSG_UPDATE_SUCCESS);
        } else {
            return createErrorResult(ResultDataConstants.MSG_UPDATE_ERROR);
        }
    }


    /**
     * 创建修改返回结果
     *
     * @param flag    boolean
     * @param message 错误信息
     * @return ResultData
     */
    public static <E> ResultData<E> createUpdateResult(Boolean flag, String message) {
        if (flag) {
            return createSuccessResult(null, message);
        } else {
            return createErrorResult(message);
        }
    }


    /**
     * 返回成功信息
     *
     * @param data 返回结果集
     * @return ResultData
     */
    public static <E> ResultData<E> createSelectSuccessResult(E data) {
        return new ResultData<>(ResultDataConstants.CODE_SUCCESS, ResultDataConstants.MSG_SUCCESS, data);
    }

    /**
     * 返回成功信息
     *
     * @param data    返回结果集
     * @param message 返回内容
     * @return ResultData
     */
    public static <E> ResultData<E> createSelectSuccessResult(E data, String message) {
        if (StringUtils.isEmpty(message)) {
            message = ResultDataConstants.MSG_SUCCESS;
        }
        return new ResultData<>(ResultDataConstants.CODE_SUCCESS, message, data);
    }


    /**
     * 返回错误信息
     *
     * @param message 返回内容
     * @return ResultData
     */
    public static <E> ResultData<E> createErrorResult(String message) {
        if (StringUtils.isEmpty(message)) {
            message = ResultDataConstants.MSG_ERROR;
        }
        return new ResultData<>(ResultDataConstants.CODE_ERROR, message, null);
    }

    /**
     * 返回成功信息
     *
     * @param data    返回结果集
     * @param message 返回内容
     * @return ResultData
     */
    public static <E> ResultData<E> createSuccessResult(E data, String message) {
        if (StringUtils.isEmpty(message)) {
            message = ResultDataConstants.MSG_SUCCESS;
        }
        return new ResultData<>(ResultDataConstants.CODE_SUCCESS, message, data);
    }


    private ResultData(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
