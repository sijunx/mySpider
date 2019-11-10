package com.spider.base.utils;


public class MapCacheObj {
    /**
     * 缓存对象
     */
    private Object CacheValue;
    /**
     * 缓存过期时间
     */
    private long ttlTime;

    MapCacheObj(Object cacheValue, Long ttlTime) {
        CacheValue = cacheValue;
        this.ttlTime = ttlTime;
    }

    Object getCacheValue() {
        return CacheValue;
    }

    long getTtlTime() {
        return ttlTime;
    }

    @Override
    public String toString() {
        return "CacheObj {" +
                "CacheValue = " + CacheValue +
                ", ttlTime = " + ttlTime +
                '}';
    }
}