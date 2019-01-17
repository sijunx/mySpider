package com.spider.search.service.exception;

/**
 * ServiceException
 * @author xusijun
 * @date 2018.01.01
 */
public class SpiderServiceException extends Exception {

    private String errorCode;
    private String detail;

    public SpiderServiceException(String errorCode, String detail) {
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

