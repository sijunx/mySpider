package com.spider.dist.facade.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.spider.base.http.SpiderHttpUtil;
import com.spider.dist.facade.api.ISpiderDistScrawlFacade;
import com.spider.dist.service.util.SpiderDistScrawlContext;
import com.spider.dist.service.util.SpiderServerConstantUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class SpiderDistScrawlFacadeImpl implements ISpiderDistScrawlFacade {

    private final static Logger logger = LoggerFactory.getLogger(SpiderDistScrawlFacadeImpl.class);

    /** 爬取url */
    public void startScrawl(){
        //  Map存储经过爬取筛选的url信息(5个url推送一次，推送完成后删除信息)
        //  Map存储urlId的信息：url、状态、当前节点、有效标记、摘要信息
        //  流程：爬取摘要信息->获取关键词信息->信息审核
        //  推送信息：url信息、摘要信息、关键词信息
        String url = SpiderServerConstantUtil.getCenterServerUrl();
        //  head参数
        Map<String,String> headMap = Maps.newHashMapWithExpectedSize(3);
        //
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ticket","X7GUslN4ZBVXAM1R7waT95z8htDQQGNvJrMSahNV7yWrIQy+fwa8huga9oy9hZ/Ca4YGciUBJWrVAbud3moq8fn29n9Vcyd0HzgyMC2MdlsBRQZBIXm4IDCGajpeku87fA8yBPFb0XtfM2E2EVUeBVvT8mHGFSD2z+RSTXnWVn4=");
        String paramsJson = jsonObject.toJSONString();
        try {
            SpiderHttpUtil.sendPostJson(url, headMap, paramsJson, "UTF-8", 30*1000);
        } catch (IOException e) {
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
