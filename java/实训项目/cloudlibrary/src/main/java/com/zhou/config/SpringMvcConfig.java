package com.zhou.config;

import com.zhou.interceptor.ResourceInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
@PropertySource("classpath:ignoreUrl.properties")
@ComponentScan("com.zhou.controller")//等同于<context:componet-scan base-package="com.zhou.controller"/>
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
    @Value("#{'${ignoreUrl}'.split(',')}")
    private List<String> ignoreUrl;
    @Bean
    public ResourceInterceptor resourceInterceptor(){
        return new ResourceInterceptor(ignoreUrl);
    }
    /**
     *   在拦截器注册类中添加自定义拦截器
     *   addPathPatterns():设置拦截路径
     *   excludePathPatterns():配置不拦截路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(resourceInterceptor()).addPathPatterns("/**").excludePathPatterns("css/**","/js/**","/image/**");
    }
    //开启对静态资源的访问
    //类似在SpringMVC配置文件中的<mvc:defalut-servlet-handler/>

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/admin/","jsp");
    }

}
/**
 * 该类配置了SpringMVC的相关信息，实现了WebMvcConfig接口，这样就可以在定制SpringMVC的配置，如添加自定义拦截器，静态资源访问和视图解析器等配置
 */