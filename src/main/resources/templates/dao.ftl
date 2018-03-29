package ${data.packagePath}.${data.module}.dao;

import java.util.List;
import java.util.Map;
import com.sinter.common.persistence.MyBatisDao;

import ${data.packagePath}.${data.module}.model.${data.className}Model;

/**
 * interface ${data.className}Mapper
 *
 */
 <#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->
 <#assign pkProperty=data.pkColumns[0].propertyName!"">
 <#assign pkPropertyType=data.pkColumns[0].propertyType!"">

@MyBatisDao
public interface ${data.className}Mapper extends MyMapper<${data.className}Model>{
		


}