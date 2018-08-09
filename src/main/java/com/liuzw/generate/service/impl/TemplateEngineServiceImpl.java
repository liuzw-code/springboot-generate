package com.liuzw.generate.service.impl;


import com.github.pagehelper.PageHelper;
import com.liuzw.generate.bean.TemplateEngineBean;
import com.liuzw.generate.bean.TemplateEngineQueryBean;
import com.liuzw.generate.mapper.TemplateEngineMapper;
import com.liuzw.generate.model.TemplateEngineModel;
import com.liuzw.generate.service.TemplateEngineService;
import com.liuzw.generate.utils.CopyDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * TemplateEngineServiceImpl
 *
 * @author liuzw
 */
@Service
public class TemplateEngineServiceImpl implements TemplateEngineService {


    @Autowired
    private TemplateEngineMapper templateEngineMapper;


    @Override
    public List<TemplateEngineModel> getList(TemplateEngineQueryBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        Example example = new Example(TemplateEngineModel.class);
        Example.Criteria createCriteria = example.createCriteria();
        if (StringUtils.isNotEmpty(bean.getEngineName())) {
            createCriteria.andLike("engineName", "%" + bean.getEngineName().trim() + "%");
        }
        return templateEngineMapper.selectByExample(example);
    }


    @Override
    public List<TemplateEngineBean> getList() {
        return CopyDataUtil.copyList(templateEngineMapper.selectAll(), TemplateEngineBean.class);
    }


    @Override
    public TemplateEngineModel getById(Long id) {
        return templateEngineMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean insert(TemplateEngineBean bean) {
        TemplateEngineModel model = CopyDataUtil.copyObject(bean, TemplateEngineModel.class);
        model.setId(null);
        return templateEngineMapper.insertSelective(model) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean update(TemplateEngineBean bean) {
        TemplateEngineModel model = CopyDataUtil.copyObject(bean, TemplateEngineModel.class);
        return templateEngineMapper.updateByPrimaryKeySelective(model) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean delete(Long id) {
        return templateEngineMapper.deleteByPrimaryKey(id) > 0;
    }

}