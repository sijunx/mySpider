package com.spider.search.service.impl;

import java.io.Serializable;

/**
 * Created by SK_ZARD on 2018/10/14.
 */
public class MyTest001 implements Serializable{

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
