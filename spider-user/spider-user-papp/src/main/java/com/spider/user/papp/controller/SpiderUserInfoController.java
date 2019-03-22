package com.spider.user.papp.controller;

import com.spider.user.service.api.ISpiderUserInfoService;
import com.spider.user.service.dto.SpiderUserInfoServiceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spider-user/")
public class SpiderUserInfoController {

    private final static Logger logger = LoggerFactory.getLogger(SpiderUserInfoController.class);

    @Autowired
    private ISpiderUserInfoService userInfoService;

    @RequestMapping(value = "/findById", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public List<SpiderUserInfoServiceDto> getSpiderUrl()throws Exception{


//        Class<?> claz01 = Thread.currentThread().getContextClassLoader().loadClass("spiderUserInfoServiceImpl");
//        Class<?> claz02 = Thread.currentThread().getContextClassLoader().loadClass("userInfoServiceProvider");



//        List<String> urlList = spiderUrlFacade.getToScrawlUrlList();
        List<SpiderUserInfoServiceDto> userInfoServiceDtoList = userInfoService.findListByPhone("13999999999");
//        logger.info("消费方打印----调用输出----userInfoDTO:{}", userInfoDTO);
//        return userInfoDTO;

        List<SpiderUserInfoServiceDto> userInfoServiceNameList = userInfoService.findListByName("张三");
        userInfoServiceDtoList.addAll(userInfoServiceNameList);
        logger.info("消费方打印----调用输出----");
        return userInfoServiceDtoList;
    }
}
