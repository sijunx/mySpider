package com.spider.user.zipkin.provider.service;

import com.spider.user.service.api.ISpiderTest01Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Test01ServiceProvider implements ISpiderTest01Service {

    private final static Logger logger = LoggerFactory.getLogger(Test01ServiceProvider.class);

    @Override
    public void findById(){
        logger.info("--------------------我是提供方 Test01ServiceProvider-------------");
    }
}
