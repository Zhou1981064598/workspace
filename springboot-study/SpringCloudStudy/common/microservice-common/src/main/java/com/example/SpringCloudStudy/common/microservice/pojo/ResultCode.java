package com.example.SpringCloudStudy.common.microservice.pojo;

public enum ResultCode {
    SUCCESS(ResultCode.SUCCESS_CODE, "操作成功"),
    ERROR(ResultCode.ERROR_CODE, "操作失败"),
    SUCCESS_LOGIN(ResultCode.SUCCESS_CODE, "登录成功"),
    SUCCESS_LOGOUT(ResultCode.SUCCESS_CODE, "退出成功"),
    ERROR_LOGOUT(ResultCode.SUCCESS_CODE, "未登录"),
    ERROR_LOGIN(2010, "登录失败"),
    ERROR_FORBIDDEN(2011, "没有权限"),
    ERROR_UNAUTHORIZATION(2012, "未经授权"),
    ERROR_PASSWORD_NOT_CHECK(2013, "密码不一致"),
    ERROR_PASSWORD_ERROR(2014, "密码错误"),
    ERROR_TOKEN_EXPIRE(2015, "登录已过期"),
    ERROR_AVATAR_UPLOAD_FAULER(2016, "头像上传失败"),
    ERROR_EDIT_ROLE_FAULER(2017, "修改用户角色失败"),


    ;


    public static final int SUCCESS_CODE = 2000;
    public static final int ERROR_CODE = 0;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

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
