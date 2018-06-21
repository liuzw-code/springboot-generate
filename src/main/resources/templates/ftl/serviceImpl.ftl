package ${data.packagePath}.${data.module}.service.impl;



import com.github.pagehelper.PageHelper;
import ${data.packagePath}.${data.module}.bean.${data.className}Bean;
import ${data.packagePath}.${data.module}.bean.${data.className}QueryBean;
import ${data.packagePath}.${data.module}.model.${data.className}Model;
import ${data.packagePath}.${data.module}.mapper.${data.className}Mapper;
import ${data.packagePath}.${data.module}.service.${data.className}Service;
import com.liuzw.blog.utils.CopyDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<${data.className}Model> getList(${data.className}QueryBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        Example example = new Example(${data.className}Model.class);
        Example.Criteria createCriteria = example.createCriteria();
//        if (StringUtils.isNotEmpty(bean.getTitle())) {
//            createCriteria.andLike("title", "%" + bean.getTitle() + "%");
//        }
//        example.setOrderByClause("create_date asc");
        return ${data.varName}Mapper.selectByExample(example);
    }


    @Override
    public ${data.className}Model getById(Long id) {
        return ${data.varName}Mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
    rollbackFor = {RuntimeException.class, Exception.class})
    public boolean insert(${data.className}Bean bean) {
        ${data.className}Model model = CopyDataUtil.copyObject(bean, ${data.className}Model.class);
        model.setId(null);
        return ${data.varName}Mapper.insertSelective(model) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
    rollbackFor = {RuntimeException.class, Exception.class})
    public boolean update(${data.className}Bean bean) {
    ${data.className}Model model = CopyDataUtil.copyObject(bean, ${data.className}Model.class);
        return ${data.varName}Mapper.updateByPrimaryKeySelective(model) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
    rollbackFor = {RuntimeException.class, Exception.class})
    public boolean delete(Long id) {
        return ${data.varName}Mapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
    rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean batchRemove(String ids) {
        String[] str = ids.split(",");
        List<Long> list = Arrays.stream(str).map(Long::valueOf).collect(Collectors.toList());
        return ${data.varName}Mapper.batchRemove(list) > 0;
    }

}