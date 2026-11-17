package com.example.SpringCloudStudy.microservice.rbac.handler;


import com.example.SpringCloudStudy.common.microservice.pojo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    /**
     * 统一异常处理
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        e.printStackTrace();
        return Result.error();
    }
}
