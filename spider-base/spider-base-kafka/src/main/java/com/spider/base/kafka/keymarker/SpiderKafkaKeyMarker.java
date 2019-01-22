package com.spider.base.kafka.keymarker;

public class SpiderKafkaKeyMarker {

    public static String getTopicGroupKey(String topic, String groupId){
        return new StringBuilder().append(topic).append("_").append(groupId).toString();
    }

    public static String getTopicKey(String topic){
        return new StringBuilder().append(topic).toString();
    }
}
