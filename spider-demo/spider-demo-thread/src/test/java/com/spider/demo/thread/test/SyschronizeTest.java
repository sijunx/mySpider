package com.spider.demo.thread.test;

import java.util.Random;

public class SyschronizeTest {

    private QuickSortTest quickSortTest = new QuickSortTest();

   public synchronized static void fun01()throws  Exception{

           int icount = 0;
           Random random = new Random();
           icount = 9;
           System.out.println("fun01进入 线程名:"+Thread.currentThread().getName());
           Thread.sleep(icount*1000);
           System.out.println("fun01结束 线程名:"+Thread.currentThread().getName());

       return ;
   }

    public synchronized Integer fun02()throws  Exception{

            Random random = new Random();
            int icount = 12;
            System.out.println("fun02开始 线程名:" + Thread.currentThread().getName());
            Thread.sleep(icount * 1000);
            System.out.println("fun02结束 线程名:" + Thread.currentThread().getName());

        return 1;
   }
}
