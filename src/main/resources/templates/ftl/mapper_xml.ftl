<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
 <#assign pkProperty = data.pkColumns[0].propertyName!"">
 <#assign pkJdbcType = data.pkColumns[0].jdbcType!"">
 <#assign pkColumnName = data.pkColumns[0].columnName!"">
 <#assign pkPropertyType = data.pkColumns[0].propertyType!"">
 <#assign start="#">
 <#assign start=start+"{">
 <#assign end="}">

<mapper namespace="${data.packagePath}.${data.module}.dao.${data.className}Mapper">

    <resultMap id="BaseResultMap" type="${data.packagePath}.${data.module}.bean.${data.className}">
	<#list data.columns as c>
         <#if data.pkColumns[0].columnName == c.propertyName >
         <id column="${c.columnName}" jdbcType="${c.jdbcType}" property="${c.propertyName}" />
         <#else>
         <result column="${c.columnName}" jdbcType="${c.jdbcType}" property="${c.propertyName}" />
         </#if>
	</#list>
    </resultMap>

    <sql id="Base_Column_List">
    <#list data.columns as c>
        ${c.columnName}<#if c_has_next>,</#if>
    </#list>
    </sql>


    <delete id="batchRemove" parameterType="${pkPropertyType}">
        delete from ${data.tableName}
        where ${pkColumnName} in
        <foreach collection="ids" item="id" separator="," open="(" close=")">
            ${start}id, jdbcType=${pkJdbcType}${end}
        </foreach>
    </delete>



</mapper>