package com.spider.search.service.api.mongo;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public interface ImageService extends SpiderBaseService {

    void setDatabase(MongoDatabase mongoDatabase);

    Document create(Document document);

    Document modify(Document document);

    Document findOne(Document document);

    List<Document> findList(Document document);

}
