package com.spider.dist.facade.thread;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.spider.base.http.SpiderHttpUtil;
import com.spider.dist.service.dto.SpiderUrlDTO;
import com.spider.dist.service.enums.SpiderUrlStatusEnum;
import com.spider.dist.service.util.SpiderDistScrawlContext;
import com.spider.dist.service.constant.SpiderServerConstant;
import com.spider.dist.service.constant.SpiderTicketConstant;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SpiderDistPollThread implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(SpiderDistPollThread.class);

    /** 爬取url */
    @Override
    public void run(){
        try {
            //  启动线程不断的从数据中心拉去数据，放入到Map中
            //  启动线程爬取、加工数据
            //  启动线程推送数据到数据中心

            //  Map存储经过爬取筛选的url信息(5个url推送一次，推送完成后删除信息)
            //  Map存储urlId的信息：url、状态、当前节点、有效标记、摘要信息
            //  流程：爬取摘要信息->获取关键词信息->信息审核
            //  推送信息：url信息、摘要信息、关键词信息
            String url = SpiderServerConstant.getPollUrl();
            //  header
            Map<String, String> headMap = Maps.newHashMapWithExpectedSize(6);
            headMap.put("signature", SpiderTicketConstant.getTicket());
            while (true) {
                Thread.sleep(10 * 1000);
                if(SpiderDistScrawlContext.getSize()>=1000){
                    continue;
                }
                logger.info("拉取开始-------------------------------------------------------");
                String result;
                try {
                    result = SpiderHttpUtil.sendPostJson(url, headMap, new HashMap<String, String>(), "UTF-8", 30 * 1000);
                } catch (IOException e) {
                    logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
                    throw new RuntimeException(e);
                }
                logger.info("返回结果:{}", result);
                //  解析结果，存入URL_MAP中
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONArray jsonArray = JSONObject.parseArray(jsonObject.get("data").toString());
                if (jsonArray != null && jsonArray.size() > 0) {
                    for (int icount = 0; icount < jsonArray.size(); icount++) {
                        String urlStr = jsonArray.getString(icount);
                        String uidStr = UUID.randomUUID().toString();
                        //  对象数据
                        SpiderUrlDTO spiderUrlDTO = new SpiderUrlDTO();
                        spiderUrlDTO.setUrlId(uidStr);
                        spiderUrlDTO.setUrl(urlStr);
                        spiderUrlDTO.setStatus(SpiderUrlStatusEnum.START.getCode());
                        SpiderDistScrawlContext.putUrlMap(uidStr, spiderUrlDTO);
                    }
                }
                logger.info("拉取结束-------------------------------------------------------");
            }
        }catch (Exception e){
            logger.warn("从数据中心拉去数据出现异常 e:{}", ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        }
    }
}
