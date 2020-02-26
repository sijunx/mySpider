package com.spider.scrawl.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.base.utils.MyHttpUtil;
import com.spider.scrawl.provider.util.MyParseJDBCUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MyMessageProcessorCanalBinlogTopic implements ISpiderMessageProcessor {

    private final static Logger logger = LoggerFactory.getLogger(MyMessageProcessorCanalBinlogTopic.class);

    @Override
    public boolean messageProcess(String message){
        try {
            logger.info("--------------------消息处理，收到的消息内容:{}", message);
            System.out.println("消息处理，收到的消息内容：" + message);
            Config appConfig = ConfigService.getAppConfig();
            String url = appConfig.getProperty("data.syn.url", "");
            String signature = appConfig.getProperty("data.syn.signature", "");
            boolean flag = MyParseJDBCUtil.checkData(message);
            if(!flag){
                logger.info("数据校验未通过，不同步:{}", message);
                return true;
            }
            String result = MyHttpUtil.send(url, message);
            logger.info("返回结果:{}", result);
            logger.info("--------------------消息处理结束------------------------------");
        }catch (Exception e){
            logger.error("处理binlog异常了e:{}", ExceptionUtils.getStackTrace(e));
        }
        return true;
    }
}
