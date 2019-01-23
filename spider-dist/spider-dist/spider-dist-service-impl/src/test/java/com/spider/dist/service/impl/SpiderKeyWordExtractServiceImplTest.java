package com.spider.dist.service.impl;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class SpiderKeyWordExtractServiceImplTest {


    public static void main(String[] arg){

        SpiderKeyWordExtractServiceImpl spiderKeyWordExtractService = new SpiderKeyWordExtractServiceImpl();

        List<String> myList = spiderKeyWordExtractService.getKeyWordList("很高兴见到你，好久没见了");

        System.out.println("myList:"+JSON.toJSONString(myList).toString());

    }
}
