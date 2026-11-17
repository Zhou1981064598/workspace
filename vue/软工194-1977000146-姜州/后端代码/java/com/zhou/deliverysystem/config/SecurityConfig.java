package com.zhou.deliverysystem.config;

import com.zhou.deliverysystem.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //权限验证处理器(没有权限)
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    //用户名或者密码格式错误处理器
    @Resource(name = "securityAuthenticationFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;

    //登录成功处理器
    @Resource(name = "securityAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    //退出成功处理器
    @Resource(name = "securityLogoutSuccessHandler")
    private LogoutSuccessHandler  logoutSuccessHandler;

    //格式错误
    @Resource(name = "securityAuthenticationEntryPointHandler")
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                .antMatchers("/user/register").anonymous()

                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        //把token校验过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //使用表单登录
        http.formLogin(config -> config
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll());
        //配置注销成功处理器
        http.logout()
                .logoutSuccessHandler(logoutSuccessHandler);

        http.exceptionHandling()
                //匿名用户访问无权限资源处理
                .authenticationEntryPoint(authenticationEntryPoint)
                //未授权处理
                .accessDeniedHandler(accessDeniedHandler);
        //允许跨域
        http.cors();

    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    AntPathMatcher antPathMatcher(){
        return new AntPathMatcher();
    }
}
