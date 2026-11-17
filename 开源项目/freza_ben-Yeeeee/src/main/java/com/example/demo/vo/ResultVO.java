package com.example.demo.vo;

import com.example.demo.enums.Code;

public class ResultVO {
    private Object data;
    private String msg;
    private String code;

    public ResultVO() {
        this.code = Code.SUCCESS.getCode();
        this.msg = Code.SUCCESS.getMessage();
    }

    public ResultVO(Object data) {
        this.data = data;
        this.code = Code.SUCCESS.getCode();
        this.msg = Code.SUCCESS.getMessage();
    }


    public ResultVO(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public ResultVO(Object data, String code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public void setFail(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
