package com.spider.search.service.api.mongo;

import com.mongodb.client.MongoDatabase;
import com.spider.search.service.dto.InputDataServiceDTO;
import org.bson.Document;

import java.util.List;

public interface InputDataService extends SpiderBaseService {

    void setDatabase(MongoDatabase mongoDatabase);

    Document create(Document document);

    List<Document> findList(Document document);

    List<Document> findListByHotsDesc();

    List<Document> findByNotEqualUrlId(Document document);

    Document findOne(Document document);

    Document modify(Document document);

    List<InputDataServiceDTO> searchByKeyWord(String keyWord, Integer bottomId);
}
