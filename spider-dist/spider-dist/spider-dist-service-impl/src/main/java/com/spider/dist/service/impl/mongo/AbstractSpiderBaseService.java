package com.spider.search.service.impl.mongo;

import com.mongodb.client.MongoDatabase;
import com.spider.search.service.api.mongo.SpiderBaseService;

public abstract class AbstractSpiderBaseService implements SpiderBaseService {

    protected MongoDatabase mongoDatabase;

    @Override
    public void setDatabase(MongoDatabase mongoDatabase){
        this.mongoDatabase = mongoDatabase;
    }
}
