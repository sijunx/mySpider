package com.spider.search.service.error;

public class ErrorCode {

    private String code;
    private String msg;

    public ErrorCode(String code, String msg) {
        this(code, msg, msg);
    }

    public ErrorCode(String code, String msg, String userMsg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}