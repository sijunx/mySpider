package com.spider.search.service.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by SK_ZARD on 2018/10/14.
 */
public class MyTest001 implements Serializable{


    private final static Logger logger = LoggerFactory.getLogger(MyTest001.class);


    private ThreadLocal<Integer> myThreadLocal = new ThreadLocal();

    public void addCounter(){
        if(myThreadLocal.get()==null){
            myThreadLocal.set(0);
        }else {
            myThreadLocal.set(myThreadLocal.get() + 1);
        }

        System.out.println(Thread.currentThread().getName()+" threadLocal.get:"+myThreadLocal.get());
    }

    public ThreadLocal<Integer> getMyThreadLocal() {
        return myThreadLocal;
    }

    public void setMyThreadLocal(ThreadLocal<Integer> myThreadLocal) {
        this.myThreadLocal = myThreadLocal;
    }
}
