package com.example.SpringCloudStudy.microservice.rbac.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private WebMvcProperties webMvcProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File path = new File(webMvcProperties.getResource().getLocation()
                .concat(webMvcProperties.getResource().getDir()));
        if (!path.exists()) {
            path.mkdirs();
        }

        registry.addResourceHandler(webMvcProperties.getResource().getMapping())
                .addResourceLocations("classpath:/", "file:"
                        .concat(webMvcProperties.getResource().getLocation())
                        .concat(webMvcProperties.getResource().getDir()))
                // .setCacheControl(CacheControl.maxAge(Duration.ofDays(30)));
                .setCacheControl(CacheControl.noCache());

        String sourcePath = "静态服务器路径=[file:"
                .concat(webMvcProperties.getResource().getLocation())
                .concat(webMvcProperties.getResource().getDir())
                .concat(",")
                .concat("classpath:/]");
        log.info(sourcePath);
    }

    /**
     * 开启 tomcat 默认servlet 处理静态资源
     *
     * @param configurer /
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable("defaultServletName");
    }
}