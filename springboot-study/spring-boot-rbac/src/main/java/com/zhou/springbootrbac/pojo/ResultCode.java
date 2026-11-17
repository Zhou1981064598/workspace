package com.zhou.springbootrbac.pojo;

/**
 * 错误状态码
 */
public enum ResultCode {
    SUCCESS(2000,"操作成功"),
    LOGIN_OUT_SUCCESS(2000,"退出成功"),
    FAILURE_USERNAME_OR_PASSWORD_ERROR(2001,"用户名或者密码错误"),
    FAILURE_USERNAME_OR_PASSWORD_EMPTY(2002,"用户名或密码不能为空"),
    FAILURE_FORMAT_ERROR(2003,"用户名或者密码格式错误"),
    FAILURE_LOGIN_USERNAME_OR_PASSWORD_ERROR(2004,"用户名或者密码格式错误"),
    FAILURE_LOGIN_ERROR(2005,"登录失败"),
    FAILURE_LOGIN_NOT_PERMISSION(2006,"没有权限");



    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
