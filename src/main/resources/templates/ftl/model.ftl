package ${data.packagePath}.${data.module}.model<#if data.packageName??>.${data.packageName}</#if>;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * TABLE_NAME:(${data.tableName})
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="${data.tableName}")
public class ${data.className}Model {

<#list data.columns as c>

    /**
     * ${c.propertyCname?if_exists}
	 */
    <#if data.pkColumns?? && data.pkColumns[0].columnName == c.propertyName >
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    <#else>
     @Column(name = "${c.columnName}")
    </#if>
	private ${c.propertyType} ${c.propertyName};

</#list>	
	

}