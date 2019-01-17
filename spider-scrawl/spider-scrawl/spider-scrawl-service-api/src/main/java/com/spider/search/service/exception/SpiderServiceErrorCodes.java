package com.spider.search.service.exception;

import com.spider.search.service.error.ErrorCode;

/**
 * ServiceErrorCodes
 * @author xusijun
 * @date 2018.01.01
 */
public class SpiderServiceErrorCodes {

    /**S01为search 2表示Service层 */
    private static final  String PKG_PREFIX_CODE = "S012";

    public static final ErrorCode SEARCH_SAVE_FAIL = new ErrorCode(PKG_PREFIX_CODE+"001","save fail");

}
