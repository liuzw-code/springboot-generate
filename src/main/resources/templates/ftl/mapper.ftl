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

    <sql id="Base_Where">
        <where>
        <#list data.columns as c>
            <if test="${c.propertyName} != null and ${c.propertyName} !=''" >
               and ${c.columnName} = ${start}${c.propertyName},jdbcType=${c.jdbcType}${end}<#if c_has_next>,</#if>
            </if>
        </#list>
        </where>
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

    <insert id="insertSelective" parameterType="${data.packagePath}.${data.module}.bean.${data.className}">
        insert into ${data.tableName}(
         <#list data.columns as c>
             ${c.columnName}<#if c_has_next>,</#if>
         </#list>
        )
        <trim prefix="values (" suffix=")" suffixOverrides="," >
        <#list data.columns as c>
            <if test="${c.propertyName} != null and ${c.propertyName}!=''" >
                ${start}${c.propertyName},jdbcType=${c.jdbcType}${end}<#if c_has_next>,</#if>
            </if>
        </#list>
        </trim>
    </insert>

    <insert id="insertBatch" parameterType="${data.packagePath}.${data.module}.bean.${data.className}">
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

    <delete id="delete" parameterType="${pkPropertyType}">
        delete from ${data.tableName} where ${pkColumnName}=${start}${pkProperty},jdbcType=${pkJdbcType}${end}
    </delete>

    <delete id="deleteBatch" parameterType="${pkPropertyType}">
        delete from ${data.tableName}
        where ${pkColumnName} in
        <foreach collection="${pkProperty}List" item="bean" separator="," open="(" close=")">
            ${start}bean.${pkProperty},jdbcType=${pkJdbcType}${end}
        </foreach>
    </delete>

    <update id="update" parameterType="${data.packagePath}.${data.module}.bean.${data.className}">
        update ${data.tableName}
        <set>
        <#list data.columns as c>
            ${c.columnName}=${start}${c.propertyName},jdbcType=${c.jdbcType}${end}<#if c_has_next>,</#if>
        </#list>
        </set>
        where ${pkColumnName}=${start}${pkProperty},jdbcType=${pkJdbcType}${end}
    </update>

    <update id="updateSelective" parameterType="${data.packagePath}.${data.module}.bean.${data.className}">
        update ${data.tableName}
        <set>
        <#list data.columns as c>
            <if test="${c.propertyName} != null and ${c.propertyName} != ''" >
                ${c.columnName}=${start}${c.propertyName},jdbcType=${c.jdbcType}${end}<#if c_has_next>,</#if>
            </if>
        </#list>
        </set>
        where ${pkColumnName}=${start}${pkProperty},jdbcType=${pkJdbcType}${end}
    </update>

    <select id="selectByPrimaryKey" parameterType="${pkPropertyType}" resultMap="BaseResultMap">
        select <include refid="Base_Column_List"/>
        from ${data.tableName}
        where ${pkColumnName} = ${start}${pkProperty},jdbcType=${pkJdbcType}${end}
    </select>

    <select id="selectOne" parameterType="${data.packagePath}.${data.module}.bean.${data.className}" resultMap="BaseResultMap">
        select <include refid="Base_Column_List"/>
        from ${data.tableName}
        <include refid="Base_Where"/>
    </select>

    <select id="selectList" parameterType="${data.packagePath}.${data.module}.bean.${data.className}" resultMap="BaseResultMap">
        select <include refid="Base_Column_List"/>
        from ${data.tableName}
        <include refid="Base_Where"/>
    </select>

    <select id="selectAll" resultMap="BaseResultMap">
        select <include refid="Base_Column_List"/>
        from ${data.tableName}
    </select>

</mapper>