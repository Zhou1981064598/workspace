package com.example.SpringCloudStudy.microservice.rbac.filter;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.SpringCloudStudy.common.microservice.constant.SecurityConstant;
import com.example.SpringCloudStudy.common.microservice.pojo.ResultCode;
import com.example.SpringCloudStudy.common.microservice.util.JwtUtil;
import com.example.SpringCloudStudy.common.microservice.util.RedisUtil;
import com.example.SpringCloudStudy.common.microservice.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Token 认证拦截器
 */
@Component
public class SecurityTokenProcessFilter extends GenericFilterBean {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取请求头中 token
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestToken = request.getHeader(SecurityConstant.REQUEST_HEADER_AUTHORIZATION_KEY);
        if (StringUtils.isNotEmpty(requestToken)) {
            // 解析 token 并且提取 用户名
            try {
                Claims claims = JwtUtil.parseJWT(requestToken);
                String username = claims.getSubject();
                if (ObjectUtils.isNotEmpty(username) && redisUtil.hasKey(SecurityConstant.REDIS_TOKEN_KEY.concat(username))) {
                    // redis 中的 token
                    Object redisToken = redisUtil.get(SecurityConstant.REDIS_TOKEN_KEY.concat(username));
                    if (redisToken.equals(requestToken)) {
                        // 获取 redis 中的 authentication
                        Object redisAuthentication = redisUtil.get(SecurityConstant.REDIS_AUTHORIZATION_KEY.concat(username));
                        if (redisAuthentication instanceof Authentication) {
                            Authentication authentication = (Authentication) redisAuthentication;
                            // 表示登录成功，因为 security 只要 SecurityContext 中存在 认证标识 就表示认证成功
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                } else {
                    responseUtil.writeToJson(servletResponse, ResultCode.ERROR_TOKEN_EXPIRE);
                    return;
                }
            } catch (Exception e) {
                // 只要抛出异常，就说明 token 无效或者过期
                e.printStackTrace();
                responseUtil.writeToJson(servletResponse, ResultCode.ERROR_TOKEN_EXPIRE);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}