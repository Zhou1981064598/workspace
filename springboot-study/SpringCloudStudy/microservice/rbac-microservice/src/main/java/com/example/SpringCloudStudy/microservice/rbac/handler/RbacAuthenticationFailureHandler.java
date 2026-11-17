package com.example.SpringCloudStudy.microservice.rbac.handler;

import com.example.SpringCloudStudy.common.microservice.pojo.ResultCode;
import com.example.SpringCloudStudy.common.microservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RbacAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    ResponseUtil responseUtil;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        responseUtil.writeToJson(httpServletResponse, ResultCode.ERROR_LOGIN);
    }
}
