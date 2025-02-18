package com.qiujie.dto;

import com.qiujie.enums.BusinessStatusEnum;

import java.util.List;

public class ResponseDTO<T> {
    private boolean success;
    private String message;
    private T data;

    public ResponseDTO(BusinessStatusEnum businessStatusEnum) {
    }

    public ResponseDTO(Integer code, String message) {
    }

    public ResponseDTO(BusinessStatusEnum businessStatusEnum, Object data) {
    }

    public ResponseDTO(BusinessStatusEnum businessStatusEnum, Object data, String token) {
    }

    public ResponseDTO(Integer code, String message, Object data) {
    }

    public ResponseDTO() {

    }

    public static <T> ResponseDTO<T> success() {
        return success(null);
    }

    public static <T> ResponseDTO<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> ResponseDTO<T> success(String message, T data) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> ResponseDTO<T> error() {
        return error("操作失败");
    }

    public static <T> ResponseDTO<T> error(String message) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
