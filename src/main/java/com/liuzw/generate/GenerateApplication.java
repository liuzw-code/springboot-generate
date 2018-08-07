package com.liuzw.generate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author liuzw
 */
@MapperScan("com.liuzw.generate.mapper")
@SpringBootApplication
public class GenerateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerateApplication.class, args);
    }
}
