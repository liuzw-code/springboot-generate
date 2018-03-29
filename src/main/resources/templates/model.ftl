package ${data.packagePath}.${data.module}.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * TABLE_NAME:(${data.tableName})
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="${data.tableName}")
public class ${data.className} {

<#list data.columns as c>
    /**
     * ${c.propertyCname?if_exists}
	 */
    <#if  data.pkColumns[0].columnName>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    @Column(name = "${c.propertyName}")
	private ${c.propertyType} ${c.propertyName};
</#list>	
	

}