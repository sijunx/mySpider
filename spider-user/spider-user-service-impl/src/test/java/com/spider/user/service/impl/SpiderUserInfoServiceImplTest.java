package com.spider.user.service.impl;

import com.spider.user.service.api.ISpiderUserInfoService;
import com.spider.user.service.dao.api.ISpiderUserInfoDao;
import com.spider.user.service.dao.entity.SpiderUserInfoEntity;
import com.spider.user.service.dto.SpiderUserInfoServiceDto;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SpiderUserInfoServiceImplTest extends SpiderBaseTest{

    private final static Logger logger = LoggerFactory.getLogger(SpiderUserInfoServiceImplTest.class);

    @Autowired
    private ISpiderUserInfoService spiderUserInfoService;
    @Autowired
    private ISpiderUserInfoDao spiderUserInfoDao;


    @Test
    public void findListByPhoneTest(){
        List<SpiderUserInfoServiceDto> spiderUserInfoServiceDtoList = spiderUserInfoService.findListByPhone("13999999999");
        System.out.println("测试"+spiderUserInfoServiceDtoList);
        logger.info("获取结果大小:{}", CollectionUtils.isEmpty(spiderUserInfoServiceDtoList)?0:spiderUserInfoServiceDtoList.size());
    }

    @Test
    public void insertTest(){
        SpiderUserInfoEntity spiderUserInfoEntity = new SpiderUserInfoEntity();
        spiderUserInfoEntity.setId(new Long(100));
        spiderUserInfoEntity.setName("王龙");
        spiderUserInfoEntity.setIdCard("31027661997282736");
        spiderUserInfoEntity.setPhone("18856564736");
        spiderUserInfoDao.insert(spiderUserInfoEntity);
    }












}