


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spider.base.kafka.monitor.SpiderKafkaMonitor;
import kafka.api.OffsetRequest;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.cluster.Broker;
import kafka.common.TopicAndPartition;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.TopicMetadataResponse;
import kafka.javaapi.consumer.SimpleConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KafkaMonitor03Test {

    private final static Logger logger = LoggerFactory.getLogger(KafkaMonitor03Test.class);

    public static void main(String[] arg)throws Exception{

        for(;;) {
            Thread.sleep(10*1000);
            long lagSize = SpiderKafkaMonitor.getLogSize("myGroup","myTopic", 0);

            logger.info("lagSize:{}", lagSize);
        }
//        String host = "121.40.187.38";
//        String topic = "myTopic";
//        int partition = 0;
//        int port = 9062;
//        String clientName = "Client_" + topic + "_" + partition;
//
//        SimpleConsumer simpleConsumer = new SimpleConsumer(host, port, 10000, 64*1024, clientName);
//        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
//        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
//        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(OffsetRequest.LatestTime(), 1));
//        kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo, OffsetRequest.CurrentVersion(), clientName);
//        OffsetResponse response = simpleConsumer.getOffsetsBefore(request);
//        if (response.hasError()) {
//            System.out.println("Error fetching data Offset , Reason: " + response.errorCode(topic, partition) );
//        }
//        long[] offsets = response.offsets(topic, partition);
//        logger.info("offsets[0]:{}", offsets[0]);
    }
}

