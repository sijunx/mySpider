package com.spider.base.kafka.task;

import com.spider.base.kafka.util.MyKafkaContext;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

/**
 * kafka发送消息任务
 * @author xusijun
 * @date 20190601
 */
public class MyKafkaProducerTask implements Runnable{

    private final String topic;
    private final String message;

    public MyKafkaProducerTask(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }

    @Override
    public void run() {
        Producer<Integer, String> producer = MyKafkaContext.getProducer();
        producer.send(new KeyedMessage<Integer, String>(topic, message));
    }
}