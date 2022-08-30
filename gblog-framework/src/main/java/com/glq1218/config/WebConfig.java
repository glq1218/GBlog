package com.glq1218.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: glq
 * @Data: 2022/8/28 下午3:30
 * @Description: TODO
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                // 是否永续Cookie
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    /**
     * 将FastJsonHttpMessageConverter包装成HttpMessageConverter对象，并用其参与消息转换器管道
     * 列表HttpMessageConverters的初始化和Bean的生成，该方式会将得到的HttpMessageConverters中
     * 的httpMessageConverter作为生成消息转换器管道列表的初始数据(从源码得知会早于默认转换器)，
     * 因此也会早于通过实现configureMessageConverters接口向消息转换器管道列表追加转换器的方式
     *
     * @return 最终版的Http消息转换器列表对象
     */
    @Bean
    public HttpMessageConverters fastJsonConverters() {
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> httpMessageConverter = fastJsonConverter;
        return new HttpMessageConverters(httpMessageConverter);
    }
}