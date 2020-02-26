package com.spider.scrawl.provider.service;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.scrawl.provider.util.MyParseJDBCUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyMessageProcessorDataSynSaveDataBaseTopic implements ISpiderMessageProcessor {

    private final static Logger logger = LoggerFactory.getLogger(MyMessageProcessorDataSynSaveDataBaseTopic.class);

    @Override
    public boolean messageProcess(String message){
        logger.info("--------------------消息处理，收到的消息内容:{}", message);
        System.out.println("消息处理，收到的消息内容："+message);
        try {
            MyParseJDBCUtil.parseBinlog(message);
        }catch (Exception e){
            logger.error("异常了e:{}", ExceptionUtils.getStackTrace(e));
        }
        //        logger.info("返回结果:{}", result);
        logger.info("--------------------消息处理结束------------------------------");
        return true;
    }
}
