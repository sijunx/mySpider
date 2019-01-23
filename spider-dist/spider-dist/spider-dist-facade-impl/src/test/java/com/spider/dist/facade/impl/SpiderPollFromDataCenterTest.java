package com.spider.dist.facade.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.spider.base.http.SpiderHttpUtil;
import com.spider.dist.service.constant.SpiderServerConstant;
import com.spider.dist.service.constant.SpiderTicketConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpiderPollFromDataCenterTest {

    private final static Logger logger = LoggerFactory.getLogger(SpiderPollFromDataCenterTest.class);

    public static void main(String[] arg){
        String url = SpiderServerConstant.getPollUrl();
        //  头信息（存储签名 非对称加密)
        Map<String,String> headMap = Maps.newHashMapWithExpectedSize(3);
        headMap.put("signature", SpiderTicketConstant.getTicket());
                //"X7GUslN4ZBVXAM1R7waT95z8htDQQGNvJrMSahNV7yWrIQy+fwa8huga9oy9hZ/Ca4YGciUBJWrVAbud3moq8fn29n9Vcyd0HzgyMC2MdlsBRQZBIXm4IDCGajpeku87fA8yBPFb0XtfM2E2EVUeBVvT8mHGFSD2z+RSTXnWVn4=");
        String result = null;
        try {
            result = SpiderHttpUtil.sendPostJson(url, headMap, new HashMap<String, String>(), "UTF-8", 30*1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("result:{}", result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray jsonArray = JSONObject.parseArray(jsonObject.get("data").toString());
        String temp = jsonArray.getString(0);
        logger.info("temp:{}", temp);
    }
}