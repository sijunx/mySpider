package com.spider.search.service.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.spider.search.service.api.mongo.TopicService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl extends AbstractSpiderBaseService implements TopicService {

    private final static Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Override
    public Document create(Document document) {
        try {
            MongoCollection<Document> collection01 = mongoDatabase.getCollection("topicUrl");
            Document doc03 = new Document();
            if(document.get("urlId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))){
                doc03.put("urlId",  String.valueOf(document.get("urlId")));
            }else{
                return null;
            }
            if(document.get("topicCode")!=null && StringUtils.isNotBlank(String.valueOf(document.get("topicCode")))){
                doc03.put("topicCode",  String.valueOf(document.get("topicCode")));
            }
            collection01.insertOne(doc03);
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document;
    }

    @Override
    public Document modify(Document document) {
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("topicUrl");
            if (document.get("topicCode") != null || StringUtils.isNotBlank(String.valueOf(document.get("topicCode")))){
                collection04.updateMany(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("topicCode", String.valueOf(document.get("topicCode")))));
            }
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document;
    }

    @Override
    public Document findOne(Document document) {
        Document document02 = null;
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("topicUrl");
            BasicDBObject query04 = new BasicDBObject();
            if(document.get("urlId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))){
                query04.put("urlId", String.valueOf(document.get("urlId")));
            }
            if(document.get("topicCode")!=null && StringUtils.isNotBlank(String.valueOf(document.get("topicCode")))){
                query04.put("topicCode", String.valueOf(document.get("topicCode")));
            }
            FindIterable<Document> findIterable04 = collection04.find(query04);
            MongoCursor<Document> mongoCursor04 = findIterable04.iterator();
            int icount = 0;
            if (mongoCursor04.hasNext()) {
                document02 = mongoCursor04.next();
                icount++;
            }
            if (icount <= 0) {
                document02 = null;
            }
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document02;
    }

    @Override
    public List<Document> findList(Document document) {
        List<Document> listDocument = new ArrayList<Document>();
        try{
            MongoCollection<Document> collectionOther = mongoDatabase.getCollection("topicUrl");
            BasicDBObject queryOther = new BasicDBObject();
            if(document.get("urlId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))){
                queryOther.put("urlId", String.valueOf(document.get("urlId")));
            }
            if(document.get("topicCode")!=null && StringUtils.isNotBlank(String.valueOf(document.get("topicCode")))){
                queryOther.put("topicCode", String.valueOf(document.get("topicCode")));
            }
            FindIterable<Document> findIterableOther = collectionOther.find(queryOther);
            MongoCursor<Document> mongoCursorOther = findIterableOther.iterator();
            int icount=0;
            while(mongoCursorOther.hasNext()) {
                Document docOther = mongoCursorOther.next();
                listDocument.add(docOther);
                icount++;
            }
            if(icount<=0){
                listDocument=null;
            }
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return listDocument;
    }


}
