package com.spider.dist.facade.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.spider.base.http.SpiderHttpUtil;
import com.spider.dist.service.constant.SpiderServerConstant;
import com.spider.dist.service.dto.SpiderUrlDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpiderPushDataCenterTest {

    private final static Logger logger = LoggerFactory.getLogger(SpiderPushDataCenterTest.class);

    public static void main(String[] arg){
        String url = SpiderServerConstant.getPushUrl();
        //  头信息（存储签名 非对称加密)
        Map<String,String> headMap = Maps.newHashMapWithExpectedSize(3);
        headMap.put("signature", "X7GUslN4ZBVXAM1R7waT95z8htDQQGNvJrMSahNV7yWrIQy+fwa8huga9oy9hZ/Ca4YGciUBJWrVAbud3moq8fn29n9Vcyd0HzgyMC2MdlsBRQZBIXm4IDCGajpeku87fA8yBPFb0XtfM2E2EVUeBVvT8mHGFSD2z+RSTXnWVn4=");

        List<SpiderUrlDTO> spiderUrlDTOList = new ArrayList<>();
        SpiderUrlDTO spiderUrlDTO = new SpiderUrlDTO();
        spiderUrlDTO.setUrl("www.baidu.com");
        spiderUrlDTOList.add(spiderUrlDTO);
        SpiderUrlDTO spiderUrlDTO02 = new SpiderUrlDTO();
        spiderUrlDTO02.setUrl("www.baidu.com");
        spiderUrlDTOList.add(spiderUrlDTO02);



        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("data", JSON.toJSONString(spiderUrlDTOList));
        String result = null;
        try {
            result = SpiderHttpUtil.sendPostJson(url, headMap, paramMap, "UTF-8", 30*1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("result:{}", result);
    }
}