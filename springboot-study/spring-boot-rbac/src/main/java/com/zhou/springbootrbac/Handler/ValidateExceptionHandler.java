package com.zhou.springbootrbac.Handler;

import com.zhou.springbootrbac.pojo.Result;
import com.zhou.springbootrbac.pojo.ResultCode;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
public class ValidateExceptionHandler {
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result<String> loginExceptionHandler(BindException e){
        StringBuffer sb=new StringBuffer();
        for (ObjectError error: e.getAllErrors()){
                sb.append(error.getDefaultMessage()+"\n");
        }
        return Result.failure(ResultCode.FAILURE_FORMAT_ERROR,sb.toString());
    }
}
