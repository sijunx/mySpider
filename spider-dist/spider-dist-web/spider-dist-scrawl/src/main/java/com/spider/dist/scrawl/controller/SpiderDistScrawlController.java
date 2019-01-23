package com.spider.dist.scrawl.controller;

import com.spider.dist.facade.api.ISpiderDistScrawlFacade;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spider-dist/scrawl")
public class SpiderDistScrawlController {

    private final static Logger logger = LoggerFactory.getLogger(SpiderDistScrawlController.class);

    @Autowired
    private ISpiderDistScrawlFacade spiderDistScrawlFacade;


    @RequestMapping(value={"start"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public void spiderCal(){
        try{
            logger.info("爬虫计算开始---------------------------------------------");
            this.spiderDistScrawlFacade.start();
            logger.info("爬虫计算结束---------------------------------------------");
        }catch (Exception e){
            logger.warn("爬虫启动失败 e:{}", ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        }
    }
}
