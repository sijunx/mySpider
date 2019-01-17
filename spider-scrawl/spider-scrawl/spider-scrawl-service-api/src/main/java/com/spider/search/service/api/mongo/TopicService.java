package com.spider.search.service.api.mongo;

import org.bson.Document;

import java.util.List;

public interface TopicService extends SpiderBaseService {

    Document create(Document document);

    List<Document> findList(Document document);

    Document findOne(Document document);

    Document modify(Document document);
}
