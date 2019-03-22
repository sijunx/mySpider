package com.spider.base.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static Object lpush(String key, String... value){
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().lpush(key, value);
        }finally {
            shardedJedisPool.close();
        }
    }

    public static String lpop(String key){
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().lpop(key);
        }finally {
            shardedJedisPool.close();
        }
    }

    public static String hmset(String key, Map<String, String> map){
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().hmset(key, map);
        }finally {
            shardedJedisPool.close();
        }
    }

    public static List<String> hmget(String key, String field){
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().hmget(key, field);
        }finally {
            shardedJedisPool.close();
        }
    }

    public static Long sadd(String key, String... value){
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().sadd(key, value);
        }finally {
            shardedJedisPool.close();
        }
    }

    public static Long srem(String key, String field){
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().srem(key, field);
        }finally {
            shardedJedisPool.close();
        }
    }

    /** 获取HashMap的key的汇总集合 */
    public static Set<String> hkeys(String key){
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().hkeys(key);
        }finally {
            shardedJedisPool.close();
        }
    }

    public static ScanResult<Map.Entry<String, String>> hscan(String key, String cursor, ScanParams scanParams){
        ShardedJedisPool shardedJedisPool = new SpiderRedisPool().getShardedJedisPool();
        try {
            return shardedJedisPool.getResource().hscan(key, cursor, scanParams);
        }finally {
            shardedJedisPool.close();
        }
    }
}