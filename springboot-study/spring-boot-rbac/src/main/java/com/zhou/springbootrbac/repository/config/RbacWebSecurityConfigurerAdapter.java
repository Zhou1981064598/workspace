package com.zhou.springbootrbac.repository.config;

import com.zhou.springbootrbac.filter.SecurityTokenProcessFilter;
import com.zhou.springbootrbac.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.annotation.Resource;

@Configuration
public class RbacWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsServiceImpl userDetailsService;

    @Resource(name = "bCryptPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Resource(name = "corsConfigurationSource")
    private CorsConfigurationSource corsConfigurationSource;

    @Resource(name = "securityAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Resource(name = "securityAuthenticationFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Resource(name = "securityAuthenticationEntryPointHandler")
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Resource(name = "securityAccessDeniedHandler")
    private AccessDeniedHandler accessDeniedHandler;

    @Resource(name = "securityTokenProcessFilter")
    private SecurityTokenProcessFilter securityTokenProcessFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf防伪造请求
        http.csrf(AbstractHttpConfigurer::disable);
        //使用表单登录
        http.formLogin(config -> config
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll());
        //跨域配置
        http.cors(config->config
                .configurationSource(corsConfigurationSource));
        http.exceptionHandling(config->config
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler));
        http.authorizeRequests(config->config
                .anyRequest().access("@securityPermissionEvaluatorHandler.hasPermission(authentication,null,null)"));
        http.addFilterBefore(securityTokenProcessFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
