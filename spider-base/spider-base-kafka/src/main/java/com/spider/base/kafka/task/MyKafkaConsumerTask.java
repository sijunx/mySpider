package com.spider.base.kafka.task;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.base.kafka.util.MyKafkaContext;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * kafka消费任务
 */
public class MyKafkaConsumerTask implements Runnable {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyKafkaConsumerTask.class);

    private final String topic;
    private final ISpiderMessageProcessor spiderMessageProcessor;

    public MyKafkaConsumerTask(String topic, ISpiderMessageProcessor spiderMessageProcessor)
    {
        this.topic = topic;
        this.spiderMessageProcessor = spiderMessageProcessor;
    }

    @Override
    public void run() {
        try {
            List<KafkaStream<byte[], byte[]>> kafkaStreamList = MyKafkaContext.getKafkaStreamListByTopic(topic);
            if(CollectionUtils.isEmpty(kafkaStreamList)){
                return;
            }
            for(KafkaStream kafkaStream : kafkaStreamList){
                ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
                while(it.hasNext()){
                    String message = new String(it.next().message(), "UTF-8");
                    spiderMessageProcessor.messageProcess(message);
                }
            }
        }catch (Exception e){
            LOGGER.warn("任务执行异常 topic:{} e:{}", topic, ExceptionUtils.getFullStackTrace(e));
        }
    }
}


