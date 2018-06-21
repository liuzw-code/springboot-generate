package ${data.packagePath}.${data.module}.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TABLE_NAME:(${data.tableName})
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ${data.className} {

<#list data.columns as c>

    /**
     * ${c.propertyCname?if_exists}
	 */
	private ${c.propertyType} ${c.propertyName};

</#list>	
	

}