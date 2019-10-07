package com.spider.ant.impl.constant;

public class DataCenterUrlConstant {

    private DataCenterUrlConstant(){}

    private static String DATE_CENTER_SERVER_URL = "http://134.175.107.11:8060/spider-scrawl/open-api";
//    private static String DATE_CENTER_SERVER_URL = "http://127.0.0.1:8060/spider-scrawl/open-api";

    private static String POLL_URL = "/getSpiderUrl";

    private static String PUSH_URL = "/recvSpiderUrl";

    public static String getPollUrl(){
        return new StringBuilder().append(DATE_CENTER_SERVER_URL).append(POLL_URL).toString();
    }

    public static String getPushUrl(){
        return new StringBuilder().append(DATE_CENTER_SERVER_URL).append(PUSH_URL).toString();
    }

}
