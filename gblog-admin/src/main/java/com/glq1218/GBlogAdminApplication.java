package com.glq1218;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.glq1218.mapper")
public class GBlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(GBlogAdminApplication.class, args);
    }
}
