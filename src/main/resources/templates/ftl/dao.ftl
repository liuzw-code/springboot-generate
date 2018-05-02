package ${data.packagePath}.${data.module}.dao;


import ${data.packagePath}.${data.module}.bean.${data.className};
import java.util.List;

/**
 * interface ${data.className}Mapper
 *
 * @author liuzw
 */
 <#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->
 <#assign pkProperty=data.pkColumns[0].propertyName!"">
 <#assign pkPropertyType=data.pkColumns[0].propertyType!"">

public interface ${data.className}Mapper {

	   /**
        * 增加数据
        *
        * @param ${data.varName} 实体对象
        * @return 受影响的行数
        */
        Integer insert(${data.className} ${data.varName});

       /**
        * 增加数据（选择字段，策略插入）
        *
        * @param ${data.varName} 实体对象
        * @return 受影响的行数
        */
        Integer insertSelective(${data.className} ${data.varName});

       /**
        * 批量插入数据
        *
        * @param ${data.varName}List 实体对象
        * @return 受影响的行数
        */
        Integer insertBatch(List<${data.className}> ${data.varName}List);


       /**
        * 删除数据
        *
        * @param ${pkProperty}  主键${pkProperty}
        * @return 受影响的行数
        */
        Integer delete(${pkPropertyType} ${pkProperty});

       /**
        * 批量删除数据（根据ID）
        *
        * @param ${pkProperty}List 主键${pkProperty}列表
        * @return 受影响的行数
        */
        Integer deleteBatch(List<${pkPropertyType}> ${pkProperty}List);

       /**
        * 更新数据
        *
        * @param ${data.varName} 实体对象
        * @return 受影响的行数
        */
        Integer update(${data.className} ${data.varName});

       /**
        * 更新数据（选择字段，策略更新）
        *
        * @param ${data.varName} 实体对象
        * @return 受影响的行数
        */
        Integer updateSelective(${data.className} ${data.varName});

       /**
        * 根据主键查询
        *
        * @param  ${pkProperty} 主键
        * @return 受影响的行数
        */
       ${data.className} selectByPrimaryKey(${pkPropertyType} ${pkProperty});

       /**
        * 根据主键查询
        *
        * @param  ${data.varName} 实体对象
        * @return 受影响的行数
        */
	   ${data.className} selectOne(${data.className} ${data.varName});

       /**
        * 查询数据
        *
        * @param  ${data.varName} 实体对象
        * @return List<${data.className}>
        */
        List<${data.className}> selectList(${data.className} ${data.varName});

       /**
        * 查询所有数据
        *
        * @return List<${data.className}>
        */
        List<${data.className}> selectAll();


}