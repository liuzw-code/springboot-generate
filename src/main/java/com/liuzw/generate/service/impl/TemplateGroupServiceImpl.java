package com.liuzw.generate.service.impl;


import com.github.pagehelper.PageHelper;
import com.liuzw.generate.bean.TemplateGroupBean;
import com.liuzw.generate.bean.TemplateGroupQueryBean;
import com.liuzw.generate.mapper.TemplateGroupMapper;
import com.liuzw.generate.model.TemplateGroupModel;
import com.liuzw.generate.service.TemplateGroupService;
import com.liuzw.generate.utils.CopyDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * TemplateGroupServiceImpl
 *
 * @author liuzw
 */
@Service
public class TemplateGroupServiceImpl implements TemplateGroupService {


    @Autowired
    private TemplateGroupMapper templateGroupMapper;


    @Override
    public List<TemplateGroupModel> getList(TemplateGroupQueryBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        Example example = new Example(TemplateGroupModel.class);
        Example.Criteria createCriteria = example.createCriteria();
        if (StringUtils.isNotEmpty(bean.getGroupName())) {
            createCriteria.andLike("groupName", "%" + bean.getGroupName().trim() + "%");
        }
        if (StringUtils.isNotEmpty(bean.getGroupDesc())) {
            createCriteria.andLike("groupDesc", "%" + bean.getGroupDesc().trim() + "%");
        }
        return templateGroupMapper.selectByExample(example);
    }


    @Override
    public List<TemplateGroupBean> getList() {
        return CopyDataUtil.copyList(templateGroupMapper.selectAll(), TemplateGroupBean.class);
    }


    @Override
    public TemplateGroupModel getById(Long id) {
        return templateGroupMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean insert(TemplateGroupBean bean) {
        TemplateGroupModel model = CopyDataUtil.copyObject(bean, TemplateGroupModel.class);
        model.setId(null);
        return templateGroupMapper.insertSelective(model) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean update(TemplateGroupBean bean) {
        TemplateGroupModel model = CopyDataUtil.copyObject(bean, TemplateGroupModel.class);
        return templateGroupMapper.updateByPrimaryKeySelective(model) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean delete(Long id) {
        return templateGroupMapper.deleteByPrimaryKey(id) > 0;
    }

}