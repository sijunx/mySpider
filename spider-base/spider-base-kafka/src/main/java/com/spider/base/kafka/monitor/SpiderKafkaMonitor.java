package com.spider.base.kafka.monitor;

import com.spider.base.kafka.util.SpiderKafkaPropertiesUtil;
import kafka.api.OffsetRequest;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.OffsetMetadataAndError;
import kafka.common.TopicAndPartition;
import kafka.javaapi.OffsetFetchRequest;
import kafka.javaapi.OffsetFetchResponse;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.consumer.SimpleConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 获取topic、partition的logSize
 * @author xusijun
 * @date 2019.2.11
 */
public class SpiderKafkaMonitor {

    private final static Logger logger = LoggerFactory.getLogger(SpiderKafkaMonitor.class);

    private SpiderKafkaMonitor(){}

    /** 获取topic、partition的logSize */
    public static long getLogSize(String group, String topic, int partition){
        String clientName = "Client_" + topic + "_" + partition;
        Properties properties = SpiderKafkaPropertiesUtil.getConsumerPropertis();
        String boostrapServerStr = properties.getProperty("bootstrap.servers");
        String[] serverInfo = boostrapServerStr.split(":");
        String host = "127.0.0.1";
        int port = 9062;
        if(serverInfo!=null && serverInfo.length>=2){
            host = serverInfo[0];
            port = Integer.parseInt(serverInfo[1]);
        }
        SimpleConsumer simpleConsumer = new SimpleConsumer(host, port, 10000, 64*1024, clientName);
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(OffsetRequest.LatestTime(), 1));
        kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo, OffsetRequest.CurrentVersion(), clientName);
        OffsetResponse response = simpleConsumer.getOffsetsBefore(request);

//        String groupId, List<TopicAndPartition> requestInfo, int correlationId, String clientId)
        List<TopicAndPartition> requestInfoList = new ArrayList<>();
        requestInfoList.add(topicAndPartition);
        OffsetFetchRequest offsetFetchRequest = new OffsetFetchRequest(group, requestInfoList,1,"1");
        OffsetFetchResponse offsetFetchResponse = simpleConsumer.fetchOffsets(offsetFetchRequest);
        if (response.hasError()) {
            logger.info("Error fetching data Offset , Reason: " + response.errorCode(topic, partition) );
            return 0;
        }
        long[] offsets = response.offsets(topic, partition);
        logger.info("offsets[0]:{}", offsets[0]);

        Map<kafka.common.TopicAndPartition, kafka.common.OffsetMetadataAndError> map01 = offsetFetchResponse.offsets();
        if(map01!=null && map01.size()>0){
            for(Map.Entry<kafka.common.TopicAndPartition, kafka.common.OffsetMetadataAndError> entry:map01.entrySet()){
                OffsetMetadataAndError offsetMetadataAndError = entry.getValue();
                Long offset01 = offsetMetadataAndError.offset();
                logger.info("offset01:{}", offset01);
            }
        }
        return offsets[0];
    }
}

