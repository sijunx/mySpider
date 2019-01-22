package com.spider.search.facade.impl;

import com.mongodb.client.MongoDatabase;
import com.spider.base.mongo.MongoConnUtil;
import com.spider.search.facade.api.ISpiderUrlFacade;
import com.spider.search.service.api.mongo.SpiderUrlService;
import org.apache.commons.collections.CollectionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpiderUrlFacadeImpl implements ISpiderUrlFacade {

    private final static Logger logger = LoggerFactory.getLogger(SpiderUrlFacadeImpl.class);

    @Autowired
    private SpiderUrlService spiderUrlService;

    @Override
    public List<String> getToScrawlUrlList(){
        MongoConnUtil mongoConnUtil = new MongoConnUtil();
        MongoDatabase mongoDatabase = mongoConnUtil.initConn();
        spiderUrlService.setDatabase(mongoDatabase);
        Document doc = new Document();
        doc.put("deleteFlag", "0");
        doc.put("deep", 8);
        List<Document> urlList = spiderUrlService.findList(doc);
        List<String> urlRetList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(urlList)){
            for(Document document:urlList){
                String urlStr = document.getString("url");
                urlRetList.add(urlStr);
            }
        }
        mongoConnUtil.connClose();
        return urlRetList;
    }
}
