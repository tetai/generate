package ${entityPackage};

<#list table.fields as field>
<#if field.propertyType?index_of("BigDecimal")!=-1>
<#assign importBigDecimal=true/>
</#if>
<#if field.propertyType?index_of("Date")!=-1>
<#assign importDate=true/>
</#if>
</#list>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
<#if importDate?exists>
import java.util.Date;
</#if>
<#if importBigDecimal?exists>
import java.math.BigDecimal;
</#if>

/**
 * 表名：${table.name}
*/
@Data
@Table(name = "${table.name}")
public class ${entity}DO {
<#-- 循环属性名称 -->
<#list table.fields as field>
    <#if field.comment??>
    /**
     * <#if field.comment!="">${field.comment}<#else >主键</#if>
     */
    </#if>
    <#if field.keyIdentityFlag>
    @Id
    @Column(name = "`${field.name}`")
    @TableId(type = IdType.AUTO)
    /**
     * ${field.comment}
     */
    <#else>
    @Column(name = "`${field.name}`")
    /**
     * ${field.comment}
     */
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
}
