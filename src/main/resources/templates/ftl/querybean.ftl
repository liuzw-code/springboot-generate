package ${data.packagePath}.${data.module}.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询使用
 *
 * @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ${data.className} extends BasePage {

<#list data.columns as c>

    /**
     * ${c.propertyCname?if_exists}
	 */
	private ${c.propertyType} ${c.propertyName};

</#list>	
	

}