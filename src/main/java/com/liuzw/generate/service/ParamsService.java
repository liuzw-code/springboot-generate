package com.liuzw.generate.service;


import com.liuzw.generate.bean.ParamsBean;
import com.liuzw.generate.bean.ParamsQueryBean;
import com.liuzw.generate.model.ParamsModel;

import java.util.List;


/**
 * interface Paramsservice
 *
 * @author liuzw
 */
public interface ParamsService {

    /**
     * 返回分页列表信息
     *
     * @param bean 数据
     * @return list<ParamsModel>
     */
    List<ParamsModel> getList(ParamsQueryBean bean);

    /**
     * 返回列表信息
     *
     * @return list<ParamsModel>
     */
    List<ParamsBean> getList();

    /**
     * 根据id返回信息
     *
     * @param id id
     * @return SysUserModel
     */
    ParamsModel getById(Long id);


    /**
     * 增加
     *
     * @param bean 数据
     * @return boolean
     */
    boolean insert(ParamsBean bean);


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
    boolean update(ParamsBean bean);

}