package com.example.SpringCloudStudy.common.microservice.pojo;

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

    private static <T> Result<T> build(int code, String message, T data) {
        return new Result<T>(code, message, data);
    }

    public static Result<Object> build(ResultCode resultCode) {
        return build(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static <T> Result<T> build(ResultCode resultCode, T data) {
        return build(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public static Result<Object> error() {
        return build(ResultCode.ERROR);
    }

    public static <T> Result<T> success(T data) {
        return build(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(String message) {
        return build(ResultCode.SUCCESS.getCode(), message, null);
    }

    public static <T> Result<T> success(String message, T data) {
        return build(ResultCode.SUCCESS.getCode(), message, data);
    }
}
