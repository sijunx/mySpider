package com.spider.base.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapCacheUtils {

    private MapCacheUtils(){

    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MapCacheUtils.class);

    /** 缓存最大个数 */
    private static final Integer CACHE_MAX_NUMBER = 100000;
    /** 缓存对象 */
    private static final Map<String, MapCacheObj> CACHE_OBJECT_MAP = new ConcurrentHashMap<>();
    /** 这个记录了缓存使用的最后一次的记录，最近使用的在最前面 */
    private static final List<String> CACHE_USE_LOG_LIST = new LinkedList<>();

    public static Map<String, MapCacheObj> getMap(){
        return CACHE_OBJECT_MAP;
    }

    /** 设置缓存 */
    public static void setCache(String cacheKey, Object cacheValue, long cacheTime) {
        Long ttlTime = null;
        if (cacheTime <= 0L) {
            if (cacheTime == -1L) {
                ttlTime = -1L;
            } else {
                return;
            }
        }
        checkSize();
        saveCacheUseLog(cacheKey);
        if (ttlTime == null) {
            ttlTime = System.currentTimeMillis() + cacheTime;
        }
        MapCacheObj cacheObj = new MapCacheObj(cacheValue, ttlTime);
        CACHE_OBJECT_MAP.putIfAbsent(cacheKey, cacheObj);
        LOGGER.info("设置成功 have set key :" + cacheKey);
    }

    /**
     * 设置缓存
     */
    public static void setCache(String cacheKey, Object cacheValue) {
        setCache(cacheKey, cacheValue, -1L);
    }

    /**
     * 获取缓存
     */
    public static Object getCache(String cacheKey) {
        if (checkCache(cacheKey)) {
            saveCacheUseLog(cacheKey);
            return CACHE_OBJECT_MAP.get(cacheKey).getCacheValue();
        }
        return null;
    }

    /** 根据key获取列表 */
    public static <T> List<T> getListByCacheKey(String cacheKey, Class<T> tClass){
       Object object = getCache(cacheKey);
       if(object == null){
           return null;
       }
       return JSON.parseArray((String)object, tClass);
    }

    public static <T> T getByCacheKey(String cacheKey, Class<T> tClass){
        Object object = getCache(cacheKey);
        if(object == null){
            return null;
        }
        return JSON.parseObject((String)object, tClass);
    }

    /**
     * 删除某个缓存
     */
    public static void deleteCache(String cacheKey) {
        Object cacheValue = CACHE_OBJECT_MAP.remove(cacheKey);
        if (cacheValue != null) {
            LOGGER.info("have delete key :" + cacheKey);
        }
    }

    /**
     * 判断缓存在不在,过没过期
     */
    private static boolean checkCache(String cacheKey) {
        MapCacheObj cacheObj = CACHE_OBJECT_MAP.get(cacheKey);
        if (cacheObj == null) {
            return false;
        }
        if (cacheObj.getTtlTime() == -1L) {
            return true;
        }
        if (cacheObj.getTtlTime() < System.currentTimeMillis()) {
            deleteCache(cacheKey);
            return false;
        }
        return true;
    }

    /**
     * 删除最近最久未使用的缓存
     */
    private static void deleteLRU() {
        LOGGER.info("delete Least recently used run!");
        String cacheKey = null;
        synchronized (CACHE_USE_LOG_LIST) {
            if (CACHE_USE_LOG_LIST.size() >= CACHE_MAX_NUMBER - 10) {
                cacheKey = CACHE_USE_LOG_LIST.remove(CACHE_USE_LOG_LIST.size() - 1);
            }
        }
        if (cacheKey != null) {
            deleteCache(cacheKey);
        }
    }

    /**
     * 删除过期的缓存
     */
    static void deleteTimeOut() {
        List<String> deleteKeyList = new LinkedList<>();
        for(Map.Entry<String, MapCacheObj> entry : CACHE_OBJECT_MAP.entrySet()) {
            if (entry.getValue().getTtlTime() < System.currentTimeMillis() && entry.getValue().getTtlTime() != -1L) {
                deleteKeyList.add(entry.getKey());
            }
        }
        //删除缓存
        for (String deleteKey : deleteKeyList) {
            deleteCache(deleteKey);
            //log数组信息同步清楚
            CACHE_USE_LOG_LIST.remove(deleteKey);
        }
    }

    /**
     * 检查大小
     * 当当前大小如果已经达到最大大小
     * 首先删除过期缓存，如果过期缓存删除过后还是达到最大缓存数目
     * 删除最久未使用缓存
     */
    private static void checkSize() {
        if (CACHE_OBJECT_MAP.size()  >= CACHE_MAX_NUMBER) {
            deleteTimeOut();
        }
        if (CACHE_OBJECT_MAP.size()  >= CACHE_MAX_NUMBER) {
            deleteLRU();
        }
    }

    /**
     * 保存缓存的使用记录
     */
    private static synchronized void saveCacheUseLog(String cacheKey) {
        synchronized (CACHE_USE_LOG_LIST) {
            CACHE_USE_LOG_LIST.remove(cacheKey);
            CACHE_USE_LOG_LIST.add(0,cacheKey);
        }
    }

    /** 获取缓存大小 */
    public static int getCacheSize(){
        return CACHE_OBJECT_MAP.size();
    }
}

