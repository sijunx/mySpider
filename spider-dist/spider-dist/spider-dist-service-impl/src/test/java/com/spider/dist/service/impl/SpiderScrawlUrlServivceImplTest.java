package com.spider.dist.service.impl;

import com.spider.dist.service.dto.SpiderUrlDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpiderScrawlUrlServivceImplTest {

    private final static Logger logger = LoggerFactory.getLogger(SpiderScrawlUrlServivceImpl.class);

    public static void main(String[] arg){
        SpiderScrawlUrlServivceImpl spiderScrawlUrlServivce = new SpiderScrawlUrlServivceImpl();
        SpiderUrlDTO spiderUrlDTO = new SpiderUrlDTO();
        spiderUrlDTO.setUrl("https://hao.360.cn/?a1004");
        spiderScrawlUrlServivce.startScrawl(spiderUrlDTO);
        logger.info("spiderUrlDTO:{}", spiderUrlDTO);
    }
}
