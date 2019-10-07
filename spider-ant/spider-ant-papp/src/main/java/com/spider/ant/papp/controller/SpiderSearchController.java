package com.spider.ant.papp.controller;

import com.spider.ant.service.api.MyDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("spider")
public class SpiderSearchController{

    private final static Logger logger = LoggerFactory.getLogger(SpiderSearchController.class);

    @Autowired
    private MyDataService myDataService;

    @RequestMapping(value={"start"}, produces={"application/json;charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Long start(ServletRequest request, HttpServletResponse response) {
        myDataService.start();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return null;
    }
}
