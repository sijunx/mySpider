package com.spider.user.zipkin.provider02.service;

import com.spider.user.service.api.ISpiderTest02Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Test02ServiceProvider implements ISpiderTest02Service {

    private final static Logger logger = LoggerFactory.getLogger(Test02ServiceProvider.class);

    @Override
    public void findById(){
        logger.info("-------------------------------我是提供方 Test02ServiceProvider ----------------------");
    }

}
