<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" > 
<#assign primaryKeyName="">
<mapper namespace="${package}.dao.${module}.${className?cap_first}Dao" >
  <resultMap id="BaseResultMap" type="${package}.entity.${module}.${className?cap_first}" >
   <#list columns as column>
       <#if column_index==0>
           <#assign primaryKeyName="${column.columnName}">
          <id column="${column.dbName}" property="${column.columnName}" jdbcType="${column.dbType?upper_case}" />
       <#else>
           <result column="${column.dbName}" property="${column.columnName}" jdbcType="${column.dbType?upper_case}" />
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
         <if test="${column.columnName} != null">
			and ${column.dbName}=${"#{"?html}${column.columnName}${"}"?html}
		 </if> 
       </#list> 
  </sql>
  
  	<select id="getById" resultMap="BaseResultMap">
		select <include refid="baseColumns"/>
		from ${tableName}  where ${keyColumn.dbName} =${"#{"?html}${primaryKeyName}${"}"?html}
	</select>
	
	<delete id="getByIds">
	     select <include refid="baseColumns"/> from ${tableName}  where ${keyColumn.dbName} in
	     <foreach collection="array" item="item" index="index"  open="(" close=")" separator=",">
               ${"#{"?html}${"item}"?html},
         </foreach>  
	</delete>
	
	<select id="get" resultMap="BaseResultMap" parameterType="${package}.entity.${module}.${className?cap_first}">
		select <include refid="baseColumns"/>
		from ${tableName}  where 1=1 <include refid="whereSql"/> limit 1
	</select>
	
	<select id="findAllList" resultMap="BaseResultMap">
	     select <include refid="baseColumns"/> from ${tableName}
	</select>
	
	<select id="findList"  parameterType="${package}.entity.${module}.${className?cap_first}" resultMap="BaseResultMap">
	     select <include refid="baseColumns"/> from ${tableName} where 1=1 <include refid="whereSql"/> 
	</select> 
	
	<insert id="insert" useGeneratedKeys="true" keyProperty="${keyColumn.columnName}" parameterType="${package}.entity.${module}.${className?cap_first}">
       insert into ${tableName} 
		<trim prefix="(" suffix=")" suffixOverrides=","> 
		   <#list columns as column> 
             <if test="${column.columnName} != null">
				   ${column.dbName},
			 </if> 
           </#list> 
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides=",">
		   <#list columns as column> 
			  <if test="${column.columnName} != null">
				${"#{"?html}${column.columnName}${"}"?html},
			  </if>
		   </#list> 
		</trim>
    </insert>
    
    <update id="updateById" parameterType="${package}.entity.${module}.${className?cap_first}">
	    update ${tableName}  set
	     <trim suffixOverrides="," >
		    <#list columns as column> 
		       <#if column.columnName!=primaryKeyName>
				  <if test="${column.columnName} != null">
					 ${column.dbName}=${"#{"?html}${column.columnName}${"}"?html},
				  </if>
			   </#if>
		    </#list>  
		  </trim>
		 where ${keyColumn.dbName} =${"#{"?html}${primaryKeyName}${"}"?html}
	</update>
     
	
	<delete id="deleteById">
        delete from ${tableName} where ${keyColumn.dbName} =${"#{"?html}${primaryKeyName}${"}"?html}
    </delete>
    
     <delete id="deleteByIds">
        delete from ${tableName} where ${keyColumn.dbName} in 
        <foreach collection="array" item="item" index="index"  open="(" close=")" separator=",">
               ${"#{"?html}${"item}"?html},
        </foreach>  
	</delete>
    
    <delete id="delete" parameterType="${package}.entity.${module}.${className?cap_first}">
        delete from ${tableName} where 1=1 <include refid="whereSql"/> 
    </delete>
   
</mapper>