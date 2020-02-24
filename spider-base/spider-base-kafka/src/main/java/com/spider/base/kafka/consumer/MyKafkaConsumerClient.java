package com.spider.base.kafka.consumer;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.base.kafka.task.MyKafkaConsumerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyKafkaConsumerClient {

    private final static Logger logger = LoggerFactory.getLogger(MyKafkaConsumerClient.class);

    /** 发送消息 */
    public static void receiveMessage(String topic, ISpiderMessageProcessor spiderMessageProcessor){
        //  线程池管理器启动线程
        MyKafkaConsumerTask consumerTask = new MyKafkaConsumerTask(topic, spiderMessageProcessor);
        logger.info("调用kafka消费端开始....");
        System.out.println("XXXXUUUUUUUU");
        Thread thread = new Thread(consumerTask);
        thread.start();
    }
}
