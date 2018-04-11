package ${data.packagePath}.${data.module}.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ${data.packagePath}.${data.module}.dao.${data.className}Mapper;
import ${data.packagePath}.${data.module}.bean.${data.className};
import ${data.packagePath}.${data.module}.service.${data.className}Service;

/**
 *  ${data.className}ServiceImpl
 *
 * @author liuzw
 */
 <#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->
 <#assign pkProperty=data.pkColumns[0].propertyName!"">
 <#assign pkPropertyType=data.pkColumns[0].propertyType!"">
@Service("${data.varName}Service")
public class ${data.className}ServiceImpl  implements ${data.className}Service {
		
		
	@Autowired
	private ${data.className}Mapper ${data.varName}Mapper;

	@Override
	public void insert(${data.className} ${data.varName}) {
		${data.varName}Mapper.insert(${data.varName});
	}

	@Override
	public void insertSelective(${data.className} ${data.varName}) {
		${data.varName}Mapper.insertSelective(${data.varName});
		
	}

	@Override
	public void delete(${pkPropertyType} ${pkProperty}) {
		${data.varName}Mapper.delete(${pkProperty});
	}

	@Override
	public void updateSelectiveById(${data.className} ${data.varName}) {
		${data.varName}Mapper.updateSelectiveById(${data.varName});
		
	}

	@Override
	public void update(${data.className} ${data.varName}) {
		${data.varName}Mapper.update(${data.varName});
		
	}

	@Override
	public ${data.className} findById(${pkPropertyType} ${pkProperty}) {
		return ${data.varName}Mapper.findById(${pkProperty});
	}

}