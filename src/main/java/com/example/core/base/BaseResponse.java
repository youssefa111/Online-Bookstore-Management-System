package com.example.core.base;

import lombok.Data;


@Data
public class BaseResponse<T> {
    private String message;
    private int statusCode;
    private Boolean success = Boolean.TRUE;
    private T body;

    public BaseResponse() {
        message = "SUCCESS";
        statusCode = 200;
        this.success = Boolean.TRUE;
    }

    public BaseResponse(T body) {
        message = "SUCCESS";
        statusCode = 200;
        this.body = body;
        this.success = Boolean.TRUE;
    }

    //    public BaseResponse(String message){
//        this.message = message;
//        statusCode = 200;
//    }
    public BaseResponse(T body, String message) {
        this.message = message;
        this.statusCode = 200;
        this.body = body;
    }

    public BaseResponse(T body, String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.body = body;
    }

    public BaseResponse(T body, String message, Boolean success, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.body = body;
        this.success = success;
    }
}
