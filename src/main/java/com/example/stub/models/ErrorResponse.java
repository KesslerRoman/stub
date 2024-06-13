package com.example.stub.models;

public class ErrorResponse {

    private String msg;

    private Integer code;

    public ErrorResponse(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
