package com.zk.blog.base;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collection;

public class ApiResponse<T> {

    private static final Integer ERROR_CODE = 1118;

    private Integer code;

    private String message;

    private T data;

    public static ApiResponse success() {
        ApiResponse response = new ApiResponse();
        response.setCode(BaseMessageEnum.SUCCESS.getCode());
        response.setMessage(BaseMessageEnum.SUCCESS.getMeesage());
        return response;
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse response = new ApiResponse<>();
        response.setCode(BaseMessageEnum.SUCCESS.getCode());
        response.setMessage(BaseMessageEnum.SUCCESS.getMeesage());
        response.setData(data);
        return response;
    }

    public static ApiResponse error(BaseMessageEnum baseMessageEnum) {
        ApiResponse response = new ApiResponse();
        response.setCode(baseMessageEnum.getCode());
        response.setMessage(baseMessageEnum.getMeesage());
        return response;
    }

    public static ApiResponse error(String message) {
        ApiResponse response = new ApiResponse();
        response.setCode(ERROR_CODE);
        response.setMessage(message);
        return response;
    }

    public static ApiResponse success(BaseMessageEnum messageEnum) {
        ApiResponse response = new ApiResponse();
        response.setCode(messageEnum.getCode());
        response.setMessage(messageEnum.getMeesage());
        return response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
