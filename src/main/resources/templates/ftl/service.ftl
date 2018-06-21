package ${data.packagePath}.${data.module}.service;



import ${data.packagePath}.${data.module}.bean.${data.className}Bean;
import ${data.packagePath}.${data.module}.model.${data.className}Model;
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
	 * 返回分页列表信息
	 *
	 * @param bean    数据
 	 * @return       list<${data.className}Model>
    */
    List<${data.className}Model> getList(${data.className}QueryBean bean);

    /**
    * 根据id返回信息
    * @param id     id
    * @return       SysUserModel
    */
    ${data.className}Model getById(Long id);


    /**
    *增加
    *
    * @param bean   数据
    * @return      boolean
    */
    boolean insert(${data.className}Bean bean);


    /**
    * 根据ID删除
    *
    * @param id     id
    * @return       boolean
    */
    boolean delete(Long id);


    /**
    * 批量删除博客
    *
    * @param id     id
    * @return       Boolean
    */
    Boolean batchRemove(String id);

    /**
    *更新
    *
    * @param bean  数据
    * @return     boolean
    */
    boolean update(${data.className}Bean bean);

}