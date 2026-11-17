package com.zhou.springbootrbac.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    // 状态码
    @JsonView(ResultView.class)
    private int code;
    // 提示信息
    @JsonView(ResultView.class)
    private String message;
    // 数据
    @JsonIgnore(false)
    @JsonView(ResultView.class)
    private T data;
    // 统一结果试图
    public interface ResultView{}
    private static <D> Result<D> build(int code,String message,D data){
        return new Result<D>(code,message,data);
    }
    public static Result<Object> build(ResultCode resultCode) {
        return build(resultCode.getCode(), resultCode.getMessage(), null);
    }
    public static <D> Result<D> success(){
        return build(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),null );
    }
    public static <D> Result<D> success(String message){
        return build(ResultCode.SUCCESS.getCode(), message,null);
    }

    public static <D> Result<D> success(ResultCode resultCode){
        return build(resultCode.getCode(),resultCode.getMessage(),null);
    }
    public static <D> Result<D> success(String message,D data){
        return build(ResultCode.SUCCESS.getCode(),message,data);
    }
    public static <D> Result<D> failure(ResultCode resultCode,String message){
        return build(resultCode.getCode(),message,null);
    }
    public static <D> Result<D> failure(ResultCode resultCode){
        return build(resultCode.getCode(),resultCode.getMessage(),null);
    }

    public static <D> Result<D> failure(ResultCode resultCode,D data){
        return build(resultCode.getCode(),resultCode.getMessage(),data);
    }
}