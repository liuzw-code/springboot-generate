package com.liuzw.generate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author liuzw
 */
@MapperScan("com.liuzw.generate.mapper")
@SpringBootApplication
public class GenerateApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GenerateApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GenerateApplication.class, args);
    }
}
