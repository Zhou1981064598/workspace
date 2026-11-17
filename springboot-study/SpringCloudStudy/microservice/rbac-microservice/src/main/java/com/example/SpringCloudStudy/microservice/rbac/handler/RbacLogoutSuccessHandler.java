package com.example.SpringCloudStudy.microservice.rbac.handler;


import com.example.SpringCloudStudy.common.microservice.constant.SecurityConstant;
import com.example.SpringCloudStudy.common.microservice.pojo.ResultCode;
import com.example.SpringCloudStudy.common.microservice.util.RedisUtil;
import com.example.SpringCloudStudy.common.microservice.util.ResponseUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RbacLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private ResponseUtil responseUtil;

    @SneakyThrows
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (ObjectUtils.isEmpty(authentication)) {
            responseUtil.writeToJson(httpServletResponse, ResultCode.SUCCESS_LOGOUT);
        }else {
            redisUtil.delete(SecurityConstant.REDIS_TOKEN_KEY.concat(authentication.getName()));
            redisUtil.delete(SecurityConstant.REDIS_AUTHORIZATION_KEY.concat(authentication.getName()));
            responseUtil.writeToJson(httpServletResponse, ResultCode.SUCCESS_LOGOUT);
        }
    }
}