package com.spider.search.service.api.mongo;

public interface AuditService extends SpiderBaseService {

    String duplicateCheck(String url);

    String duplicateTwoMoreCheck(String url);

    String blackWordsExists(String word);

    String titleCheck(String title);

    String txtCheck(String txt);

}
