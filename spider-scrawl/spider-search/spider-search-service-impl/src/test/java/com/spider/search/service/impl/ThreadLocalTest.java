package com.spider.search.service.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SK_ZARD on 2018/10/14.
 */
public class ThreadLocalTest {


    private final static Logger logger = LoggerFactory.getLogger(ThreadLocalTest.class);

    public static void main(String[] arg){

        int i = 24>>>3;
        Map map = new HashMap();

        logger.info("i:{}", i);

//        try{
//            BigDecimal big = new BigDecimal(1).divide(new BigDecimal(0));
//        }catch (Exception e){
//            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
//        }



//        MyTest001 myTest001 = new MyTest001();
//        MyThread001 thread01 = new MyThread001(myTest001);
//        MyThread001 thread02 = new MyThread001(myTest001);
//
//        thread01.run();
//        thread02.run();
    }


}
