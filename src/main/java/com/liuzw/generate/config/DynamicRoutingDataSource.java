package com.liuzw.generate.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * 动态切换数据源
 *
 * @author liuzw
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private static DynamicRoutingDataSource instance;

    private static final byte[] LOCK = new byte[0];


    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 掉哪里获取对象
     *
     * @return DynamicRoutingDataSource
     */
    public static synchronized DynamicRoutingDataSource getInstance(){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = new DynamicRoutingDataSource();
                }
            }
        }
        return instance;
    }

    /**
     * 根据Key获取数据源的信息
     *
     * @return 数据源key
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
