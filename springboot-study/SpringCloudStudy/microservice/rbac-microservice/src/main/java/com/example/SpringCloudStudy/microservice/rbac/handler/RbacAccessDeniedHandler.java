package com.example.SpringCloudStudy.microservice.rbac.handler;

import com.example.SpringCloudStudy.common.microservice.pojo.ResultCode;
import com.example.SpringCloudStudy.common.microservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RbacAccessDeniedHandler implements AccessDeniedHandler {
    @Autowired
    ResponseUtil responseUtil;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        responseUtil.writeToJson(httpServletResponse, ResultCode.ERROR_FORBIDDEN);
    }
}
