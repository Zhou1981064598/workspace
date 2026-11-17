package com.example.SpringCloudStudy.microservice.rbac.handler;

import com.example.SpringCloudStudy.common.microservice.constant.SecurityConstant;
import com.example.SpringCloudStudy.common.microservice.pojo.ResultCode;
import com.example.SpringCloudStudy.common.microservice.util.JwtUtil;
import com.example.SpringCloudStudy.common.microservice.util.RedisUtil;
import com.example.SpringCloudStudy.common.microservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RbacAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        String jwtToken = JwtUtil.createJWT(authentication.getName());
        redisUtil.set(SecurityConstant.REDIS_TOKEN_KEY.concat(authentication.getName()), jwtToken, JwtUtil.JWT_TTL);
        redisUtil.set(SecurityConstant.REDIS_AUTHORIZATION_KEY.concat(authentication.getName()), authentication, JwtUtil.JWT_TTL);
        responseUtil.writeToJson(httpServletResponse, ResultCode.SUCCESS_LOGIN, jwtToken);
    }
}
