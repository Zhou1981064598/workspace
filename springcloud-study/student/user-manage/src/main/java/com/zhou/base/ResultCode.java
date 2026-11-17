package com.zhou.base;

public enum ResultCode {
    SUCCESS(0,"成功"),
    //    参数错误：1000-1999
    PARAM_IS_INVALID(1001,"参数无效"),
    PARAM_IS_BLANK(1002,"参数为空"),
    PARAM_TYPE_BIND_ERROR(1003,"参数类型错误"),
    PARAM_NOT_COMPLETE(1004,"参数缺失"),
    //    用户错误：2001-2999
    USER_NOT_LOGIN_IN(2001,"用户未登录，访问的路径需要验证，请登录"),
    USER_LOGIN_ERROR(2002,"账号不存在或者密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003,"账号已被禁用"),
    USER_NOT_EXIST(2004,"用户不存在"),
    USER_HAS_EXIST(2005,"用户已存在"),
    USER_UPD_PWD(2006,"密码更新失败"),
    USER_PWD_ERROR(2007,"原密码输入不正确"),
    STU_NOT_EXIST(3001,"学生不存在"),
    SCORE_HAS_EXIST(3002,"成绩已经录入，请勿重复执行");

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer showCode(){
        return this.code;
    }
    public String showMsg(){
        return this.msg;
    }
}
