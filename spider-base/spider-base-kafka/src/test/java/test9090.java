import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;

/**
 * Created by sijunx on 2018/7/12.
 */
public class test9090   {

    private List<KafkaConsumer<String, String>> consumers = new ArrayList<>();

    public void myConsumer(){
        //提交offset
        //KafkaConsumer<String, String> consumer = KafkaConsumerBuilder.builder(props).withBorkerServer(kafkaServers).withGroupId(groupId).build();
        Properties props = new Properties();
        //kafka消费的的地址
        props.put("bootstrap.servers", "134.175.107.11:9092");
        //组名 不同组名可以重复消费
        props.put("group.id", "myGroup");
        //是否自动提交
        props.put("enable.auto.commit", "false");
        //超时时间
        props.put("session.timeout.ms", "30000");
        //一次最大拉取的条数
        props.put("max.poll.records", 10);
//		earliest当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
//		latest
//		当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
//		none
//		topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
        props.put("auto.offset.reset", "earliest");
        //序列化
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //  分配分区数量
        consumer.assign(Arrays.asList(new TopicPartition("myTopic", 1)));
        //  订阅消息
        consumer.subscribe(Collections.singletonList("myTopic"));
        //  提交消息
        consumer.commitAsync();
    }
}
