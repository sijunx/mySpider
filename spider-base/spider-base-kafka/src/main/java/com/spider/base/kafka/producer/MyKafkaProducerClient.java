package com.spider.base.kafka.producer;

import com.spider.base.kafka.task.MyKafkaProducerTask;
import com.spider.base.kafka.util.MyThreadPoolExcutorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyKafkaProducerClient {

    private final static Logger logger = LoggerFactory.getLogger(MyKafkaProducerClient.class);


    /** 发送消息 */
    public static void sendMessage(String topic, String message){
        //  线程池管理器启动线程
        MyKafkaProducerTask producerTask = new MyKafkaProducerTask(topic, message);
        MyThreadPoolExcutorUtil.generateTaskExcutor().submit(producerTask);
    }
}
