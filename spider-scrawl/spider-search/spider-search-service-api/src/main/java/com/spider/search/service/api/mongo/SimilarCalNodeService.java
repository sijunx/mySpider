package com.spider.search.service.api.mongo;

import org.bson.Document;

public interface SimilarCalNodeService extends SpiderBaseService {

    //  节点01：根据url获取数据，存入data表
    //  节点02：根据url对应的data，抽取关键词
    //  节点03：根据关键词，计算摘要

    //  节点01：计算url对应data和主题之间的相似度
    //  节点02：根据url与主题之间的相似度，计算热点

    void startFlow(String urlId);

    void endFlow(String urlId);

    Document getFlow(String urlId);

    void createFlow(String urlId);
}
