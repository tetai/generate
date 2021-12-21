package ${doPath};

<#list table.fields as field>
<#if field.propertyType?index_of("BigDecimal")!=-1>
<#assign importBigDecimal=true/>
</#if>
<#if field.propertyType?index_of("Date")!=-1>
<#assign importDate=true/>
</#if>
</#list>

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
<#if importDate?exists>
import java.util.Date;
</#if>
<#if importBigDecimal?exists>
import java.math.BigDecimal;
</#if>

/**
 * @description: 表名：${table.name}
 * @author: ${author}
 * @date: ${date}
 */
@Data
@TableName("${table.name}")
public class ${entity}DO {
<#-- 循环属性名称 -->
<#list table.fields as field>
    <#if field.keyIdentityFlag>
    @TableField("${field.name}")
    @TableId(type = IdType.AUTO)
    /**
     * ${field.comment}
     */
    <#else>
    /**
     * ${field.comment}
     */
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
}
