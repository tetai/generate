package ${dtoPath};

<#list table.fields as field>
<#if field.propertyType?index_of("BigDecimal")!=-1>
<#assign importBigDecimal=true/>
</#if>
<#if field.propertyType?index_of("Date")!=-1>
<#assign importDate=true/>
</#if>
</#list>

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
<#if importDate?exists>
import java.util.Date;
</#if>
<#if importBigDecimal?exists>
import java.math.BigDecimal;
</#if>

/**
 * @description:
 * @author: ${author}
 * @date: ${date}
 */
@Data
public class ${entity}DTO implements Serializable {

    private static final long serialVersionUID = #{serialVersionUID}L;

<#-- 循环属性名称 -->
<#list table.fields as field>

    @ApiModelProperty("${field.comment}")
    private ${field.propertyType} ${field.propertyName};
</#list>
}
