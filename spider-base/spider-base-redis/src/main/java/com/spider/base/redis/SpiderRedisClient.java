package com.spider.base.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Map;

public class SpiderRedisClient {

    private final static Logger logger = LoggerFactory.getLogger(SpiderRedisClient.class);

    private static  final SpiderRedisClient spiderRedisClient = new SpiderRedisClient();

    public static SpiderRedisClient getInstance() {
        return spiderRedisClient;
    }

    /** redis分布式锁 */
    public static boolean setNx(String key, String value, int expireTime){
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        long setFlag = 0;
        try {
            setFlag = shardedJedisPool.getResource().setnx(key, value);
            shardedJedisPool.getResource().expire(key, expireTime);
        }finally {
            shardedJedisPool.close();
        }
        return setFlag>0;
    }

    /** 获取key的value */
    public static String get(String key) {
        if(key == null){
            logger.warn("入参key为空");
            return null;
        }
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().get(key);
        }finally {
            shardedJedisPool.close();
        }
    }

    /** 获取key的value */
    public static Map<String,String> getHashMap(String key) {
        if(key == null){
            logger.warn("入参key为空");
            return null;
        }
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().hgetAll(key);
        }finally {
            shardedJedisPool.close();
        }
    }

    /**
     * @param key
     * @param value
     * @param expireSec 超时时间
     * 说明： nxxx 表示：NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key if it already exist.
     *        expx 表示：EX|PX, expire time units: EX = seconds; PX = milliseconds*/

    public static String set(String key, String value, final long expireSec){
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().set(key, value, "NX", "EX", expireSec);
        }finally {
            shardedJedisPool.close();
        }
    }


}