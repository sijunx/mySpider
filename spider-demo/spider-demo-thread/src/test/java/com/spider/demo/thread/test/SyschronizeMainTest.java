package com.spider.demo.thread.test;

import java.util.concurrent.*;

public class SyschronizeMainTest {

    public static void main(String[] arg)throws Exception{
        SyschronizeTest syschronizeTest = new SyschronizeTest();
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
        ExecutorService executorService = new ThreadPoolExecutor(5,5, 5,TimeUnit.SECONDS, workQueue);

        MyThread01 myThread01 = new MyThread01(syschronizeTest);
        Thread thread = new Thread(myThread01);
        thread.start();

        System.out.println("xxxx");

        MyThread02 myThread02 = new MyThread02(syschronizeTest);
        Thread thread001 = new Thread(myThread02);
        thread001.start();


    }
}
