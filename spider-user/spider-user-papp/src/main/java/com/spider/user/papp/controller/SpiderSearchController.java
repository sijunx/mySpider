package com.spider.search.papp.controller;

import com.alibaba.fastjson.JSON;
import com.spider.base.dto.SpiderResponseBody;
import com.spider.user.service.api.IUserInfoService;
import com.spider.user.service.dto.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("spider-user/")
public class SpiderSearchController{

    private final static Logger logger = LoggerFactory.getLogger(SpiderSearchController.class);

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping(value = "/findById", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public SpiderResponseBody<UserInfoDTO> getSpiderUrl(){
//        List<String> urlList = spiderUrlFacade.getToScrawlUrlList();
        UserInfoDTO userInfoDTO = userInfoService.findById(new Long(1));
        logger.info("消费方打印----调用输出----userInfoDTO:{}", JSON.toJSONString(userInfoDTO));
        return SpiderResponseBody.buildSucessResponse(userInfoDTO);
    }
}
