package com.liuzw.generate.controller;


import com.liuzw.generate.bean.Page;
import com.liuzw.generate.bean.Query;
import com.liuzw.generate.bean.Result;
import com.liuzw.generate.bean.Table;
import com.liuzw.generate.service.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author liuzw
 */
@Controller
@RequestMapping
public class GenerateController {
	
	@Autowired
	private IGenerateService generateService;


	/**
	 * 跳转到 thymeleaf 页面
	 */
	@RequestMapping(value={"/","/index"})
	public String index() {
		return "index";
	}
	/**
	 * 跳转到 thymeleaf 页面
	 */
	@RequestMapping("/generator")
	public String generator() {
		return "generator";
	}

	/**
	 * 生成所有
	 *
	 */
	@RequestMapping("/generate")
	public void generatorAll(HttpServletRequest request, HttpServletResponse response) {
		String tableNames = request.getParameter("tables");
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
		response.setContentType("application/zip; charset=UTF-8");
		generateService.generatorAll(response, tableNames);
	}

	/**
	 * 生成所有
	 *
	 */
	@RequestMapping("/generateCode")
	@ResponseBody
	public String generateCode(HttpServletRequest request) {
		String tableNames = request.getParameter("tables");
		String path = request.getParameter("path");
		generateService.generatorCode(tableNames, path);
		return "success";
	}



	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Result<Page<Table>> list(HttpServletRequest request){
		Query query = Query.builder().pageNum(Integer.valueOf(request.getParameter("pageNum")))
				.pageSize(Integer.valueOf(request.getParameter("pageSize")))
				.tableName(request.getParameter("tableName")).build();
		return Result.createSuccessResult(generateService.queryList(query));
	}

}
