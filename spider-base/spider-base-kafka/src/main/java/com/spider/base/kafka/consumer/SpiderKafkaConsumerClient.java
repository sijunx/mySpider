//package com.spider.base.kafka.consumer;
//
//import com.spider.base.kafka.api.ISpiderMessageProcessor;
//import com.spider.base.kafka.task.SpiderKafkaConsumerTask;
//import com.spider.base.kafka.util.SpiderKafkaContext;
//import com.spider.base.utils.SpiderThreadPoolExcutorUtil;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * Created by SK_ZARD on 2018/10/5.
// */
//public class SpiderKafkaConsumerClient {
//
//    private final static Logger logger = LoggerFactory.getLogger(SpiderKafkaConsumerClient.class);
//
//    private static SpiderKafkaConsumerClient spiderKafkaConsumerClient;
//
//    private static ReentrantLock lock = new ReentrantLock();
//
//    private SpiderKafkaConsumerClient(){}
//
//    public static SpiderKafkaConsumerClient getInstance(){
//        try {
//            lock.tryLock();
//            if (spiderKafkaConsumerClient == null) {
//                spiderKafkaConsumerClient = new SpiderKafkaConsumerClient();
//            }
//        }finally {
//            lock.unlock();
//        }
//        return spiderKafkaConsumerClient;
//    }
//
//    //  收取消息
//    public void receiveMessages(String topic, String groupId,  ISpiderMessageProcessor spiderMessageProcessor){
//        List<KafkaConsumer<String, String>> kafkaConsumerList = SpiderKafkaContext.getAndSetTopicConsumerList(topic, groupId);
//        if(!CollectionUtils.isEmpty(kafkaConsumerList)){
//            //  线程池管理器启动线程
//            for(KafkaConsumer<String, String> kafkaConsumer:kafkaConsumerList){
//                SpiderKafkaConsumerTask spiderKafkaConsumerTask = new SpiderKafkaConsumerTask(kafkaConsumer, topic, groupId,spiderMessageProcessor);
//                SpiderThreadPoolExcutorUtil.getThreadPoolExcutor().submit(spiderKafkaConsumerTask);
//            }
//        }
//    }
//}
