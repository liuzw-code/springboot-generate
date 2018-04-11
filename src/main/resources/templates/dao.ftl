package ${data.packagePath}.${data.module}.dao;


import com.sinter.common.persistence.MyBatisDao;

import ${data.packagePath}.${data.module}.bean.${data.className}Model;

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
	 	 * 增加
	 	 *
	 	 * @param ${data.varName}
	 	 **/
	 	void insert(${data.className} ${data.varName});

		/**
	 	 * 增加Selective
	     *
	 	 * @param ${data.varName}
	 	 **/
	    void insertSelective(${data.className} ${data.varName});

	    /**
	 	 * 删除
	     *
	 	 * @param ${pkProperty}
	 	 **/
	    void delete(${pkPropertyType} ${pkProperty});

	    /**
	 	 * 更新Selective
	     *
	 	 * @param ${data.varName}
	 	 **/
	    void updateSelectiveById(${data.className} ${data.varName});

	    /**
	 	 * 更新
	     *
	 	 * @param ${data.varName}
	 	 **/
	    void update(${data.className} ${data.varName});

	    /**
	 	 * 根据主键查询
         *
	 	 * @param ${pkProperty}
	 	 **/
       ${data.className} findById(${pkPropertyType} ${pkProperty});


}