package ${data.packagePath}.${data.module}.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TABLE_NAME:(${data.tableName})
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ${data.className}Dto {

<#list data.columns as c>
    /**
     * ${c.propertyCname?if_exists}
	 */
	private ${c.propertyType} ${c.propertyName};
</#list>	
	

}