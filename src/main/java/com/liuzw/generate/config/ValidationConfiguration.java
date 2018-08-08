package com.liuzw.generate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 参数校验
 *
 * @author liuzw
 * @date 2018/8/8 11:21
 **/
@Configuration
public class ValidationConfiguration  {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
