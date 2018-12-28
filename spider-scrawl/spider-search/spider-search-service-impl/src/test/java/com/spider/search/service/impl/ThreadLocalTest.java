package com.spider.search.service.impl;

/**
 * Created by SK_ZARD on 2018/10/14.
 */
public class ThreadLocalTest {


    public static void main(String[] arg){

        MyTest001 myTest001 = new MyTest001();
        MyThread001 thread01 = new MyThread001(myTest001);
        MyThread001 thread02 = new MyThread001(myTest001);

        thread01.run();
        thread02.run();
    }


}
