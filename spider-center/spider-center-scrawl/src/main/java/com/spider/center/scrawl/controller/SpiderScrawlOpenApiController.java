package com.spider.center.scrawl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.spider.base.dto.SpiderResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("spider-scrawl/open-api")
public class SpiderScrawlOpenApiController extends SpiderBaseController{

    private final static Logger logger = LoggerFactory.getLogger(SpiderScrawlOpenApiController.class);

//    @Autowired
//    private ISpiderUrlFacade spiderUrlFacade;

    @RequestMapping(value = "/getSpiderUrl", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public SpiderResponseBody<List<String>> getSpiderUrl(){
//        List<String> urlList = spiderUrlFacade.getToScrawlUrlList();
        List<String> urlList = new ArrayList<>();
        urlList.add("https://blog.csdn.net/weixin_42861564/article/details/81748362");
        urlList.add("https://ask.csdn.net/questions/263733");
        urlList.add("https://blog.csdn.net/ly_LAN/article/details/82892832");
        logger.info("------获取到的数据:{}", JSON.toJSONString(urlList));
        return SpiderResponseBody.buildSucessResponse(urlList);
    }

    @RequestMapping(value = "/recvSpiderUrl", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public SpiderResponseBody recvSpiderUrl(@RequestParam(required = false) String data){
        JSONArray jsonArray = JSON.parseArray(data);
        logger.info("-------------收到推送过来的URL数据啦-------------------------------------------------------------");
        logger.info("data:{} ", data);
        logger.info("jsonArray:{}", jsonArray);
        //SpiderKafkaProducerClient.sendMessage(SpiderTopicConstant.SPIDER_DIST_TOPIC, data);

        return SpiderResponseBody.buildSucessResponse(null);
    }
}
