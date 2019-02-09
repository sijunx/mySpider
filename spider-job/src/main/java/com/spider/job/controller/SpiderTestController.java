package com.spider.job.controller;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spider/")
public class SpiderTestController {

    private final static Logger logger = LoggerFactory.getLogger(SpiderTestController.class);

    @Autowired
    private ISpiderMessageProcessor spiderMessageProcessor;

    @RequestMapping(value = "/findById", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void getSpiderUrl(){

        logger.info("--------测试---调用输出----");
        return ;
    }
}
