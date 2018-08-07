package com.liuzw.generate.controller;


import com.liuzw.generate.bean.Page;
import com.liuzw.generate.utils.PageUtil;

import java.util.List;

/**
 * @author liuzw
 **/

public class BaseController {

    <T> Page<T> createPageInfo(List<T> list) {
        return new Page<>(list);
    }

    <T, V> Page<V> convertPageInfo(List<T> list, Class<V> clazz) {
        return copyPageList(list, clazz);
    }

    private <T, V> Page<V> copyPageList(List<T> list, Class<V> clazz) {
        if (list == null) {
            return null;
        } else {
            return PageUtil.getPageList(list, clazz);
        }
    }

}
