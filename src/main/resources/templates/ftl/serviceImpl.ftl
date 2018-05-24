package ${data.packagePath}.${data.module}.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${data.packagePath}.${data.module}.bean.${data.className};
import ${data.packagePath}.${data.module}.dao.${data.className}Mapper;
import ${data.packagePath}.${data.module}.service.${data.className}Service;
import java.util.List;

/**
 *  ${data.className}ServiceImpl
 *
 * @author liuzw
 */
 <#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->
 <#assign pkProperty=data.pkColumns[0].propertyName!"">
 <#assign pkPropertyType=data.pkColumns[0].propertyType!"">
@Service
public class ${data.className}ServiceImpl implements ${data.className}Service {
		
		
	@Autowired
	private ${data.className}Mapper ${data.varName}Mapper;


	@Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean insert(${data.className} ${data.varName}) {
        return ${data.varName}Mapper.insert(${data.varName}) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean insertSelective(${data.className} ${data.varName}) {
        return ${data.varName}Mapper.insertSelective(${data.varName}) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean insertBatch(List<${data.className}> ${data.varName}List) {
        return ${data.varName}Mapper.insertBatch(${data.varName}List) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean delete(${pkPropertyType} ${pkProperty}) {
    	return ${data.varName}Mapper.delete(${pkProperty}) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean deleteBatch(List<${pkPropertyType}> ${pkProperty}List) {
    	return ${data.varName}Mapper.deleteBatch(${pkProperty}List) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean update(${data.className} ${data.varName}) {
    	return ${data.varName}Mapper.update(${data.varName}) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean updateSelective(${data.className} ${data.varName}) {
    	return ${data.varName}Mapper.updateSelective(${data.varName}) > 0;
    }

    @Override
	public ${data.className} selectByPrimaryKey(${pkPropertyType} ${pkProperty}){
    	return ${data.varName}Mapper.selectByPrimaryKey(${pkProperty});
    }

    @Override
    public ${data.className} selectOne(${data.className} ${data.varName}) {
    	return ${data.varName}Mapper.selectOne(${data.varName});
    }

	@Override
	public List<${data.className}> selectList(${data.className} ${data.varName}) {
		return ${data.varName}Mapper.selectList(${data.varName});
	}

	@Override
	public List<${data.className}> selectAll() {
		return ${data.varName}Mapper.selectAll();
	}

}