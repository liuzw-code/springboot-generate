package com.liuzw.generate.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 对象复制
 *
 * @author liuzw
 */
@Slf4j
public class CopyDataUtil {

    private CopyDataUtil(){}

    public static <T, V> V copyObject(T source, Class<V> clazz) {
        if (source == null) {
            return null;
        }
        V newObj = null;
        try {
            newObj = clazz.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, newObj);
        } catch (InstantiationException | IllegalAccessException e) {
           log.error("", e);
        }
        return newObj;
    }

    public static <T, V> List<V> copyList(List<T> list, Class<V> clazz) {
        List<V> data = new ArrayList<>();
        if (list != null) {
            try {
                for (T obj : list) {
                    V dto = clazz.newInstance();
                    org.springframework.beans.BeanUtils.copyProperties(obj, dto);
                    data.add(dto);
                }
            } catch (IllegalAccessException | InstantiationException e) {
                log.error("", e);
            }
            return data;
        } else {
            return Collections.emptyList();
        }
    }

}
