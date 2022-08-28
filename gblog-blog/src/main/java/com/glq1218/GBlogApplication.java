package com.glq1218;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: glq
 * @Data: 2022/8/28 下午12:26
 * @Description: TODO
 */
@SpringBootApplication
@MapperScan("com.glq1218.mapper")
public class GBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(GBlogApplication.class,args);
    }
}
