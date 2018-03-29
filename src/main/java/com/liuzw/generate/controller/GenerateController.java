package com.liuzw.generate.controller;


import com.liuzw.generate.service.IGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘泽伟
 */
@RestController
@RequestMapping("generate")
public class GenerateController {
	
	@Autowired
	private IGenerateService generateService;

	/**
	 * 生成实体类
	 *
	 */
	@RequestMapping("generateEntity")
	public void generateEntity(String tableNames) {
		generateService.generateEntity(tableNames);
	}

	/**
	 * 生成service接口
	 *
     */
	@RequestMapping("generateService")
	public void generateService(String tableNames){
		generateService.generateService(tableNames);
	}

	/**
	 * 生成service实现类
	 *
	 */
	@RequestMapping("generateServiceImpl")
	public void generateServiceImpl(String tableNames){
		generateService.generateServiceImpl(tableNames);
	}

	/**
	 * 生成dao层
	 *
	 */
	@RequestMapping("generateDao")
	public void generateDao(String tableNames){
		generateService.generateDao(tableNames);
	}

	/**
	 * 生成mapper文件
	 *
	 */
	@RequestMapping("generateMapper")
	public void generateMapper(String tableNames){
		 generateService.generateMapper(tableNames);
	}

	/**
	 * 生成所有
	 *
	 */
	@RequestMapping("generateAll")
	public void generateAll(String tableNames){
		generateService.generateAll(tableNames);
	}

}
