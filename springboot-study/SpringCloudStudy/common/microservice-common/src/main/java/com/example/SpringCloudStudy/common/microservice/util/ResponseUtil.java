package com.example.SpringCloudStudy.common.microservice.util;

import com.example.SpringCloudStudy.common.microservice.pojo.Result;
import com.example.SpringCloudStudy.common.microservice.pojo.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class ResponseUtil {
    @Autowired
    ObjectMapper objectMapper;

    /**
     * 使用普通 HttpServletResponse 返回 JSON 格式的数据
     * @param response  /
     * @param resultCode    /
     */
    public void writeToJson(ServletResponse response, ResultCode resultCode) {
        response.setContentType("application/json;charset=utf-8");
        try {
            response
                    .getWriter()
                    .write(objectMapper.writeValueAsString(Result
                            .build(resultCode)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用普通 HttpServletResponse 返回 JSON 格式的数据
     * @param response  /
     * @param resultCode    /
     * @param data  /
     * @param <T>   /
     */
    public <T> void writeToJson(ServletResponse response, ResultCode resultCode, T data) {
        response.setContentType("application/json;charset=utf-8");
        try {
            response
                    .getWriter()
                    .write(objectMapper.writeValueAsString(Result
                            .build(resultCode, data)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
