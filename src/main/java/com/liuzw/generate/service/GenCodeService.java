package com.liuzw.generate.service;

import com.liuzw.generate.bean.GenCodeBean;

import javax.servlet.http.HttpServletResponse;

/**
 * @author liuzw
 */
public interface GenCodeService {


	/**
	 * 下载生成的代码 or 生成代码到本地
	 *
	 * @param bean 	   参数
	 * @param response response
	 * @return         Boolean
	 */
	Boolean genCode(HttpServletResponse response, GenCodeBean bean);

}