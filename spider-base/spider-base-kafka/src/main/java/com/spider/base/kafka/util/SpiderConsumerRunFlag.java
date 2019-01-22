package com.spider.base.kafka.util;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpiderConsumerRunFlag {

    private static AtomicBoolean runFlag = new AtomicBoolean(true);

    public static AtomicBoolean getRunFlag() {
        return runFlag;
    }

    public static void setRunFlag(AtomicBoolean runningFlag) {
        runFlag = runningFlag;
    }
}
