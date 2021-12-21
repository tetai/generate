<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPath}.${entity}Mapper">

    <resultMap id="BaseResultMap" type="${doPath}.${entity}DO">
<#list table.fields as field>
    <#if field.keyIdentityFlag>
        <id column="${field.name}" jdbcType="${field.type}" property="${field.propertyName}" />
    <#else>
        <result column="${field.name}" jdbcType="${field.type}" property="${field.propertyName}" />
    </#if>
</#list>
    </resultMap>

</mapper>
