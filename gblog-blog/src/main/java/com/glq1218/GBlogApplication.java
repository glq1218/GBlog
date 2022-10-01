package com.glq1218;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: glq
 * @Data: 2022/8/28 下午12:26
 * @Description: TODO
 */
@SpringBootApplication
@MapperScan("com.glq1218.mapper")
// 开启定时任务
@EnableScheduling
// 开启swagger
@EnableSwagger2
public class GBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(GBlogApplication.class,args);
    }
}
