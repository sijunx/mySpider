package com.spider.job;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyMessageProcessor implements ISpiderMessageProcessor {

    private final static Logger logger = LoggerFactory.getLogger(MyMessageProcessor.class);

    @Override
    public void messageProcess(String message){
        logger.info("--------------------消息处理，收到的消息内容:{}", message);
    }
}
