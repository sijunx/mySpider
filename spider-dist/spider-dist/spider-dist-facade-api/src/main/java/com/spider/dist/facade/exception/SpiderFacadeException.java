package com.spider.search.facade.exception;

/**
 * FacadeException
 * @author xusijun
 * @date 2018.01.01
 */
public class SpiderFacadeException extends Exception {

    private String errorCode;
    private String detail;

    public SpiderFacadeException(String errorCode, String detail) {
        this.errorCode = errorCode;
        this.detail = detail;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
