package com.spider.base.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SpiderThreadPoolExcutorUtil {

    private static final ExecutorService executorService =  new ThreadPoolExecutor(5,10, 1000, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(), new ThreadFactoryBuilder().setNameFormat("SpiderExcutor").build(), new ThreadPoolExecutor.AbortPolicy());

    public static ExecutorService getThreadPoolExcutor(){
        return executorService;
    }
}
