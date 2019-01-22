package com.spider.openapi.scrawl.controller;

import com.spider.base.dto.SpiderResponseBody;
import com.spider.search.facade.api.ISpiderUrlFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spider-scrawl/open-api")
public class SpiderScrawlOpenApiController extends SpiderBaseController{

    private final static Logger logger = LoggerFactory.getLogger(SpiderScrawlOpenApiController.class);

    @Autowired
    private ISpiderUrlFacade spiderUrlFacade;

    @RequestMapping(value = "/getSpiderUrl", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public SpiderResponseBody<List<String>> getSpiderUrl(){
        List<String> urlList = spiderUrlFacade.getToScrawlUrlList();
        return SpiderResponseBody.buildSucessResponse(urlList);
    }
}
