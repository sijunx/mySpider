package com.spider.scrawl.provider.service;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyMessageProcessor60 implements ISpiderMessageProcessor {

    private final static Logger logger = LoggerFactory.getLogger(MyMessageProcessor60.class);

    @Override
    public void messageProcess(String message){
        logger.info("--------------------消息处理，收到的消息内容:{}", message);
        System.out.println("消息处理，收到的消息内容："+message);
        logger.info("--------------------消息处理结束------------------------------");
    }

}
