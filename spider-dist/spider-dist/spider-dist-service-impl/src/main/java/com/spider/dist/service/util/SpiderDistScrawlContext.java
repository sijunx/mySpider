package com.spider.dist.service.util;

import com.alibaba.fastjson.JSON;
import com.spider.dist.service.dto.SpiderUrlDTO;
import com.spider.dist.service.enums.SpiderUrlStatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SpiderDistScrawlContext {

    private final static Logger logger = LoggerFactory.getLogger(SpiderDistScrawlContext.class);

    private static Map<String, SpiderUrlDTO> URL_MAP = new HashMap<>();

    /** 存入URL_MAP信息*/
    public static void putUrlMap(String key, SpiderUrlDTO spiderUrlDTO){
        ReentrantLock reentrantPutLock = new ReentrantLock();
        reentrantPutLock.tryLock();

        try {
            SpiderUrlDTO value = URL_MAP.get(key);
            if(value == null){
                URL_MAP.put(key, spiderUrlDTO);
                logger.info("放入URL_MAP数据成功 key:{} value:{}", key, spiderUrlDTO);
            }
        }finally {
            reentrantPutLock.unlock();
        }
    }

    /** 获取开始状态的URL信息 */
    public static SpiderUrlDTO getAndSetStartUrl(){
        //  输出当前Map内的内容
        if(URL_MAP!=null && URL_MAP.size()>0){
            logger.info("------------------------------------Map中的内容为---------------------------------------");
            for(Map.Entry entry:URL_MAP.entrySet()){
                SpiderUrlDTO spiderUrlDTO = (SpiderUrlDTO)entry.getValue();
                String valueStr = new StringBuilder().append(spiderUrlDTO.getStatus()).append(" ").append(spiderUrlDTO.getUrl()).append(spiderUrlDTO.getKeyWords()).append(spiderUrlDTO.getTitle()).toString();
                logger.info("-------------------key:{}  value:{}", entry.getKey(), valueStr);
            }
            logger.info("------------------------------------Map中的内容为---------------------------------------");
        }
        SpiderUrlDTO spiderUrlRet = null;
        ReentrantLock reentrantPutLock = new ReentrantLock();
        reentrantPutLock.lock();
        try{
            List<SpiderUrlDTO> spiderUrlDTOList = getUrlByStatus(SpiderUrlStatusEnum.START.getCode(), 1);
            if(!CollectionUtils.isEmpty(spiderUrlDTOList)){
                String urlId = spiderUrlDTOList.get(0).getUrlId();
                spiderUrlRet = URL_MAP.get(urlId);
                spiderUrlRet.setStatus(SpiderUrlStatusEnum.PROCESSSING.getCode());
            }
        }finally {
            reentrantPutLock.unlock();
        }
        logger.info("获取开始状态的URL信息 spiderUrlRet:{}", spiderUrlRet);
        return spiderUrlRet;
    }

    /** 根据状态获取URL列表 */
    private static List<SpiderUrlDTO> getUrlByStatus(Integer status, Integer limit){
        List<SpiderUrlDTO> spiderUrlRetList = new ArrayList<>();
        //  遍历搜索
        if(URL_MAP==null || URL_MAP.size()<=0){
            return Collections.emptyList();
        }
        for(Map.Entry<String, SpiderUrlDTO> entry:URL_MAP.entrySet()){
            SpiderUrlDTO spiderUrlDTO = entry.getValue();
            if(spiderUrlDTO.getStatus()!=null && spiderUrlDTO.getStatus()== status){
                if(spiderUrlRetList.size() > limit){
                    break;
                }
                spiderUrlRetList.add(spiderUrlDTO);
            }
        }
        logger.info("根据状态获取到数据 status:{} spiderUrlRetList.size:{}", status, spiderUrlRetList.size());
        return spiderUrlRetList;
    }

    /** 获取已经通过的URL列表，并置为可删除状态 */
    public static List<SpiderUrlDTO> getAndSetPassedUrlList(){
        List<SpiderUrlDTO> spiderUrlDTOList = null;
        ReentrantLock reentrantGetLock = new ReentrantLock();
        reentrantGetLock.lock();
        try {
            spiderUrlDTOList = getUrlByStatus(SpiderUrlStatusEnum.PASSED.getCode(), 5);
            if(!CollectionUtils.isEmpty(spiderUrlDTOList)){
                for(SpiderUrlDTO spiderUrlDTO:spiderUrlDTOList){
                    SpiderUrlDTO spiderUrl = URL_MAP.get(spiderUrlDTO.getUrlId());
                    spiderUrl.setStatus(SpiderUrlStatusEnum.CAN_DELETE.getCode());
                }
            }
        }finally {
            reentrantGetLock.unlock();
        }
        return spiderUrlDTOList;
    }

    /** 根据UrlList删除URL_MAP对应数据 */
    public static void deleteUrlList(List<SpiderUrlDTO> spiderUrlList){
        ReentrantLock reentrantGetLock = new ReentrantLock();
        reentrantGetLock.lock();
        try {
            if(!CollectionUtils.isEmpty(spiderUrlList)){
                for(SpiderUrlDTO spiderUrlDTO:spiderUrlList){
                    URL_MAP.remove(spiderUrlDTO.getUrlId());
                }
            }
        }finally {
            reentrantGetLock.unlock();
        }
    }

    public static Integer getSize(){
        return URL_MAP.size();
    }
}
