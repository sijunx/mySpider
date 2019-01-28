package com.spider.user.service.impl;

import com.spider.user.service.api.ISpiderUserInfoService;
import com.spider.user.service.dto.SpiderUserInfoServiceDto;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(locations = {"classpath:context/*.xml"})
public class SpiderUserInfoServiceImplTest {

    private final static Logger logger = LoggerFactory.getLogger(SpiderUserInfoServiceImplTest.class);

    @Autowired
    private ISpiderUserInfoService spiderUserInfoService;

    @Test
    public void testJunitTest(){

        List<SpiderUserInfoServiceDto> spiderUserInfoServiceDtoList = spiderUserInfoService.findListByPhone("13999999999");
        System.out.println("测试"+spiderUserInfoServiceDtoList);
        logger.info("获取结果大小:{}", CollectionUtils.isEmpty(spiderUserInfoServiceDtoList)?0:spiderUserInfoServiceDtoList.size());
    }

}