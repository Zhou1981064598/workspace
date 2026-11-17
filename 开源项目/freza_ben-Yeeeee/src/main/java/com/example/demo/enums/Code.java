package com.example.demo.enums;

public enum Code {

    SUCCESS("0", "操作成功"),
    OPERATION_FAIL("1000", "操作失败"),
    ILLEGAL_PARAMETER("1001", "非法参数"),
    NULL_DATA("2001", "未查询出匹配数据"),
    ACCESS_DENIED("4010", "权限不足，拒绝访问"),
    USER_DO_NOT_EXIST("4011", "用户不存在"),
    USER_LOGIN_FAIL("4012", "错误的账号或密码"),
    USER_LOGIN_INVALID_TOKEN("4013", "无效的身份令牌"),
    USER_QUIT_FAIL("4020", "退出失败"),
    UNKNOWN_ERROR("-1", "未知错误");


    private final String code;
    private final String msg;

    Code(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }

}