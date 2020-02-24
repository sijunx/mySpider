package com.spider.base.kafka.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 */
public class MyThreadPoolExcutorUtil {

        private MyThreadPoolExcutorUtil(){}

        private static final int CORE_POOL_SIZE = 100;

        private static final int MAXIMUM_POOL_SIZE = 400;

        private static final long KEEP_ALIVE_TIME = 10;

        private static final ExecutorService executorService =  new ThreadPoolExecutor(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new ArrayBlockingQueue(2000), new ThreadFactoryBuilder().setNameFormat("MyThreadPoolExcutorUtil").build(), new ThreadPoolExecutor.AbortPolicy());

        public static ExecutorService generateTaskExcutor() {
            return executorService;
        }
    }




