//package com.spider.base.kafka.producer;
//
//import com.spider.base.kafka.task.SpiderKafkaProducerTask;
//import com.spider.base.kafka.util.SpiderKafkaContext;
//import com.spider.base.utils.SpiderThreadPoolExcutorUtil;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.locks.ReentrantLock;
//
//public class SpiderKafkaProducerClient {
//
//    private final static Logger logger = LoggerFactory.getLogger(SpiderKafkaProducerClient.class);
//
//    private static SpiderKafkaProducerClient spiderKafkaProducerClient;
//
//    private static KafkaProducer<String, String> kafkaProducer;
//
//    private static ReentrantLock lock = new ReentrantLock();
//
//    public SpiderKafkaProducerClient getInstance(){
//        try{
//            lock.tryLock();
//            if(spiderKafkaProducerClient==null){
//                spiderKafkaProducerClient = new SpiderKafkaProducerClient();
//            }
//        }finally {
//            lock.unlock();
//        }
//        return spiderKafkaProducerClient;
//    }
//
//    /** 发送消息 */
//    public static void sendMessage(String topic, String message){
//        KafkaProducer<String, String> kafkaProducer = SpiderKafkaContext.getAndSetTopicProducer(topic);
//        if(kafkaProducer != null){
//            //  线程池管理器启动线程
//            SpiderKafkaProducerTask spiderKafkaProducerTask = new SpiderKafkaProducerTask(kafkaProducer, topic, message);
//            SpiderThreadPoolExcutorUtil.getThreadPoolExcutor().submit(spiderKafkaProducerTask);
//        }
//    }
//}
