package com.spider.base.kafka.util;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyKafkaContext {

    public static Producer<Integer, String> producer = null;

    public static ConsumerConnector consumerConnector = null;

    public static ConcurrentHashMap<String, List<KafkaStream<byte[], byte[]>>> kafkaStreamMap = new ConcurrentHashMap();

    /** 获取生产者列表 */
    public static Producer<Integer, String> getProducer(){
        if(producer == null) {
            producer = new Producer<Integer, String>(new ProducerConfig(MyKafkaPropertiesUtil.getProducerConfig()));
        }
        return producer;
    }

    /** 获取消费者列表 */
    public static ConsumerConnector getConsumer(){
//        if(consumerConnector == null) {
            //todo:加互斥锁
            consumerConnector = Consumer.createJavaConsumerConnector(new ConsumerConfig(MyKafkaPropertiesUtil.getConsumerConfig()));
  //      }
        return consumerConnector;
    }

    /** 根据topic获取kafka消费流列表 */
    public static List<KafkaStream<byte[], byte[]>> getKafkaStreamListByTopic(String topic){
        if(!kafkaStreamMap.containsKey(topic)){
            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
            topicCountMap.put(topic, new Integer(1));
            Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = getConsumer().createMessageStreams(topicCountMap);
            if(!CollectionUtils.isEmpty(consumerMap.get(topic))){
                kafkaStreamMap.put(topic, consumerMap.get(topic));
            }
        }
        return kafkaStreamMap.get(topic);
    }
}
