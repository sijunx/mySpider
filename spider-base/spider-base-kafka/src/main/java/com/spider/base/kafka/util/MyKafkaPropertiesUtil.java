package com.spider.base.kafka.util;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLOutput;
import java.util.Properties;

public class MyKafkaPropertiesUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(MyKafkaPropertiesUtil.class);

    public static Properties getConsumerConfig(){
        //获取zookeeper配置的信息
        String zookeeperConnect = getZookeeperIps();
        LOGGER.info("获取zookeeper配置 zookeeperConnect:{}", zookeeperConnect);
        Properties props = new Properties();
        props.put("zookeeper.connect", zookeeperConnect);
        props.put("group.id", "myGroup");
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return props;
    }

    /** 获取生产者配置信息 */
    public static Properties getProducerConfig() {
        //获取配置brokerList
        String metadataBrokerList = getBootsServerIps();
        LOGGER.info("获取metadataBrokerList配置 metadataBrokerList:{}", metadataBrokerList);
        Properties props = new Properties();
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", metadataBrokerList);
        // Use random partitioner. Don't need the key type. Just set it to Integer.
        // The message is of type String.
        return props;
    }

    private static String getBootsServerIps(){
        Config appConfig = ConfigService.getAppConfig();
        String serverIp = appConfig.getProperty("bootstrap.server", "");
        LOGGER.info("配置bootstrap.servers"+serverIp);
        return serverIp;
    }

    private static String getZookeeperIps(){
//        String env = System.getProperty("env");
//        String appId = System.getProperty("app.id");
//        String apploMeta = System.getProperty("apollo.meta");
//        System.out.println(" env:"+env+" app.id: "+ appId + " apollo.meta"+apploMeta);

        Config appConfig = ConfigService.getAppConfig();
        String serverIp = appConfig.getProperty("kafka.zookeeper", "");

        System.out.println("配置zookeeper:"+serverIp);
        LOGGER.info("配置zookeeper"+serverIp);
        return serverIp;

    }
}
