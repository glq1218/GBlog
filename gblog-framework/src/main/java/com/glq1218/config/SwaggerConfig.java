package com.glq1218.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String ARTICLE = "文章";
    public static final String TAG_2 = "tag2";
    public static final String TAG_3 = "tag3";

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.glq1218.controller")).build()
                .apiInfo(apiInfo())
                .tags(new Tag(ARTICLE, "文章相关接口"))
                .tags(new Tag(TAG_2, "Tag 2 description."))
                .tags(new Tag(TAG_3, "Tag 3 description."));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("My API").version("1.0.0").build();
    }
}