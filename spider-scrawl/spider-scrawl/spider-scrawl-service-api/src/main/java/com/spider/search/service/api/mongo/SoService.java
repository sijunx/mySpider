package com.spider.search.service.api.mongo;

import org.bson.Document;

import java.util.List;

public interface SoService extends SpiderBaseService {

    List<Document> soBySentence(String sentence);

}
