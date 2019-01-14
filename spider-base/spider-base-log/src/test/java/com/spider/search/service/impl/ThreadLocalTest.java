package com.spider.search.service.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by SK_ZARD on 2018/10/14.
 */
public class ThreadLocalTest {


    private final static Logger logger = LoggerFactory.getLogger(ThreadLocalTest.class);

    public static void main(String[] arg){

        try{
            BigDecimal big = new BigDecimal(1).divide(new BigDecimal(0));
        }catch (Exception e){
//            e.printStackTrace();
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }



//        MyTest001 myTest001 = new MyTest001();
//        MyThread001 thread01 = new MyThread001(myTest001);
//        MyThread001 thread02 = new MyThread001(myTest001);
//
//        thread01.run();
//        thread02.run();
    }


}
