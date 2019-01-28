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

@RestController
@RequestMapping("spider-user/")
public class SpiderSearchController{

    private final static Logger logger = LoggerFactory.getLogger(SpiderSearchController.class);

    @Autowired
    private ISpiderUserInfoService userInfoService;

    @RequestMapping(value = "/findById", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public SpiderUserInfoServiceDto getSpiderUrl(){
//        List<String> urlList = spiderUrlFacade.getToScrawlUrlList();
        SpiderUserInfoServiceDto userInfoDTO = userInfoService.findById(new Long(1));
        logger.info("消费方打印----调用输出----userInfoDTO:{}", userInfoDTO);
        return userInfoDTO;
    }
}
