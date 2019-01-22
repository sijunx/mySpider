package com.spider.dist.service.util;

public class SpiderServerConstantUtil {

    private SpiderServerConstantUtil(){}

    private static String centerServerUrl = "http://127.0.0.1:8060/spider-scrawl/open-api/getSpiderUrl";

//    private static String spiderUrl = "http://121.40.187.38:8095/spider-search/spider/list";

    private static String spiderUrl = "http://localhost:8091/spider-search/spider/list";

    public static String getCenterServerUrl(){
        return centerServerUrl;
    }

    public static String getSpiderUrl(){
        return spiderUrl;
    }

}
