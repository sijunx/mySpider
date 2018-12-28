package com.spider.search.service.api.mongo;

import com.mongodb.client.MongoDatabase;

public interface SpiderBaseService {

    void setDatabase(MongoDatabase mongoDatabase);

}
