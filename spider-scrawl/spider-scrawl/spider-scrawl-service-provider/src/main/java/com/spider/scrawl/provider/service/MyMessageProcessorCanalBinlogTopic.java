package com.spider.scrawl.provider.service;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.google.common.collect.Maps;
import com.spider.base.http.SpiderHttpUtil;
import com.spider.base.kafka.api.ISpiderMessageProcessor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class MyMessageProcessorCanalBinlogTopic implements ISpiderMessageProcessor {

    private final static Logger logger = LoggerFactory.getLogger(MyMessageProcessorCanalBinlogTopic.class);

    @Override
    public boolean messageProcess(String message){
        logger.info("--------------------消息处理，收到的消息内容:{}", message);
        System.out.println("消息处理，收到的消息内容："+message);
        Config appConfig = ConfigService.getAppConfig();
        String url = appConfig.getProperty("data.syn.url", "");
        String signature = appConfig.getProperty("data.syn.signature", "");
        //  header
        Map<String, String> headMap = Maps.newHashMapWithExpectedSize(6);
        headMap.put("signature", signature);
        Map<String, String> bodyMap = Maps.newHashMapWithExpectedSize(6);
        bodyMap.put("message", message);
        String result;
        try {
            result = SpiderHttpUtil.sendPostJson(url, headMap, bodyMap, "UTF-8", 30 * 1000);
        } catch (IOException e) {
            logger.error("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
            return false;
        }
        logger.info("返回结果:{}", result);
        logger.info("--------------------消息处理结束------------------------------");
        return true;
    }
}
