package com.spider.dist.facade.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.spider.base.http.SpiderHttpUtil;
import com.spider.dist.service.constant.SpiderServerConstant;
import com.spider.dist.service.dto.SpiderUrlDTO;
import com.spider.dist.service.enums.SpiderHttpCodeEnum;
import com.spider.dist.service.util.SpiderDistScrawlContext;
import com.spider.dist.service.constant.SpiderTicketConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 获取通过的数据，并推送到数据中心，删除审核未通过的数据
 * @author xusijun
 * @date 2019.1.23
 */
public class SpiderDistPushThread implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(SpiderDistPushThread.class);

    /** 推送url信息至数据中心 */
    @Override
    public void run(){
        try {
            while(true) {
                Thread.sleep(30*1000);
                //  获取处理通过的数据
                List<SpiderUrlDTO> spiderUrlDTOList = SpiderDistScrawlContext.getAndSetPassedUrlList();
                logger.info("获取推送数据:{}", spiderUrlDTOList);
                String dataStr = JSON.toJSONString(spiderUrlDTOList);
                if(CollectionUtils.isEmpty(spiderUrlDTOList)){
                    continue;
                }
                logger.info("推送线程开始-------------------------------------------------------");
                //  推送数据到数据中心(若数据推送失败，则置位发送失败)
                String url = SpiderServerConstant.getPushUrl();
                //  header
                Map<String, String> headMap = Maps.newHashMapWithExpectedSize(6);
                headMap.put("signature", SpiderTicketConstant.getTicket());
                Map<String, String> bodyMap = Maps.newHashMapWithExpectedSize(6);
                bodyMap.put("data", dataStr);
                String result;
                try {
                    result = SpiderHttpUtil.sendPostJson(url, headMap, bodyMap, "UTF-8", 30 * 1000);
                } catch (IOException e) {
                    logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
                    throw new RuntimeException(e);
                }
                logger.info("返回结果:{}", result);
                //  若推送成功，则删除URL列表数据
                JSONObject jsonObject = JSONObject.parseObject(result);
                String code = jsonObject.getString("code");
                if (Objects.equals(code, SpiderHttpCodeEnum.SUCCESS.getCode().toString())) {
                    SpiderDistScrawlContext.deleteUrlList(spiderUrlDTOList);
                }
            }
        }catch (Exception e){
            logger.warn("从数据中心拉去数据出现异常 e:{}", ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        }
    }
}
