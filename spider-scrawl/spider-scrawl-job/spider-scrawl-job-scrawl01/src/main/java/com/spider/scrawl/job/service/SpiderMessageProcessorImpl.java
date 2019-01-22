package com.spider.scrawl.job.service;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.base.kafka.task.SpiderKafkaConsumerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpiderMessageProcessorImpl implements ISpiderMessageProcessor{

    private final static Logger logger = LoggerFactory.getLogger(SpiderKafkaConsumerTask.class);

    @Override
    public void messageProcess(String message){
        logger.info("message:{}", message);

        System.out.println("message:"+message);
//        JSONObject jsonObject = JSONObject.parseObject(message);
//        String userName = jsonObject.getString("userName");
//        logger.info("userName:{}", userName);
    }
}
