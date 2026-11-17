package com.zhou.deliverysystem.pojo;

/**
 * 错误状态码
 */
public enum ResultCode {
    SUCCESS(2000,"操作成功"),
    SUCCESS_REGISTER(2000,"注册成功"),
    LOGIN_OUT_SUCCESS(2000,"退出成功"),
    FAILURE_USERNAME_OR_PASSWORD_ERROR(2001,"用户名或者密码错误"),
    FAILURE_USERNAME_OR_PASSWORD_EMPTY(2002,"用户名或密码不能为空"),
    FAILURE_FORMAT_ERROR(2003,"用户名或者密码格式错误"),
    FAILURE_LOGIN_USERNAME_OR_PASSWORD_ERROR(2004,"用户名或者密码格式错误"),
    FAILURE_LOGIN_ERROR(2005,"登录失败"),
    FAILURE_LOGIN_NOT_PERMISSION(2006,"没有权限"),
    FAILURE_AUTHENTICATION_FAILURE(2007,"认证失败"),
    FAILURE_UPLOAD(2008,"文件上传失败"),
    FAiLURE_LOADING(2009,"还未登录,无法访问页面"),
    FAiLURE_REGISTER(2010,"注册失败"),
    FAILURE_LOGIN_USER_BAN(2009,"用户已被禁用,请联系管理员解封"),
    FAiLURE_REGISTER_EXIST_USERNAME(2011,"账号已存在，请重新输入"),
    FAiLURE_APPLY_EXIST(2010,"您已提交申请,不可重复申请"),
    SELECT_FAILURE(2010,"查询失败"),
    OPTION_FAILURE(2010,"操作失败"),
    APPLY_FAILURE(2010,"申请失败"),
    FAILURE_APPLY_RIDER_EXIST(2010,"您已是骑手，不必再申请");

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
