package com.spider.base.kafka.task;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpiderKafkaProducerTask implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(SpiderKafkaProducerTask.class);

    private KafkaProducer<String, String> kafkaProducer;
    private String topic;
    private String message;

    public SpiderKafkaProducerTask(KafkaProducer<String, String> kafkaProducer, String topic, String message){
        this.kafkaProducer = kafkaProducer;
        this.topic = topic;
        this.message = message;
    }

    @Override
    public void run(){
        String key = "message";
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, key, message);
        kafkaProducer.send(producerRecord);
        logger.info("发送数据完成 value:{}", message);
        System.out.println("发送数据完成 message:"+message);
    }
}
