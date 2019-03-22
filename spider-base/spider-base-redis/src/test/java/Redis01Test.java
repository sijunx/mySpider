import com.spider.base.redis.SpiderRedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;

public class Redis01Test {

    private final static Logger logger = LoggerFactory.getLogger(Redis01Test.class);

    public static void main(String[] arg){

        ScanParams scanParams = new ScanParams();
        scanParams.match("aa*");
        ScanResult<Map.Entry<String, String>> scanResult = SpiderRedisClient.hscan("jingjing", "0",scanParams );
        System.out.println("scanResult:"+scanResult.getResult());
        if(scanResult != null){
            List<Map.Entry<String, String>> list = scanResult.getResult();
            for(int i=0; i<list.size(); i++){
                Map.Entry<String, String> entry = list.get(i);
                System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
            }
        }

//        Set<String> stringSet = SpiderRedisClient.hkeys("jing");
//        System.out.println("stringSet:"+stringSet);

//        Long long01 = SpiderRedisClient.sadd("da", new String[]{"前","龙", "霸王", "龙"});
//        System.out.println("long01:"+long01);
//        Long long02 = SpiderRedisClient.srem("da", "前");
//        System.out.println("long02:"+long02);

//        Map<String, String> map = new HashMap<>();
//        map.put("jimi", "100");
//        map.put("luck", "90");
//        String str90 = SpiderRedisClient.hmset("jing", map);
//        System.out.println("str90:"+str90);
//        List<String> str90List = SpiderRedisClient.hmget("jing", "jimi");
//        System.out.println("str90List:"+str90List);

//        String[] objects = new String[]{"tom","李四"};
//        Object result03 = SpiderRedisClient.lpush("moon", objects);
//        logger.info("result03:{}", result03);
//        for(int i=0; i<10; i++){
//            String str08 = SpiderRedisClient.lpop("moon");
//            logger.info("str08:"+str08);
//        }

//        boolean flag = SpiderRedisClient.setNx("hello", "nihao", 500000);
//        logger.info("setNxResult:{}", flag);
//
//        String result01 = SpiderRedisClient.set("hi", "xia", 5000);
//        logger.info("result01:{}", result01);
//
//        String result02 = SpiderRedisClient.get("hi");
//        logger.info("result02:{}", result02);
    }
}
