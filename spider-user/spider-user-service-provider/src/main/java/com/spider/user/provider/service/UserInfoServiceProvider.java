package com.spider.user.provider.service;

import com.spider.user.service.api.ISpiderUserInfoService;
import com.spider.user.service.dto.SpiderUserInfoServiceDto;
import com.spider.user.service.impl.SpiderUserInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoServiceProvider implements ISpiderUserInfoService {

    private final static Logger logger = LoggerFactory.getLogger(UserInfoServiceProvider.class);

    @Autowired
    private SpiderUserInfoServiceImpl spiderUserInfoService;

    @Override
    public List<SpiderUserInfoServiceDto> findListByPhone(String phone){
        logger.info("-------------------------------我是提供方----------------------");
        return spiderUserInfoService.findListByPhone(phone);
    }

    @Override
    public List<SpiderUserInfoServiceDto> findListByName(String name){
        logger.info("-------------------------------我是提供方----------------------");
        System.out.println("我是提供方");
        return spiderUserInfoService.findListByName(name);
    }
}
