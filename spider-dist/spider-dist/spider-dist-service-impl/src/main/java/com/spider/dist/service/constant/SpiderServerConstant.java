package com.spider.dist.service.constant;

public class SpiderServerConstant {

    private SpiderServerConstant(){}

    private static String DATE_CENTER_SERVER_URL = "http://134.175.107.11:8060/spider-scrawl/open-api";

    private static String POLL_URL = "/getSpiderUrl";

    private static String PUSH_URL = "/recvSpiderUrl";

    public static String getPollUrl(){
        return new StringBuilder().append(DATE_CENTER_SERVER_URL).append(POLL_URL).toString();
    }

    public static String getPushUrl(){
        return new StringBuilder().append(DATE_CENTER_SERVER_URL).append(PUSH_URL).toString();
    }

}
