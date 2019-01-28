package com.spider.user.provider.service;

import com.spider.user.service.api.ISpiderUserInfoService;
import com.spider.user.service.dto.SpiderUserInfoServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceProvider implements ISpiderUserInfoService {

    private final static Logger logger = LoggerFactory.getLogger(UserInfoServiceProvider.class);

    @Override
    public SpiderUserInfoServiceDto findById(Long id){
        logger.info("-------------------------------我是提供方----------------------");
        System.out.println("我是提供方");
        SpiderUserInfoServiceDto userInfoDTO = new SpiderUserInfoServiceDto();
        userInfoDTO.setName("张胜男");
        return userInfoDTO;
    }
}
