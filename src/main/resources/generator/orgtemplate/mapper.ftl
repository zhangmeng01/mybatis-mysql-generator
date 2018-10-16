<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" > 
<#assign primaryKeyName="">
<mapper namespace="${className?cap_first}" >
   <resultMap id="BaseResultMap" type="${package}.bean.${className?cap_first}" >
   <#list columns as column>
       <#if column_index==0>
          <#assign primaryKeyName="${column.columnName}">
          <id column="${column.dbName}" property="${column.columnName}" jdbcType="${column.dbType?upper_case}" />
       <#else>
          <#if (column.dbType?upper_case)=='DATETIME'>
            <result column="${column.dbName}" property="${column.columnName}" jdbcType="TIMESTAMP" />
          <#elseif ((column.dbType?upper_case)=='TEXT') || ((column.dbType?upper_case)=='LONGTEXT')>
             <result column="${column.dbName}" property="${column.columnName}" jdbcType="LONGVARCHAR" />
          <#else>
            <result column="${column.dbName}" property="${column.columnName}" jdbcType="${column.dbType?upper_case}" />
          </#if>
       </#if> 
   </#list> 
  </resultMap>
   
  <sql id="baseColumns">
      <#list columns as column>
         <#if column_index==0>${column.dbName}<#else>,${column.dbName}</#if>
      </#list>
  </sql>
  
  <sql id="whereSql">
      <#list columns as column>
       <#if (column.dataType?upper_case)=='STRING'> 
        <if test="${column.columnName} != null and ${column.columnName}!='' ">
         and ${column.dbName}=${"#{"?html}${column.columnName}${"}"?html}
		</if>
       <#else>
        <if test="${column.columnName} != null">
         and ${column.dbName}=${"#{"?html}${column.columnName}${"}"?html}
		</if> 
       </#if>
       </#list>
  </sql>
  
  	<select id="getById" resultMap="BaseResultMap">
		select <include refid="baseColumns"/>
		from ${tableName}  where ${keyColumn.dbName} =${"#{"?html}${primaryKeyName}${"}"?html}
	</select>
	
	<select id="getByIds" resultMap="BaseResultMap">
	     select <include refid="baseColumns"/> from ${tableName}  where ${keyColumn.dbName} in
	     <foreach collection="array" item="item" index="index"  open="(" close=")" separator=",">
               ${"#{"?html}${"item}"?html}
         </foreach>  
	</select>
	
	<select id="get" resultMap="BaseResultMap" parameterType="${package}.bean.${className?cap_first}">
		select <include refid="baseColumns"/>
		from ${tableName}  where 1=1 <include refid="whereSql"/> limit 1
	</select>
	
	<select id="findAllList" resultMap="BaseResultMap">
	     select <include refid="baseColumns"/> from ${tableName}
	</select>
	
	<select id="findList"  parameterType="${package}.bean.${className?cap_first}" resultMap="BaseResultMap">
	     select <include refid="baseColumns"/> from ${tableName} where 1=1 <include refid="whereSql"/>
	      <if test="orderBy != null and orderBy!='' ">
                order by ${r'${orderBy}'}  
            </if>
	</select>

	
	 <select id="getCount"  parameterType="${package}.bean.${className?cap_first}" resultType="java.lang.Integer">
         select count(1) from ${tableName} where 1=1 <include refid="whereSql"/>
      </select>
	
      <insert id="insert" useGeneratedKeys="true" keyProperty="${keyColumn.columnName}" parameterType="${package}.domain.${className?cap_first}">
       insert into ${tableName} 
		<trim prefix="(" suffix=")" suffixOverrides=","> 
		   <#list columns as column> 
		    <#if (column.dataType?upper_case)=='STRING'> 
		        <if test="${column.columnName} != null and ${column.columnName}!='' ">
		         ${column.dbName},
				</if>
		       <#else>
		        <if test="${column.columnName} != null">
		          ${column.dbName},
				</if> 
              </#if>
           </#list> 
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides=",">
		   <#list columns as column> 
		    <#if (column.dataType?upper_case)=='STRING'> 
		        <if test="${column.columnName} != null and ${column.columnName}!='' ">
		        ${"#{"?html}${column.columnName}${"}"?html},
				</if>
		       <#else>
		        <if test="${column.columnName} != null">
		         ${"#{"?html}${column.columnName}${"}"?html},
				</if> 
				</#if>
		   </#list> 
		</trim>
    </insert>
    
    <update id="updateById" parameterType="${package}.bean.${className?cap_first}">
	    update ${tableName}  set
	     <trim suffixOverrides="," >
		    <#list columns as column> 
		       <#if column.columnName!=primaryKeyName>
		        <#if (column.dataType?upper_case)=='STRING'> 
		        <if test="${column.columnName} != null and ${column.columnName}!='' ">
		       ${column.dbName}=${"#{"?html}${column.columnName}${"}"?html},
				</if>
		       <#else>
		        <if test="${column.columnName} != null">
		        ${column.dbName}=${"#{"?html}${column.columnName}${"}"?html},
				</if> 
			   </#if>
			  </#if>
		    </#list>  
		  </trim>
		 where ${keyColumn.dbName} =${"#{"?html}${primaryKeyName}${"}"?html}
	</update>

	 <update id="updateByIds">
          update ${tableName}  set
	     <trim suffixOverrides="," >
		    <#list columns as column>
		       <#if column.columnName!=primaryKeyName>
		         <#if (column.dataType?upper_case)=='STRING'> 
		        <if test="${"entity."?html}${column.columnName} != null and ${"entity."?html}${column.columnName} != '' ">
		        ${column.dbName}= ${r'#{entity.'}${column.columnName}${"}"?html},
				</if>
		       <#else>
		        <if test="${"entity."?html}${column.columnName} != null">
		        ${column.dbName}= ${r'#{entity.'}${column.columnName}${"}"?html},
				</if> 
			   </#if>
			   </#if>
		    </#list>
		  </trim>
		 where ${keyColumn.dbName} in
		  <foreach collection="ids" item="item" index="index"  open="(" close=")" separator=",">
               ${"#{"?html}${"item}"?html}
         </foreach>
	</update>
     
	
	<delete id="deleteById">
        delete from ${tableName} where ${keyColumn.dbName} =${"#{"?html}${primaryKeyName}${"}"?html}
    </delete>
    
     <delete id="deleteByIds">
        delete from ${tableName} where ${keyColumn.dbName} in 
        <foreach collection="array" item="item" index="index"  open="(" close=")" separator=",">
               ${"#{"?html}${"item}"?html}
        </foreach>  
	</delete>
    
    <delete id="delete" parameterType="${package}.bean.${className?cap_first}">
        delete from ${tableName} where 1=1 <include refid="whereSql"/> 
    </delete>
   
</mapper>