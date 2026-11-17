package com.example.demo.util;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date:2022-11-07
 * Time:20:12
 *
 * @Author: 东莞呵呵
 */
@Data
public class ReturnData {
    private int code;
    private String msg;
    private Object data;

    public static ReturnData ok(String msg, Object data) {
        ReturnData returnData = new ReturnData();
        returnData.setData(data);
        returnData.setCode(1);
        returnData.setMsg(msg);
        return returnData;
    }

    public static ReturnData fail(String msg, Object data) {
        ReturnData returnData = new ReturnData();
        returnData.setData(data);
        returnData.setCode(400);
        returnData.setMsg(msg);
        return returnData;
    }
}
