package com.spider.dist.service.api;

public interface ISpiderAuditService {

    boolean duplicateCheckPass(String url);

    boolean duplicateTwoMoreCheckPass(String url);

    boolean blackWordsExistsCheckPass(String word);

    boolean titleCheckPass(String title);

    boolean summaryCheckPass(String txt);
}
