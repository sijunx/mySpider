package com.spider.base.kafka.util;

import com.spider.base.kafka.keymarker.SpiderKafkaKeyMarker;
import org.apache.commons.collections.CollectionUtils;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class SpiderKafkaContext {

    private final static Logger logger = LoggerFactory.getLogger(SpiderKafkaContext.class);

    public static ConcurrentHashMap<String, List<KafkaConsumer<String, String>>> topicConsumerMap = new ConcurrentHashMap();

    public static ConcurrentHashMap<String, KafkaProducer<String, String>> topicProducerMap = new ConcurrentHashMap<>();

    /** 获取生产者列表 */
    public static KafkaProducer<String, String> getAndSetTopicProducer(String topic){
        KafkaProducer<String, String> kafkaProducer = topicProducerMap.get(SpiderKafkaKeyMarker.getTopicKey(topic));
        if(kafkaProducer==null) {
            Properties properties = SpiderKafkaPropertiesUtil.getProducerProperties();
            KafkaProducer<String, String> myKafkaProducer = new KafkaProducer<String, String>(properties);
            topicProducerMap.put(SpiderKafkaKeyMarker.getTopicKey(topic), myKafkaProducer);
        }
        return topicProducerMap.get(SpiderKafkaKeyMarker.getTopicKey(topic));
    }

    /** 获取消费者列表 */
    public static List<KafkaConsumer<String, String>> getAndSetTopicConsumerList(String topic, String groupId){
        List<KafkaConsumer<String, String>> kafkaConsumerList = topicConsumerMap.get(SpiderKafkaKeyMarker.getTopicGroupKey(topic, groupId));
        if(CollectionUtils.isEmpty(kafkaConsumerList)){
            //  获取分区数量
            KafkaProducer<String, String> producer = topicProducerMap.get(SpiderKafkaKeyMarker.getTopicKey(topic));
            int partitions = 1;
            if(producer != null){
                List<org.apache.kafka.common.PartitionInfo> partitionInfoList = producer.partitionsFor(topic);
                partitions = partitionInfoList!=null?partitionInfoList.size():1;
            }
            //  若分区过小或者过大，则设置为1
            if(partitions < 1 || partitions > 100){
                partitions = 1;
                logger.warn("SpiderKafkaContext获取topic分区数量异常 topic:{} partitions:{}", topic, partitions);
            }
            //  获取分区数量，根据topic分区数量创建消费者数量
            kafkaConsumerList = new ArrayList<>();
            for(int icount=0; icount<partitions; icount++){
                Properties properties = SpiderKafkaPropertiesUtil.getConsumerPropertis();
                KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
                kafkaConsumer.subscribe(Arrays.asList(topic));
                kafkaConsumerList.add(kafkaConsumer);
            }
            topicConsumerMap.put(SpiderKafkaKeyMarker.getTopicGroupKey(topic, groupId), kafkaConsumerList);
        }
        return topicConsumerMap.get(SpiderKafkaKeyMarker.getTopicGroupKey(topic, groupId));
    }
}
