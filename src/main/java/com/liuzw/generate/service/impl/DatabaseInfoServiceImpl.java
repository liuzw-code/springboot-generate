package com.liuzw.generate.service.impl;


import com.github.pagehelper.PageHelper;
import com.liuzw.generate.bean.DatabaseInfoBean;
import com.liuzw.generate.bean.DatabaseInfoQueryBean;
import com.liuzw.generate.mapper.DatabaseInfoMapper;
import com.liuzw.generate.model.DatabaseInfoModel;
import com.liuzw.generate.service.DatabaseInfoService;
import com.liuzw.generate.utils.CopyDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * DatabaseInfoServiceImpl
 *
 * @author liuzw
 */
@Service
public class DatabaseInfoServiceImpl implements DatabaseInfoService {


    @Autowired
    private DatabaseInfoMapper databaseInfoMapper;


    @Override
    public List<DatabaseInfoModel> getList(DatabaseInfoQueryBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        Example example = new Example(DatabaseInfoModel.class);
        Example.Criteria createCriteria = example.createCriteria();
        if (StringUtils.isNotEmpty(bean.getName())) {
            createCriteria.andLike("name", "%" + bean.getName().trim() + "%");
        }
        if (StringUtils.isNotEmpty(bean.getDbUrl())) {
            createCriteria.andLike("dbUrl", "%" + bean.getDbUrl().trim() + "%");
        }
        return databaseInfoMapper.selectByExample(example);
    }


    @Override
    public List<DatabaseInfoModel> getList() {
        return databaseInfoMapper.selectAll();
    }

    @Override
    public DatabaseInfoModel getById(Long id) {
        return databaseInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean insert(DatabaseInfoBean bean) {
        DatabaseInfoModel model = CopyDataUtil.copyObject(bean, DatabaseInfoModel.class);
        model.setId(null);
        return databaseInfoMapper.insertSelective(model) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean update(DatabaseInfoBean bean) {
        DatabaseInfoModel model = CopyDataUtil.copyObject(bean, DatabaseInfoModel.class);
        return databaseInfoMapper.updateByPrimaryKeySelective(model) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean delete(Long id) {
        return databaseInfoMapper.deleteByPrimaryKey(id) > 0;
    }


}