package com.spider.base.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

public class SpiderRedisClient {

    private static  final SpiderRedisClient spiderRedisClient = new SpiderRedisClient();

    public static SpiderRedisClient getInstance() {
        return spiderRedisClient;
    }

    public ShardedJedisPool getShardedJedisPool(){
        //  配置信息
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // 设置最大连接数
        poolConfig.setMaxTotal(200);
        // 设置最大空闲数
        poolConfig.setMaxIdle(8);
        // 设置最大等待时间
        poolConfig.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        poolConfig.setTestOnBorrow(true);
        //  redis集群配置
        List<JedisShardInfo> shardsList = new ArrayList<>();
        JedisShardInfo jedisShardInfo = new JedisShardInfo("127.0.0.1", 6379, 3000);
        shardsList.add(jedisShardInfo);
        return new ShardedJedisPool(poolConfig, shardsList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    /** redis分布式锁 */
    public boolean setNx(String key, String value, int expireTime){
        ShardedJedisPool shardedJedisPool = this.getShardedJedisPool();
        long setFlag = 0;
        try {
            setFlag = shardedJedisPool.getResource().setnx(key, value);
            shardedJedisPool.getResource().expire(key, expireTime);
        }finally {
            shardedJedisPool.close();
        }
        return setFlag>0;
    }
}