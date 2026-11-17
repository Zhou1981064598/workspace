package com.zhou.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;
    private Integer count;
    private ResultCode resultCode;

    public Result() {
    }

    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.showCode();
        this.msg = resultCode.showMsg();
        this.data = data;
    }

    /**
     * 返回成功信息
     * @return
     */
    public static Result success(){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return  result;
    }

    /**
     * 返回成功信息，带列表数据
     * @param data 列表数据
     * @return
     */
    public static Result success(Object data){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return  result;
    }

    /**
     * 返回成功信息，带分页列表数据
     * @param data 列表数据
     * @param count 总数
     * @return
     */
    public static Result success(Object data, int count){
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        result.setCount(count);
        return  result;
    }

    /**
     * 返回失败信息
     * @param resultCode 失败的错误码
     * @return
     */
    public static Result failure(ResultCode resultCode){
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }
}
