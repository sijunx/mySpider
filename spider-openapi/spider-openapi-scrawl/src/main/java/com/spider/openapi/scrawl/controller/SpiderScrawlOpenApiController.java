package com.spider.openapi.scrawl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("spider-scrawl/open-api")
public class SpiderScrawlOpenApiController {

    private final static Logger logger = LoggerFactory.getLogger(SpiderScrawlOpenApiController.class);


    @RequestMapping(value={"list"}, produces={"application/json;charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public void list(ServletRequest request, HttpServletResponse response){

    }

}
