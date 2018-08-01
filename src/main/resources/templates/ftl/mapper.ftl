package ${data.packagePath}.${data.module}.mapper;


import ${data.packagePath}.${data.module}.model.${data.className}Model;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * interface ${data.className}Mapper
 *
 * @author liuzw
 */
 <#--一般一个表就定义一个主键,如果属性值存在,就是下标为0的值,不存在就是空值-->
 <#assign pkProperty=data.pkColumns[0].propertyName!"">
 <#assign pkPropertyType=data.pkColumns[0].propertyType!"">

public interface ${data.className}Mapper extends Mapper<${data.className}Model>, MySqlMapper<${data.className}Model> {

    /**
    * 批量删除
    *
    * @param ids     id
    * @return       Integer
    */
    Integer batchRemove(@Param("ids") List<Long> ids);
}