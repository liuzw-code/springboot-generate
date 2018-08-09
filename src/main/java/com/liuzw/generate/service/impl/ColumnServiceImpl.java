package com.liuzw.generate.service.impl;

import com.github.pagehelper.PageHelper;
import com.liuzw.generate.bean.ColumnBean;
import com.liuzw.generate.bean.GenQueryBean;
import com.liuzw.generate.bean.TableBean;
import com.liuzw.generate.mapper.ColumnMapper;
import com.liuzw.generate.service.ColumnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述
 *
 * @author liuzw
 * @date 2018/8/6 20:29
 **/
@Slf4j
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnMapper columnMapper;

    @Override
    public List<ColumnBean> getTableAllColumns(List<String> tableNames) {
        return columnMapper.getTableAllColumns(tableNames);
    }

    @Override
    public List<ColumnBean> getTablePkColumns(List<String> tableNames) {
        return columnMapper.getTablePkColumns(tableNames);
    }

    @Override
    public List<TableBean> getList(GenQueryBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        return columnMapper.getList(bean.getTableName());
    }


}
