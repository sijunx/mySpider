package com.spider.user.zipkin.consumer.controller;

import com.spider.user.service.api.ISpiderTest01Service;
import com.spider.user.service.api.ISpiderTest02Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spider-user/")
public class SpiderUserInfoController {

    private final static Logger logger = LoggerFactory.getLogger(SpiderUserInfoController.class);

    @Autowired
    private ISpiderTest01Service test01Service;
    @Autowired
    private ISpiderTest02Service test02Service;

    @RequestMapping(value = "/findById", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getSpiderUrl(){
        logger.info("消费方打印----调用输出----");
        test01Service.findById();
        test02Service.findById();
        try {
            Class<?> claz01 = Thread.currentThread().getContextClassLoader().loadClass("SpiderUserInfoServiceImpl");
            Class<?> claz02 = Thread.currentThread().getContextClassLoader().loadClass("UserInfoServiceProvider");

            logger.info("claz01:"+claz01);
            logger.info("claz02:"+claz02);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "---------------------axxxx---------------";
    }
}
