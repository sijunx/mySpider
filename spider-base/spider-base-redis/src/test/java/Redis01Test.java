import com.spider.base.redis.SpiderRedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Redis01Test {

    private final static Logger logger = LoggerFactory.getLogger(Redis01Test.class);

    public static void main(String[] arg){
        boolean flag = SpiderRedisClient.setNx("hello", "nihao", 500000);
        logger.info("setNxResult:{}", flag);

        String result01 = SpiderRedisClient.set("hi", "xia", 5000);
        logger.info("result01:{}", result01);

        String result02 = SpiderRedisClient.get("hi");
        logger.info("result02:{}", result02);
    }
}
