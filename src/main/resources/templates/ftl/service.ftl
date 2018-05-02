package ${data.packagePath}.${data.module}.service;



import ${data.packagePath}.${data.module}.bean.${data.className};
import java.util.List;


/**
 * interface ${data.className}service
 *
 * @author liuzw
 */
 <#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->
 <#assign pkProperty=data.pkColumns[0].propertyName!"">
 <#assign pkPropertyType=data.pkColumns[0].propertyType!"">
public interface ${data.className}Service {

       /**
        * 增加数据
        *
        * @param ${data.varName} 实体对象
        * @return Boolean
        */
        Boolean insert(${data.className} ${data.varName});

       /**
        * 增加数据（选择字段，策略插入）
        *
        * @param ${data.varName} 实体对象
        * @return Boolean
        */
        Boolean insertSelective(${data.className} ${data.varName});

       /**
        * 批量插入数据
        *
        * @param ${data.varName}List 实体对象
        * @return Boolean
        */
        Boolean insertBatch(List<${data.className}> ${data.varName}List);


       /**
        * 删除数据
        *
        * @param ${pkProperty}  主键${pkProperty}
        * @return Boolean
        */
        Boolean delete(${pkPropertyType} ${pkProperty});

       /**
        * 批量删除数据（根据ID）
        *
        * @param ${pkProperty}List 主键${pkProperty}列表
        * @return Boolean
        */
        Boolean deleteBatch(List<${pkPropertyType}> ${pkProperty}List);

       /**
        * 更新bean中属性不为空的数据
        *
        * @param ${data.varName} 实体对象
        * @return Boolean
        */
        Boolean update(${data.className} ${data.varName});

       /**
        * 更新数据（选择字段，策略更新）
        *
        * @param ${data.varName} 实体对象
        * @return Boolean
        */
        Boolean updateSelective(${data.className} ${data.varName});

       /**
        * 根据主键查询
        *
        * @param  ${pkProperty} 主键
        * @return Boolean
        */
        ${data.className} selectByPrimaryKey(${pkPropertyType} ${pkProperty});

       /**
        * 根据主键查询
        *
        * @param  ${data.varName} 实体对象
        * @return Boolean
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