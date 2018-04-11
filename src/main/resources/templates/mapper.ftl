<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
 <#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->
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
            <id column="${c.columnName}" jdbcType="${c.jdbcType}" property="${c.propertyName}" />
		</#list>
    </resultMap>


    <sql id="Base_Column_List">
		<#list data.columns as c>
			${c.columnName}<#if c_has_next>,</#if>
		</#list>
    </sql>


    <insert id="insert" parameterType="${data.packagePath}.${data.module}.bean.${data.className}">
        insert into ${data.tableName}(
			 <#list data.columns as c>
				 ${c.columnName}<#if c_has_next>,</#if>
			 </#list>
        ) values(
			  <#list data.columns as c>
				  ${start}${c.propertyName},jdbcType=${c.jdbcType}${end}<#if c_has_next>,</#if>
			  </#list>
        )
    </insert>


    <insert id="batchInsert" parameterType="${data.packagePath}.${data.module}.bean.${data.className}">
        insert into ${data.tableName}(
			<#list data.columns as c>
				${c.columnName}<#if c_has_next>,</#if>
			</#list>
        ) values
        <foreach collection="beans" item="bean" separator=",">
            (
		<#list data.columns as c>
			${start}bean.${c.propertyName},jdbcType=${c.jdbcType}${end}<#if c_has_next>,</#if>
		</#list>
            )
        </foreach>
    </insert>

    <insert id="insertSelective" parameterType="${data.packagePath}.${data.module}.bean.${data.className}">
        insert into ${data.tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
  		    <#list data.columns as c>
                <if test="${c.propertyName} != null and ${c.propertyName}!=''" >
					${c.columnName}<#if c_has_next>,</#if>
                </if>
			</#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
			<#list data.columns as c>
                <if test="${c.propertyName} != null and ${c.propertyName}!=''" >
					${start}${c.propertyName},jdbcType=${c.jdbcType}${end}<#if c_has_next>,</#if>
                </if>
			</#list>
        </trim>
    </insert>

    <delete id="delete" parameterType="${pkPropertyType}">
        delete from ${data.tableName} where ${pkColumnName}=${start}${pkProperty},jdbcType=${pkJdbcType}${end}
    </delete>

    <update id="update" parameterType="${data.packagePath}.${data.module}.bean.${data.className}">
        update ${data.tableName}
        <set>
			<#list data.columns as c>
				${c.columnName}=${start}${c.propertyName},jdbcType=${c.jdbcType}${end}<#if c_has_next>,</#if>
			</#list>
        </set>
        where
	${pkColumnName}=${start}${pkProperty},jdbcType=${pkJdbcType}${end}
    </update>

    <update id="updateSelectiveById" parameterType="${data.packagePath}.${data.module}.bean.${data.className}">
        update ${data.tableName}
        <set>
    		<#list data.columns as c>
                <if test="${c.propertyName} != null and ${c.propertyName}!=''" >
					${c.columnName}=${start}${c.propertyName},jdbcType=${c.jdbcType}${end}<#if c_has_next>,</#if>
                </if>
			</#list>
        </set>
        where
	${pkColumnName}=${start}${pkProperty},jdbcType=${pkJdbcType}${end}
    </update>

    <select id="selectById" parameterType="${pkPropertyType}" resultMap="BaseResultMap">
        select <include refid="Base_Column_List"/>
        from ${data.tableName}
        where ${pkColumnName} = ${start}${pkProperty},jdbcType=${pkJdbcType}${end}
    </select>

</mapper>