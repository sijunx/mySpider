package com.spider.base.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

public class SpiderRedisPool {


    public ShardedJedisPool getShardedJedisPool(){
        //  配置信息
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        //  设置最大连接数
        poolConfig.setMaxTotal(200);
        //  设置最大等待时间
        poolConfig.setMaxWaitMillis(1000 * 100);
        //  在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        poolConfig.setTestOnBorrow(true);
        //  处于空闲状态的连接，超过此值会被销毁 30分钟
        poolConfig.setMinEvictableIdleTimeMillis(1800000);
        //  最新空闲连接
        poolConfig.setMinIdle(5);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setLifo(false);
        //  对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        poolConfig.setSoftMinEvictableIdleTimeMillis(1800000);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(false);
        poolConfig.setTestWhileIdle(true);
        //  多长时间执行空闲线程的回收 10分钟
        poolConfig.setTimeBetweenEvictionRunsMillis(600000);
        poolConfig.setBlockWhenExhausted(false);
        //  redis集群配置
        List<JedisShardInfo> shardsList = new ArrayList<>();
        JedisShardInfo jedisShardInfo = new JedisShardInfo("134.175.107.11", 6379, 3000);
        jedisShardInfo.setPassword("121212");
        shardsList.add(jedisShardInfo);
        return new ShardedJedisPool(poolConfig, shardsList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

}
