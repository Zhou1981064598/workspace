package com.example.springbootstudy.config;

import com.example.springbootstudy.eneity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 目的是为了让spring来加载当前类
 */
@Configuration
public class BeanConfig {
    /**
     * 默认在spring bean工厂中注册ObjectMapper对象，并且去买(id=函数名)
     */
    private User user;

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
