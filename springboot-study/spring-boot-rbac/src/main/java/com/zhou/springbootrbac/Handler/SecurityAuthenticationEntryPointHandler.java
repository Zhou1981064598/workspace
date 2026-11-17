package com.zhou.springbootrbac.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhou.springbootrbac.pojo.Result;
import com.zhou.springbootrbac.pojo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse
                .getWriter()
                .write(objectMapper.writeValueAsString(Result.failure(ResultCode.FAILURE_LOGIN_ERROR)));
    }
}
