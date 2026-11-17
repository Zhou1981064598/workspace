package com.example.SpringCloudStudy.microservice.rbac.config;


import com.example.SpringCloudStudy.microservice.rbac.filter.SecurityTokenProcessFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * Security 相关配置
 */
@Configuration
@EnableWebSecurity
public class RbacWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    @Autowired
    SecurityTokenProcessFilter securityTokenProcessFilter;

    /**
     * Security 加密方式
     * @return  /
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * SpringSecurity 跨域设置
     */
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Security 配置
     * @param http  /
     * @throws Exception    /
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单登录配置
        http.formLogin(config -> config
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
        );

        // 退出登录配置
        http.logout(config -> config
                .logoutSuccessHandler(logoutSuccessHandler)
        );

        // 跨域配置
        http.cors(config -> config
                .configurationSource(corsConfigurationSource())
        );

        // 伪造登录配置
        http.csrf(AbstractHttpConfigurer::disable);

        // 认证异常处理配置
        http.exceptionHandling(config -> config
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );

        // 请求权限规则配置
        http.authorizeRequests(config -> config
                // swagger 文档
                // http://localhost:8888/swagger-ui/index.html
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/*/api-docs").permitAll()
                // druid 监控
                // http://localhost:8888/druid/index.html
                .antMatchers("/druid/**").permitAll()
                .anyRequest().access("@rbacPermissionEvaluatorHandler.hasPermission(authentication,null,null)")
        );

        // Token 认证过滤器
        http.addFilterBefore(securityTokenProcessFilter, UsernamePasswordAuthenticationFilter.class);
    }
}