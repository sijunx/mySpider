package com.spider.base.kafka.task;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.base.kafka.util.SpiderConsumerRunFlag;
import org.apache.commons.collections.CollectionUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpiderKafkaConsumerTask implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(SpiderKafkaConsumerTask.class);

    private KafkaConsumer<String, String> consumer;

    private String topic;

    private String groupId;

    private ISpiderMessageProcessor spiderMessageProcessor;


    public SpiderKafkaConsumerTask(KafkaConsumer kafkaConsumer, String topic, String groupId, ISpiderMessageProcessor inSpiderMessageProcessor) {
        this.consumer = kafkaConsumer;
        this.topic = topic;
        this.groupId = groupId;
        this.spiderMessageProcessor = inSpiderMessageProcessor;
    }

    @Override
    public void run() {
        logger.info("进入run...............................................");
        while(SpiderConsumerRunFlag.getRunFlag().get()) {
            //  主要负责消费kafka消息
            ConsumerRecords<String, String> consumerRecords = consumer.poll(3000);
            logger.info("大小:", consumerRecords.count());
            if(consumerRecords!=null && consumerRecords.count()>0){
                for (ConsumerRecord<String, String> record : consumerRecords) {
                    logger.info("获取到消息的key:{} value:{}", record.key(), record.value());
                    spiderMessageProcessor.messageProcess(String.valueOf(record.value()));
                }
            }
        }
    }
}