package com.spider.search.service.impl;

/**
 * Created by SK_ZARD on 2018/10/14.
 */
public class MyThread001 extends Thread{

    private MyTest001 myTest001 = null;

    MyThread001(MyTest001 myTest){
        myTest001 = myTest;
    }

    public void run(){

        for(int icount=0; icount<10; icount++) {
            myTest001.addCounter();
        }

    }
}
