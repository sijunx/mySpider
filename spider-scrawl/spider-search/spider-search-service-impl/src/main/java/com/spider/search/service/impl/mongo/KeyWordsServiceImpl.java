package com.spider.search.service.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.spider.search.service.api.mongo.KeyWordsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyWordsServiceImpl extends AbstractSpiderBaseService implements KeyWordsService {

    private final static Logger logger = LoggerFactory.getLogger(KeyWordsServiceImpl.class);

    public Document create(Document document) {
        try {
            MongoCollection<Document> collection = mongoDatabase.getCollection("keyWords");
            Document doc03 = new Document();
            if(document.get("urlId")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))) {
                doc03.put("urlId", String.valueOf(document.get("urlId")));
            }
            if(document.get("keyWord")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("keyWord")))){
                doc03.put("keyWord",  String.valueOf(document.get("keyWord")));
            }
            if(document.get("counts")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("counts")))){
                doc03.put("counts",  Double.parseDouble(String.valueOf(document.get("counts"))));
            }
            collection.insertOne(doc03);
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document;
    }

    public Document modify(Document document) {
        try{
            if(document.get("urlId")==null || StringUtils.isBlank(String.valueOf(document.get("urlId")))){
                return null;
            }
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("keyWords");
            if(document.get("keyWord") != null || StringUtils.isNotEmpty(String.valueOf(document.get("keyWord")))){
                collection04.updateMany(Filters.eq("urlId", String.valueOf(document.get("urlId"))), new Document("$set",new Document("keyWord",String.valueOf(document.get("keyWord")))));
            }
            if(document.get("counts") != null || StringUtils.isNotEmpty(String.valueOf(document.get("counts")))){
                collection04.updateMany(Filters.eq("urlId", String.valueOf(document.get("urlId"))), new Document("$set",new Document("counts",String.valueOf(document.get("counts")))));
            }
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document;
    }

    public Document findOne(Document document) {
        Document document02 = null;
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("keyWords");
            BasicDBObject query04 = new BasicDBObject();
            if (null != document.get("urlId") && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))) {
                query04.put("urlId", document.get("urlId"));
            }
            if (null != document.get("keyWord") && StringUtils.isNotEmpty(String.valueOf(document.get("keyWord")))) {
                query04.put("keyWord", document.get("keyWord"));
            }
            if (null != document.get("counts") && StringUtils.isNotEmpty(String.valueOf(document.get("counts")))) {
                query04.put("counts", document.get("counts"));
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

    public List<Document> findList(Document document) {
        List<Document> listDocument = new ArrayList<Document>();
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("keyWords");
            BasicDBObject query04 = new BasicDBObject();
            if (null != document.get("urlId") && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))) {
                query04.put("urlId", document.get("urlId"));
            }
            if (null != document.get("keyWord") && StringUtils.isNotEmpty(String.valueOf(document.get("keyWord")))) {
                query04.put("keyWord", document.get("keyWord"));
            }
            if (null != document.get("counts") && StringUtils.isNotEmpty(String.valueOf(document.get("counts")))) {
                query04.put("counts", document.get("counts"));
            }
            FindIterable<Document> findIterable04 = collection04.find(query04);
            MongoCursor<Document> mongoCursor04 = findIterable04.iterator();
            int icount = 0;
            while (mongoCursor04.hasNext()) {
                Document document02 = mongoCursor04.next();
                listDocument.add(document02);
                icount++;
            }
            if (icount <= 0) {
                listDocument = null;
            }
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return listDocument;
    }

    public List<Document> findByEqualUrlId(Document document) {
        List<Document> listDocument = new ArrayList<Document>();
        try {
            MongoCollection<Document> collectionOther = mongoDatabase.getCollection("keyWords");
            BasicDBObject queryOther = new BasicDBObject();
            queryOther.put("urlId", String.valueOf(document.get("urlId")));

            BasicDBObject dbObject = new BasicDBObject();
            //mongodb中按counts字段倒序查询（-1是倒序，1是正序）
            dbObject.put("counts", -1);
            FindIterable<Document> findIterableOther = collectionOther.find(queryOther).sort(dbObject);
            MongoCursor<Document> mongoCursorOther = findIterableOther.iterator();
            int icount = 0;
            while (mongoCursorOther.hasNext()) {
                Document docOther = mongoCursorOther.next();
                listDocument.add(docOther);
                icount++;
            }
            if (icount <= 0) {
                listDocument = null;
            }
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return listDocument;
    }
}