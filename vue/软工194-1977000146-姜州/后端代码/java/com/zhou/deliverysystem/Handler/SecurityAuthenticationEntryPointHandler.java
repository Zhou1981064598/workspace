package com.zhou.deliverysystem.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.zhou.deliverysystem.pojo.Result;
import com.zhou.deliverysystem.pojo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        if ("Full authentication is required to access this resource".equals(e.getMessage())){
            httpServletResponse
                    .getWriter()
                    .write(objectMapper.writeValueAsString(Result.failure(ResultCode.FAiLURE_LOADING)));
        }else if ("用户已被禁用,请联系管理员解封".equals(e.getMessage())){
            httpServletResponse
                    .getWriter()
                    .write(objectMapper.writeValueAsString(Result.failure(ResultCode.FAILURE_LOGIN_USER_BAN)));
        }else {
        httpServletResponse
                .getWriter()
                .write(objectMapper.writeValueAsString(Result.failure(2009,e.getMessage())));
    }}
}
