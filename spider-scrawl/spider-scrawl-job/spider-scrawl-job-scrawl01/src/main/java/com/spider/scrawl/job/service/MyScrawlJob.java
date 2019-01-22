package com.spider.scrawl.job.service;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.base.kafka.consumer.SpiderKafkaConsumerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class MyScrawlJob {

    @Autowired
    private ISpiderMessageProcessor spiderMessageProcessor;

    public void excuteMessageProcessing(){
        //  订阅消息：待爬取的网页url信息 topic:spider_scrawl_url_topic group:spider_scrawl_url_group
        SpiderKafkaConsumerClient consumerClient = SpiderKafkaConsumerClient.getInstance();
        consumerClient.receiveMessages("myTopic", "myGroup", spiderMessageProcessor);


        //  推送消息：返回爬取加工过的url信息 topic:spider_scrawl_passed_url_topic


    }
}
