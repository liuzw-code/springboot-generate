package com.liuzw.generate.aop;

import java.lang.annotation.*;


/**
 * 主要用户切换当前数据源
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
}
