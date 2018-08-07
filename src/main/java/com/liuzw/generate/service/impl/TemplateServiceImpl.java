package com.liuzw.generate.service.impl;


import com.github.pagehelper.PageHelper;
import com.liuzw.generate.bean.TemplateBean;
import com.liuzw.generate.bean.TemplateQueryBean;
import com.liuzw.generate.mapper.TemplateMapper;
import com.liuzw.generate.model.TemplateModel;
import com.liuzw.generate.service.TemplateService;
import com.liuzw.generate.utils.CopyDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TemplateServiceImpl
 *
 * @author liuzw
 */
@Service
public class TemplateServiceImpl implements TemplateService {


    @Autowired
    private TemplateMapper templateMapper;


    @Override
    public List<TemplateBean> getList(TemplateQueryBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        return templateMapper.getList(bean);
    }


    @Override
    public TemplateModel getById(Long id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean insert(TemplateBean bean) {
        TemplateModel model = CopyDataUtil.copyObject(bean, TemplateModel.class);
        model.setId(null);
        return templateMapper.insertSelective(model) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean update(TemplateBean bean) {
        TemplateModel model = CopyDataUtil.copyObject(bean, TemplateModel.class);
        return templateMapper.updateByPrimaryKeySelective(model) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean delete(Long id) {
        return templateMapper.deleteByPrimaryKey(id) > 0;
    }

}