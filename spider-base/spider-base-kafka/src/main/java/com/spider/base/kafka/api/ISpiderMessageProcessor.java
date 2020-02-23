package com.spider.base.kafka.api;

public interface ISpiderMessageProcessor {

    boolean messageProcess(String message);
}
