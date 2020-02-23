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
public class MyMessageProcessorDataSynSaveDataBaseTopic implements ISpiderMessageProcessor {

    private final static Logger logger = LoggerFactory.getLogger(MyMessageProcessorDataSynSaveDataBaseTopic.class);

    @Override
    public boolean messageProcess(String message){
        logger.info("--------------------消息处理，收到的消息内容:{}", message);
        System.out.println("消息处理，收到的消息内容："+message);

//        logger.info("返回结果:{}", result);
        logger.info("--------------------消息处理结束------------------------------");
        return true;
    }
}
