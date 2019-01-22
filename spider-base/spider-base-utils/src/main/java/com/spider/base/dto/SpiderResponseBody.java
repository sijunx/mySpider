package com.spider.base.dto;

import java.io.Serializable;
import java.util.HashMap;

public class SpiderResponseBody<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    private static final String SUCESS = "10000";
    private static final String MESSAGE = "success";

    private SpiderResponseBody(T content) {
        this.code = SUCESS;
        this.message = MESSAGE;
        this.data = content;
    }

//    public static SpiderResponseBody buildSucessResponse(T data) {
//        if (data == null) {
//            data = (T) new HashMap<>(1);
//        }
//        return new SpiderResponseBody(data);
//    }

    public static <T> SpiderResponseBody<T> buildSucessResponse(T content) {
        if(content == null){
            content = (T) new HashMap<>(1);
        }
        return new SpiderResponseBody(content);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
