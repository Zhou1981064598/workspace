package com.example.demo.exception;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.enums.Code;
import com.example.demo.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception e) {
        ResultVO result = new ResultVO();
        e.printStackTrace();
        result.setFail(Code.UNKNOWN_ERROR.getCode(), e.getMessage());
        return result;
    }

    @ExceptionHandler(InvalidClaimException.class)
    public ResultVO handleInvalidClaimException(InvalidClaimException e) {
        e.printStackTrace();
        return new ResultVO(Code.USER_LOGIN_INVALID_TOKEN.getCode(), Code.USER_LOGIN_INVALID_TOKEN.getMessage());
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResultVO handleJWTVerificationException(JWTVerificationException e) {
        e.printStackTrace();
        return new ResultVO(Code.USER_LOGIN_INVALID_TOKEN.getCode(), Code.USER_LOGIN_INVALID_TOKEN.getMessage());
    }

    @ExceptionHandler(ValidationFailException.class)
    public ResultVO handleValidationFailException(ValidationFailException e) {
        e.printStackTrace();
        return new ResultVO(Code.ILLEGAL_PARAMETER.getCode(), e.getMessage());
    }

    @ExceptionHandler(ParseException.class)
    public ResultVO handleParseException(ParseException e) {
        e.printStackTrace();
        return new ResultVO(Code.ILLEGAL_PARAMETER.getCode(), e.getMessage());
    }

}
