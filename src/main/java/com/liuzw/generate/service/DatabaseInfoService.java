package com.liuzw.generate.service;


import com.liuzw.generate.bean.DatabaseInfoBean;
import com.liuzw.generate.bean.DatabaseInfoQueryBean;
import com.liuzw.generate.model.DatabaseInfoModel;

import java.util.List;


/**
 * interface DatabaseInfoservice
 *
 * @author liuzw
 */
public interface DatabaseInfoService {

    /**
     * 返回分页列表信息
     *
     * @param bean 数据
     * @return list<DatabaseInfoModel>
     */
    List<DatabaseInfoModel> getList(DatabaseInfoQueryBean bean);


    /**
     * 返回列表信息
     *
     * @return list<DatabaseInfoModel>
     */
    List<DatabaseInfoModel> getList();


    /**
     * 根据id返回信息
     *
     * @param id id
     * @return SysUserModel
     */
    DatabaseInfoModel getById(Long id);

    /**
     * 增加
     *
     * @param bean 数据
     * @return boolean
     */
    boolean insert(DatabaseInfoBean bean);

    /**
     * 根据ID删除
     *
     * @param id id
     * @return boolean
     */
    boolean delete(Long id);

    /**
     * 更新
     *
     * @param bean 数据
     * @return boolean
     */
    boolean update(DatabaseInfoBean bean);

}