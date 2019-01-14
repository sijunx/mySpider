package com.spider.search.service.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.spider.search.service.api.mongo.BlackWordsService;
import com.spider.search.service.impl.mongo.thread.HotsThread;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlackWordsServiceImpl extends AbstractSpiderBaseService implements BlackWordsService {

    private final static Logger logger = LoggerFactory.getLogger(BlackWordsServiceImpl.class);

    @Override
    public Document create(Document document) {
        try {
            MongoCollection<Document> collection01 = mongoDatabase.getCollection("blackWords");
            Document doc03 = new Document();
            if(document.get("wordId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("wordId")))){
                doc03.put("wordId",  String.valueOf(document.get("wordId")));
            }else{
                return null;
            }
            if(document.get("word")!=null && StringUtils.isNotBlank(String.valueOf(document.get("word")))){
                doc03.put("word",  String.valueOf(document.get("word")));
            }
            collection01.insertOne(doc03);
        }catch (Exception e){
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document;
    }

    @Override
    public Document modify(Document document) {
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("blackWords");
            if (document.get("word") != null || StringUtils.isNotBlank(String.valueOf(document.get("word")))){
                collection04.updateMany(Filters.eq("wordId", document.get("wordId")), new Document("$set", new Document("word", String.valueOf(document.get("word")))));
            }
        }catch (Exception e){
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document;
    }

    @Override
    public Document findOne(Document document) {
        Document document02 = null;
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("blackWords");
            BasicDBObject query04 = new BasicDBObject();
            if(document.get("wordId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("wordId")))){
                query04.put("wordId", String.valueOf(document.get("wordId")));
            }
            if(document.get("word")!=null && StringUtils.isNotBlank(String.valueOf(document.get("word")))){
                query04.put("word", String.valueOf(document.get("word")));
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
            MongoCollection<Document> collectionOther = mongoDatabase.getCollection("blackWords");
            BasicDBObject queryOther = new BasicDBObject();
            if(document.get("wordId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("wordId")))){
                queryOther.put("wordId", String.valueOf(document.get("wordId")));
            }
            if(document.get("word")!=null && StringUtils.isNotBlank(String.valueOf(document.get("word")))){
                queryOther.put("word", String.valueOf(document.get("word")));
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
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return listDocument;
    }
}
