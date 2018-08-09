-- --------------------------------------------------------
-- 服务器版本:                     5.6.33 - MySQL Community Server (GPL)
-- 服务器操作系统:                  linux-glibc2.5
-- HeidiSQL 版本:                 9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 gen_code 的数据库结构
CREATE DATABASE IF NOT EXISTS `gen_code` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gen_code`;


-- 导出  表 gen_code.t_code_database_info 结构
CREATE TABLE IF NOT EXISTS `t_code_database_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `db_driver` varchar(100) NOT NULL DEFAULT 'com.mysql.jdbc.Driver' COMMENT '数据库驱动',
  `db_url` varchar(200) NOT NULL COMMENT '数据库地址',
  `db_username` varchar(100) NOT NULL COMMENT '数据库账户',
  `db_password` varchar(100) NOT NULL COMMENT '连接密码',
  `db_type` int(11) NOT NULL DEFAULT '1' COMMENT '数据库类型(1:mysql)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据库链接信息';

-- 正在导出表  gen_code.t_code_database_info 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `t_code_database_info` DISABLE KEYS */;
INSERT INTO `t_code_database_info` (`id`, `name`, `db_driver`, `db_url`, `db_username`, `db_password`, `db_type`, `create_time`, `update_time`) VALUES
	(1, 'liuzw', 'com.mysql.jdbc.Driver', 'jdbc:mysql://120.77.209.219:3306/liuzw', 'root', '123456', 1, '2018-08-03 11:45:05', '2018-08-06 20:57:36'),
	(2, 'ota', 'com.mysql.jdbc.Driver', 'jdbc:mysql://120.77.209.219:3306/ota', 'root', '123456', 1, '2018-08-03 11:45:05', '2018-08-06 20:57:43'),
	(20, '', 'com.mysql.jdbc.Driver', '', '', '', 1, '2018-08-08 16:49:19', '2018-08-08 16:49:19');
/*!40000 ALTER TABLE `t_code_database_info` ENABLE KEYS */;


-- 导出  表 gen_code.t_code_params 结构
CREATE TABLE IF NOT EXISTS `t_code_params` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '别名',
  `author` varchar(20) NOT NULL COMMENT '作者',
  `package_name` varchar(100) NOT NULL COMMENT '包路径',
  `local_path` varchar(100) NOT NULL DEFAULT '' COMMENT '本地路径',
  `copyright` varchar(2000) NOT NULL DEFAULT '' COMMENT '版权信息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='生成模板的基础信息';

-- 正在导出表  gen_code.t_code_params 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `t_code_params` DISABLE KEYS */;
INSERT INTO `t_code_params` (`id`, `name`, `author`, `package_name`, `local_path`, `copyright`, `create_time`, `update_time`) VALUES
	(1, '刘泽伟', 'liuzw', 'com.liuzw.xxx', '', '/**\n * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved.\n */', '2018-08-03 23:47:00', '2018-08-08 20:22:44');
/*!40000 ALTER TABLE `t_code_params` ENABLE KEYS */;


-- 导出  表 gen_code.t_code_template 结构
CREATE TABLE IF NOT EXISTS `t_code_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `template_name` varchar(50) NOT NULL COMMENT '模板名称',
  `template_type` varchar(50) NOT NULL COMMENT '模板类型',
  `template_desc` varchar(1000) DEFAULT NULL COMMENT '模板描述',
  `template_path` varchar(100) DEFAULT NULL COMMENT '模板路径',
  `template_content` text COMMENT '模板内容',
  `template_file_name` varchar(50) NOT NULL COMMENT '模板生成文件名称',
  `group_id` bigint(20) NOT NULL COMMENT '组ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='模板信息';

-- 正在导出表  gen_code.t_code_template 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `t_code_template` DISABLE KEYS */;
INSERT INTO `t_code_template` (`id`, `template_name`, `template_type`, `template_desc`, `template_path`, `template_content`, `template_file_name`, `group_id`, `create_time`, `update_time`) VALUES
	(1, 'controller', 'Java', '生成Controller', 'controller', '${data.copyright}\r\n\r\npackage ${data.packageName}.controller;\r\n\r\n\r\nimport ${data.packageName}.bean.${data.className}Bean;\r\nimport ${data.packageName}.bean.${data.className}QueryBean;\r\nimport ${data.packageName}.service.${data.className}Service;\r\nimport io.swagger.annotations.Api;\r\nimport io.swagger.annotations.ApiImplicitParam;\r\nimport io.swagger.annotations.ApiImplicitParams;\r\nimport io.swagger.annotations.ApiOperation;\r\nimport org.springframework.beans.factory.annotation.Autowired;\r\n\r\n\r\n/**\r\n * \r\n * @${data.author}\r\n **/\r\n<#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->\r\n<#assign pkProperty=data.pkColumns[0].javaFieldName!"">\r\n<#assign pkPropertyType=data.pkColumns[0].javaType!"">\r\n@RestController\r\n@RequestMapping("/${data.classVarName}")\r\n@Api(description = "管理")\r\npublic class ${data.className}Controller extends BaseController {\r\n\r\n    @Autowired\r\n    private ${data.className}Service ${data.classVarName}Service;\r\n\r\n\r\n    /**\r\n     *  获取所有数据\r\n     *\r\n     * @param bean  ${data.className}Bean\r\n     * @return     ResultData<PageInfo<${data.className}Bean>>\r\n     */\r\n     @ApiOperation(value = "获取所有数据", notes = "获取所有数据")\r\n     @PostMapping(value = "/list")\r\n     public ResultData<PageInfo<${data.className}Bean>> getList(@RequestBody ${data.className}QueryBean bean) {\r\n         return  ResultData.createSelectSuccessResult(convertPageInfo(${data.classVarName}Service.getList(bean), ${data.className}Bean.class));\r\n     }\r\n\r\n    /**\r\n     *  根据id获取数据\r\n     *\r\n     * @param ${pkProperty}    主键id\r\n     * @return     ResultData<${data.className}Bean>\r\n     */\r\n     @ApiOperation(value = "根据id获取数据", notes = "根据id获取数据")\r\n     @ApiImplicitParams({@ApiImplicitParam(name = "${pkProperty}", value = "数据Id", paramType = "query", dataType = "${pkPropertyType}")})\r\n     @PostMapping(value = "/query")\r\n     public ResultData<${data.className}Bean> query(@RequestBody IdBean idDto) {\r\n        return ResultData.createSelectSuccessResult(CopyDataUtil.copyObject(${data.classVarName}Service.getById(idBean.getId()), ${data.className}Bean.class));\r\n     }\r\n\r\n\r\n    /**\r\n     *  添加\r\n     *\r\n     * @param bean   ${data.className}Bean\r\n     * @return      ResultData<${data.className}Bean>\r\n     */\r\n     @ApiOperation(value = "增加数据", notes = "增加数据")\r\n     @PostMapping(value = "/add")\r\n     public ResultData<${data.className}Bean> insert(@RequestBody ${data.className}Bean bean) {\r\n        return ResultData.createInsertResult(${data.classVarName}Service.insert(bean));\r\n     }\r\n\r\n    /**\r\n     *  更新\r\n     *\r\n     * @param bean  ${data.className}Bean\r\n     * @return     ResultData<${data.className}Bean>\r\n     */\r\n     @ApiOperation(value = "更新数据", notes = "更新数据")\r\n     @PostMapping(value = "/edit")\r\n     public ResultData<${data.className}Bean> update(@RequestBody ${data.className}Bean bean) {\r\n        return ResultData.createUpdateResult(${data.classVarName}Service.update(bean));\r\n     }\r\n\r\n\r\n     /**\r\n      *  删除\r\n      *\r\n      * @param  idDto  主键id\r\n      * @return        ResultData<String>\r\n      */\r\n      @ApiOperation(value = "删除数据", notes = "删除数据")\r\n      @PostMapping(value = "/remove")\r\n      public ResultData<Void> delete(@RequestBody IdBean idBean) {\r\n         return ResultData.createDeleteResult(${data.classVarName}Service.delete(idBean.getId()));\r\n      }\r\n\r\n}\r\n', 'Controller.java', 3, '2018-08-08 20:10:55', '2018-08-08 20:58:05'),
	(2, 'service', 'Java', '生成service模板', 'service', '${data.copyright}\n\npackage ${data.packageName}.service;\n\n\nimport ${data.packageName}.bean.${data.className}Bean;\nimport ${data.packageName}.bean.${data.className}QueryBean;\nimport ${data.packageName}.model.${data.className}Model;\n\nimport java.util.List;\n\nimport .model.${data.className};\nimport .dto.${data.className};\n\n/**\n * interface ${data.className}\n *\n * @${data.author}\n */\n <#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->\n <#assign pkProperty=data.pkColumns[0].javaFieldName!"">\n <#assign pkPropertyType=data.pkColumns[0].javaType!"">\npublic interface ${data.className} {\n\n\n   /**\n    * 返回分页列表信息\n    *\n    * @param ${data.classVarName}   数据\n    * @return       list<${data.className}>\n    */\n    List<${data.className}> getList(${data.className}QueryBean ${data.classVarName}QueryBean);\n\n    /**\n     * 根据id返回信息\n     * @param id     ${pkProperty}\n     * @return       ${data.className}Model\n     */\n    ${data.className}Model getById(${pkPropertyType} ${pkProperty});\n\n\n    /**\n     * 根据ID删除\n     *\n     * @param id     ${pkProperty}\n     * @return       Boolean\n     */\n    Boolean delete(${pkPropertyType} ${pkProperty});\n\n    /**\n     *增加\n     *\n     * @param ${data.classVarName}Bean   数据\n     * @return      Boolean\n     */\n    Boolean insert(${data.className}Bean ${data.classVarName}Bean);\n\n    /**\n     *更新\n     *\n     * @param ${data.classVarName}Bean  数据\n     * @return     Boolean\n     */\n    Boolean update(${data.className}Bean ${data.classVarName}Bean);\n	\n}', 'Service.java', 3, '2018-08-04 21:22:47', '2018-08-08 21:00:21'),
	(3, 'serviceImpl', 'Java', '生成serviceImpl', 'service.impl', '${data.copyright}\n\npackage ${data.packageName}.service.impl;\n\n\n\nimport com.github.pagehelper.PageHelper;\nimport ${data.packageName}.bean.${data.className}Bean;\nimport ${data.packageName}.bean.${data.className}QueryBean;\nimport ${data.packageName}.mapper.${data.className}Mapper;\nimport ${data.packageName}.model.${data.className}Model;\nimport ${data.packageName}.service.${data.className}Service;\nimport ${data.packageName}.utils.CopyDataUtil;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.stereotype.Service;\nimport org.springframework.transaction.annotation.Propagation;\nimport org.springframework.transaction.annotation.Transactional;\nimport tk.mybatis.mapper.entity.Example;\n\n/**\n *  ${data.className}ServiceImpl\n *\n *  @${data.author}\n */\n<#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->\n<#assign pkProperty=data.pkColumns[0].javaFieldName!"">\n<#assign pkPropertyType=data.pkColumns[0].javaType!"">\n@Service("${data.classVarName}Service")\npublic class ${data.className}ServiceImpl  implements ${data.className}Service {\n     		\n		\n     @Autowired\n     private ${data.className}Mapper ${data.classVarName}Mapper;\n\n\n     @Override\n     public List<${data.className}Model> getList(${data.className}QueryBean ${data.classVarName}QueryBean) {\n	    PageHelper.startPage(${data.classVarName}QueryBean.getPageNumberByDefault(), ${data.classVarName}QueryBean.getPageSizeByDefault());\n	    Example example = new Example(ParamsModel.class);\n            Example.Criteria createCriteria = example.createCriteria();\n	    //此处写查询条件\n//         if (StringUtils.isNotEmpty(${data.classVarName}QueryBean.getName())) {\n//               createCriteria.andLike("name", "%" + bean.getName().trim() + "%");\n//          }\n	     return ${data.classVarName}Mapper.selectByExample(example);\n     }\n\n    @Override\n    public ${data.className}Model getById(${pkPropertyType} ${pkProperty}) {\n    	  return ${data.classVarName}Mapper.selectByPrimaryKey(${pkProperty});\n    }\n\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,\n		rollbackFor = {RuntimeException.class, Exception.class})\n    public Boolean insert(${data.className}Bean ${data.classVarName}Bean) {\n	   ${data.className}Model insertObj = CopyDataUtil.copyObject(${data.classVarName}Bean, ${data.className}Model.class);\n	   insertObj.setId(null);\n	   return ${data.classVarName}Mapper.insertSelective(insertObj) > 0;\n    }\n\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,\n		rollbackFor = {RuntimeException.class, Exception.class})\n    public Boolean update(${data.className}Bean ${data.classVarName}Bean) {\n	     ${data.className}Model updateObj = CopyDataUtil.copyObject(${data.classVarName}Bean, ${data.className}Model.class);\n	     return ${data.classVarName}Mapper.updateByPrimaryKeySelective(updateObj) > 0;\n    }\n\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,\n		rollbackFor = {RuntimeException.class, Exception.class})\n    public Boolean delete(${pkPropertyType} ${pkProperty}) {\n	      return ${data.classVarName}Mapper.deleteByPrimaryKey(${pkProperty}) > 0;\n    }\n\n\n}', 'ServiceImpl.java', 3, '2018-08-07 11:24:03', '2018-08-08 21:02:59'),
	(4, 'mapper', 'Java', '生成Mapper文件', 'mapper', '${data.copyright}\r\n\r\npackage ${data.packageName}.mapper;\r\n\r\n\r\nimport ${data.packageName}.model.${data.className}Model;\r\nimport tk.mybatis.mapper.common.Mapper;\r\nimport tk.mybatis.mapper.common.MySqlMapper;\r\n\r\n/**\r\n * interface ${data.className}Mapper\r\n *\r\n * @${data.author}\r\n */\r\n <#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->\r\n<#assign pkProperty=data.pkColumns[0].javaFieldName!"">\r\n<#assign pkPropertyType=data.pkColumns[0].javaType!"">\r\npublic interface ${data.className}Mapper extends Mapper<${data.className}Model>, MySqlMapper<${data.className}Model> {\r\n		\r\n\r\n}', 'Mapper.java', 3, '2018-08-08 19:46:29', '2018-08-08 20:11:09'),
	(5, 'queryBean', 'Java', '生成查询条件的Bean', 'bean', '${data.copyright}\r\n\r\npackage ${data.packageName}.bean;\r\n\r\n\r\nimport lombok.*;\r\nimport io.swagger.annotations.ApiModelProperty;\r\n\r\n/**\r\n * TABLE_NAME:(${data.tableName})\r\n *\r\n * @${data.author}\r\n */\r\n\r\n@Data\r\n@Builder\r\n@AllArgsConstructor\r\n@NoArgsConstructor\r\n@EqualsAndHashCode(callSuper = true)\r\npublic class ${data.className}QueryBean extends BasePage {\r\n\r\n    /**\r\n     *  这里只做查询使用，下面的查询参数按照实际业务进行增删，\r\n     */\r\n\r\n<#list data.columns as c>\r\n\r\n    /**\r\n     * ${c.javaFieldComment?if_exists}\r\n     */\r\n    <#if c.javaFieldComment == \'\'>\r\n    @ApiModelProperty(value = "${c.javaFieldName}", name = "${c.javaFieldName}"<#if c.isNullable == \'NO\'>, required=true</#if>)\r\n    <#else>\r\n    @ApiModelProperty(value = "${c.javaFieldComment}", name = "${c.javaFieldComment}"<#if c.isNullable == \'NO\'>, required=true</#if>)\r\n    </#if>\r\n    private ${c.javaType} ${c.javaFieldName};\r\n</#list>	\r\n	\r\n\r\n}', 'QueryBean.java', 3, '2018-08-08 19:53:31', '2018-08-08 20:49:55'),
	(6, 'bean', 'Java', '生成Bean', 'bean', '${data.copyright}\r\n\r\npackage ${data.packageName}.bean;\r\n\r\n\r\nimport lombok.*;\r\nimport io.swagger.annotations.ApiModelProperty;\r\n\r\n/**\r\n * TABLE_NAME:(${data.tableName})\r\n *\r\n * @${data.author}\r\n */\r\n\r\n@Data\r\n@Builder\r\n@AllArgsConstructor\r\n@NoArgsConstructor\r\n@EqualsAndHashCode\r\npublic class ${data.className}Bean {\r\n\r\n    /**\r\n     *  这里只做查询使用，下面的查询参数按照实际业务进行增删，\r\n     */\r\n\r\n<#list data.columns as c>\r\n    /**\r\n     * ${c.javaFieldComment?if_exists}\r\n     */\r\n    <#if c.javaFieldComment == \'\'>\r\n    @ApiModelProperty(value = "${c.javaFieldName}", name = "${c.javaFieldName}"<#if c.isNullable == \'NO\'>, required=true</#if>)\r\n    <#else>\r\n    @ApiModelProperty(value = "${c.javaFieldComment}", name = "${c.javaFieldComment}"<#if c.isNullable == \'NO\'>, required=true</#if>)\r\n    </#if>\r\n    private ${c.javaType} ${c.javaFieldName};\r\n</#list>	\r\n	\r\n\r\n}', 'Bean.java', 3, '2018-08-08 19:58:56', '2018-08-08 20:11:16'),
	(7, 'model', 'Java', '生成Model', 'model', '${data.copyright}\r\n\r\npackage ${data.packageName}.model;\r\n\r\nimport lombok.AllArgsConstructor;\r\nimport lombok.Builder;\r\nimport lombok.Data;\r\nimport lombok.NoArgsConstructor;\r\nimport javax.persistence.*;\r\n\r\n/**\r\n * TABLE_NAME:(${data.tableName})\r\n *\r\n *  @${data.author}\r\n */\r\n\r\n@Data\r\n@Builder\r\n@AllArgsConstructor\r\n@NoArgsConstructor\r\n@Table(name ="${data.tableName}")\r\npublic class ${data.className}Model {\r\n\r\n\r\n<#list data.columns as c>\r\n    /**\r\n     * ${c.javaFieldComment?if_exists}\r\n     */\r\n    <#if data.pkColumns[0].javaFieldName == c.javaFieldName >\r\n    @Id\r\n    @GeneratedValue(strategy = GenerationType.IDENTITY)\r\n    <#else>\r\n    @Column(name = "${c.columnName}")\r\n    </#if>\r\n    private ${c.javaType} ${c.javaFieldName};\r\n</#list>	\r\n	\r\n\r\n}', 'Model.java', 3, '2018-08-08 20:00:20', '2018-08-08 21:24:55'),
	(8, 'mapper.xml', 'Xml', '生成mybaits的Xml', 'mapper', '<?xml version="1.0" encoding="UTF-8" ?>\n<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"\n        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">\n<mapper namespace="${data.packageName}.mapper.${data.className}Mapper">\n\n	<resultMap id="BaseResultMap" type="${data.packageName}.bean.${data.className}Bean">\n		<#list data.columns as c>\n		<result column="${c.columnName}" jdbcType="${c.columnType}" property="${c.javaFieldName}" />\n		</#list>\n	</resultMap>\n\n\n	<sql id="Base_Column_List">\n		<#list data.columns as c>\n		`${c.columnName}`<#if c_has_next>,</#if>\n		</#list>\n	</sql>\n\n\n</mapper>', 'Mapper.xml', 3, '2018-08-08 20:09:19', '2018-08-08 21:27:03'),
	(9, 'vue', 'Html', '生成vue文件', 'html', '<template>\r\n    <section>\r\n        <!--工具条-->\r\n        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">\r\n\r\n            <el-form align="right" :inline="true"  class="demo-form-inline">\r\n                <el-form-item label="">\r\n                    <el-input v-model="filters.xxxxx" placeholder="查询条件没写，自己写....."></el-input>\r\n                </el-form-item>\r\n                <el-form-item label="">\r\n                    <el-button type="primary" v-on:click="searchData">查询</el-button>\r\n                </el-form-item>\r\n                <el-form-item label="">\r\n                    <el-button type="primary" @click="handleEditOrAdd">新增</el-button>\r\n                </el-form-item>\r\n            </el-form>\r\n        </el-col>\r\n\r\n        <!--列表-->\r\n        <el-table :data="list"  height="500" highlight-current-row v-loading="listLoading" @selection-change="selsChange"\r\n                  style="width: 100%;">\r\n            <el-table-column type="selection" ></el-table-column>\r\n           <#list data.columns as c>\r\n             <el-table-column prop="${c.javaFieldName}" label="${c.propertyCname?if_exists}"   sortable></el-table-column>\r\n           </#list>\r\n\r\n            <el-table-column  fixed="right" label="操作" width="150">\r\n                <template slot-scope="scope">\r\n                    <el-button size="small" @click="handleEditOrAdd(scope.$index, scope.row)">编辑</el-button>\r\n                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>\r\n                </template>\r\n            </el-table-column>\r\n        </el-table>\r\n\r\n        <!--工具条-->\r\n        <el-col :span="24" style="margin-top: 10px">\r\n            <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>\r\n            <el-pagination\r\n                    background\r\n                    @size-change="handleSizeChange"\r\n                    @current-change="handleCurrentChange"\r\n                    :current-page="pageNum"\r\n                    :page-sizes="[10, 20, 30, 40]"\r\n                    :page-size="pageSize"\r\n                    layout="total, sizes, prev, pager, next, jumper"\r\n                    :total="total" style="float: right">\r\n            </el-pagination>\r\n        </el-col>\r\n\r\n        <!--编辑界面-->\r\n        <el-dialog :title="title" v-model="formVisible" :visible.sync="formVisible">\r\n            <el-form :model="form" label-width="80px" :rules="formRules" ref="form">\r\n            <#list data.columns as c>\r\n               <el-form-item label="${c.javaFieldComment?if_exists}" prop="${c.javaFieldName}">\r\n                  <el-input v-model="form.${c.javaFieldName}" auto-complete="off"></el-input>\r\n               </el-form-item>\r\n            </#list>\r\n\r\n            </el-form>\r\n            <div slot="footer" class="dialog-footer">\r\n                <el-button @click.native="formVisible = false">取消</el-button>\r\n                <el-button type="primary" @click.native="submit" :loading="loading">提交</el-button>\r\n            </div>\r\n        </el-dialog>\r\n    </section>\r\n</template>\r\n\r\n<script>\r\n    import {get${data.className}List, remove${data.className}, batchRemove${data.className}, edit${data.className}, add${data.className}} from \'../../api/api\';\r\n\r\n    export default {\r\n        data() {\r\n            return {\r\n                filters: {\r\n                    name: \'\',\r\n                },\r\n                list: [],\r\n                total: 0,\r\n                title:"",//弹出框标题\r\n                pageNum: 1, //当前页码\r\n                pageSize: 10,//页数\r\n                listLoading: false,\r\n                sels: [],//列表选中列\r\n                formVisible: false,//新增 or 编辑界面是否显示\r\n                loading: false,\r\n                formRules: {\r\n                    name: [{required: true, message: \'请输入姓名\', trigger: \'blur\'}]\r\n                },\r\n                //新增 or 编辑界面数据\r\n                form: {\r\n                  <#list data.columns as c>\r\n                      ${c.javaFieldName}:\'\' <#if c_has_next>,</#if>\r\n                  </#list>\r\n                },\r\n\r\n            }\r\n        },\r\n        methods: {\r\n            //切换页数\r\n            handleCurrentChange(val) {\r\n                this.pageNum = val;\r\n                this.getData();\r\n            },\r\n            //处理分页条数\r\n            handleSizeChange(val) {\r\n                this.pageSize = val;\r\n                this.getData();\r\n            },\r\n            searchData(){\r\n                this.pageNum = 1;\r\n                this.getData();\r\n            },\r\n            //获取用户列表\r\n            getData() {\r\n                let para = {\r\n                   // xxxxx: this.filters.xxxxx,\r\n                    pageInfo: {\r\n                        pageNum: this.pageNum,\r\n                        pageSize: this.pageSize,\r\n                    }\r\n                };\r\n                this.listLoading = true;\r\n                get${data.className}List(para).then((res) => {\r\n                    this.listLoading = false;\r\n                    if (res.code === 1) {\r\n                        this.total = res.data.total;\r\n                        this.list = res.data.list;\r\n                    }\r\n                }, () => { this.listLoading = false; });\r\n            },\r\n            //删除\r\n            handleDel: function (index, row) {\r\n                this.$confirm(\'确认删除该记录吗?\', \'提示\', {\r\n                    type: \'warning\'\r\n                }).then(() => {\r\n                    this.listLoading = true;\r\n                    let para = {id: row.id};\r\n                    remove${data.className}(para).then((res) => {\r\n                        this.listLoading = false;\r\n                        if (res.code === 1) {\r\n                            this.success("删除成功");\r\n                            this.getData();\r\n                        }\r\n                    });\r\n                }).catch(() => {});\r\n            },\r\n            //显示编辑/新增界面\r\n            handleEditOrAdd: function (index, row) {\r\n                if (row) {\r\n                    this.title= "编辑";\r\n                    this.form = Object.assign({}, row);\r\n                } else {\r\n                    this.title= "新增";\r\n                    this.form = {};\r\n                }\r\n                this.formVisible = true;\r\n            },\r\n            //编辑\r\n            submit: function () {\r\n                this.$refs.form.validate((valid) => {\r\n                    if (valid) {\r\n                        this.$confirm(\'确认提交吗？\', \'提示\', {}).then(() => {\r\n                            this.loading = true;\r\n                            let para = this.form;\r\n                            if(para.id) {\r\n                                edit${data.className}(para).then((res) => {\r\n                                    this.loading = false;\r\n                                    if (res.code === 1){\r\n                                        this.success("修改成功");\r\n                                        this.$refs[\'form\'].resetFields();\r\n                                        this.formVisible = false;\r\n                                        this.getData();\r\n                                     }\r\n                                })\r\n                            } else {\r\n                                add${data.className}(para).then((res) => {\r\n                                    this.loading = false;\r\n                                    if (res.code === 1) {\r\n                                        this.success("新增成功");\r\n                                        this.$refs[\'form\'].resetFields();\r\n                                        this.formVisible = false;\r\n                                        this.getData();\r\n                                    }\r\n                                });\r\n                            }\r\n                        });\r\n                    }\r\n                });\r\n            },\r\n\r\n            selsChange: function (sels) {\r\n                this.sels = sels;\r\n            },\r\n            //批量删除\r\n            batchRemove: function () {\r\n                var ids = this.sels.map(item => item.id).toString();\r\n                this.$confirm(\'确认删除选中记录吗？\', \'提示\', {\r\n                    type: \'warning\'\r\n                }).then(() => {\r\n                    this.listLoading = true;\r\n                    let para = {ids: ids};\r\n                    batchRemove${data.className}(para).then((res) => {\r\n                        this.listLoading = false;\r\n                        if (res.code === 1) {\r\n                            this.success("删除成功");\r\n                            this.getData();\r\n                        }\r\n                    });\r\n                });\r\n            },\r\n\r\n            success: function(message){\r\n                this.$message({\r\n                    message: message,\r\n                    type: \'success\'\r\n                });\r\n            }\r\n        },\r\n        mounted() {\r\n            this.getData();\r\n        }\r\n    }\r\n\r\n</script>\r\n\r\n<style scoped>\r\n\r\n</style>', '.vue', 3, '2018-08-09 09:08:17', '2018-08-09 09:08:27');
/*!40000 ALTER TABLE `t_code_template` ENABLE KEYS */;


-- 导出  表 gen_code.t_code_template_group 结构
CREATE TABLE IF NOT EXISTS `t_code_template_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL COMMENT '组名称',
  `group_desc` varchar(400) NOT NULL DEFAULT '' COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='模板组';

-- 正在导出表  gen_code.t_code_template_group 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `t_code_template_group` DISABLE KEYS */;
INSERT INTO `t_code_template_group` (`id`, `group_name`, `group_desc`, `create_time`, `update_time`) VALUES
	(3, 'test', 'test', '2018-08-04 20:55:11', '2018-08-04 20:55:11'),
	(4, 'liuzw', 'liuzw', '2018-08-04 20:55:26', '2018-08-04 20:55:26');
/*!40000 ALTER TABLE `t_code_template_group` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
