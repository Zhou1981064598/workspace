package com.example.SpringCloudStudy.microservice.rbac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

/**
 * 通用 Bean 配置类
 */
@Configuration
public class BeanConfigurer {
    /**
     * URI 和 路径 匹配
     * @return  /
     */
    @Bean
    public AntPathMatcher antPathMatcher(){
        return new AntPathMatcher();
    }
}
