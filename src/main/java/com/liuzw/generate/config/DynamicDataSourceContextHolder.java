package com.liuzw.generate.config;

/**
 * 动态数据源
 *
 * @author liuzw
 */

public class DynamicDataSourceContextHolder {

    /**
     * 维护每个线程的变量，以避免影响其他线程
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>() {

        /** * 将 default 数据源的 key 作为默认数据源的 key */
        @Override
        protected String initialValue() {
            return "default";
        }
    };


    /**
     * 设置当前数据源
     *
     * @param key 数据源的 key
     */
    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    /**
     * 获取数据源
     *
     * @return  数据源key
     */
    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置默认数据源
     */
    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

}
