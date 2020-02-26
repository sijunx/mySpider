import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpiderConsumerTest {

    private final static Logger logger = LoggerFactory.getLogger(SpiderConsumerTest.class);

    private static KafkaConsumer<String, String> consumer;

    public static void main(String[] argu){
//        SpiderKafkaConsumerClient consumerClient = SpiderKafkaConsumerClient.getInstance();
//        SpiderMessageProcessorImpl spiderMessageProcessor = new SpiderMessageProcessorImpl();
//        consumerClient.receiveMessages("myTopic", "myGroup", spiderMessageProcessor);
    }
}
