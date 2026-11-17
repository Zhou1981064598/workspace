package com.zhou.deliverysystem.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.zhou.deliverysystem.pojo.Result;
import com.zhou.deliverysystem.pojo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        String token=authentication.getName();
        httpServletResponse
                .getWriter()
                .write(objectMapper.writeValueAsString(Result.success(ResultCode.LOGIN_OUT_SUCCESS)));
    }
}
