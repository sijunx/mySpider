package com.spider.search.service.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.spider.search.service.api.mongo.ReverseIndexService;
import com.spider.search.service.api.mongo.WordsDicService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReverseIndexServiceImpl extends AbstractSpiderBaseService implements ReverseIndexService {

    private final static Logger logger = LoggerFactory.getLogger(ReverseIndexServiceImpl.class);

    @Autowired
    private WordsDicService wordsDicService;

    public Document create(Document document) {
        try {
            MongoCollection<Document> collection = mongoDatabase.getCollection("reverseIndex");
            Document doc03 = new Document();
            if(document.get("urlId")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))){
                doc03.put("urlId",  String.valueOf(document.get("urlId")));
            }
            if(document.get("url")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("url")))){
                doc03.put("url",  String.valueOf(document.get("url")));
            }
            if(document.get("wordId")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("wordId")))){
                doc03.put("wordId",  String.valueOf(document.get("wordId")));
            }
            if(document.get("title")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("title")))){
                doc03.put("title",  String.valueOf(document.get("title")));
            }
            if(document.get("summary")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("summary")))){
                doc03.put("summary",  String.valueOf(document.get("summary")));
            }
            if(document.get("hots")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("hots")))){
                doc03.put("hots",  Double.parseDouble(String.valueOf(document.get("hots"))));
            }
            collection.insertOne(doc03);
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document;
    }

    public List<Document> findList(Document document) {
        List<Document> listDocument = new ArrayList<Document>();
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("reverseIndex");
            BasicDBObject query04 = new BasicDBObject();
            if (null != document.get("urlId") && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))) {
                query04.put("urlId", document.get("urlId"));
            }
            if (null != document.get("url") && StringUtils.isNotEmpty(String.valueOf(document.get("url")))) {
                query04.put("url", document.get("url"));
            }
            if (null != document.get("wordId") && StringUtils.isNotEmpty(String.valueOf(document.get("wordId")))) {
                query04.put("wordId", document.get("wordId"));
            }
            if (null != document.get("title") && StringUtils.isNotEmpty(String.valueOf(document.get("title")))) {
                query04.put("title", document.get("title"));
            }
            if (null != document.get("summary") && StringUtils.isNotEmpty(String.valueOf(document.get("summary")))) {
                query04.put("summary", document.get("summary"));
            }
            if (null != document.get("hots") && StringUtils.isNotEmpty(String.valueOf(document.get("hots")))) {
                query04.put("hots", Double.parseDouble(String.valueOf(document.get("hots"))));
            }

            FindIterable<Document> findIterable04=null;
            if (    null != document.get("limit") && StringUtils.isNotEmpty(String.valueOf(document.get("limit")))
                &&  null != document.get("skip")  && StringUtils.isNotEmpty(String.valueOf(document.get("skip")))) {
                int limitNumber = Integer.parseInt(String.valueOf(document.get("limit")));
                int skip = Integer.parseInt(String.valueOf(document.get("skip")));
                findIterable04 = collection04.find(query04).limit(limitNumber).skip(skip);
            }else{
                findIterable04 = collection04.find(query04);
            }

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

    public Document findOne(Document document) {
        Document document02 = null;
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("reverseIndex");
            BasicDBObject query04 = new BasicDBObject();
            if (null != document.get("urlId") && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))) {
                query04.put("urlId", document.get("urlId"));
            }
            if (null != document.get("url") && StringUtils.isNotEmpty(String.valueOf(document.get("url")))) {
                query04.put("url", document.get("url"));
            }
            if (null != document.get("wordId") && StringUtils.isNotEmpty(String.valueOf(document.get("wordId")))) {
                query04.put("wordId", document.get("wordId"));
            }
            if (null != document.get("title") && StringUtils.isNotEmpty(String.valueOf(document.get("title")))) {
                query04.put("title", document.get("title"));
            }
            if (null != document.get("summary") && StringUtils.isNotEmpty(String.valueOf(document.get("summary")))) {
                query04.put("summary", document.get("summary"));
            }
            if (null != document.get("hots") && StringUtils.isNotEmpty(String.valueOf(document.get("hots")))) {
                query04.put("hots", Double.parseDouble(String.valueOf(document.get("hots"))));
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

    public Document modify(Document document) {
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("reverseIndex");
            if (document.get("url") != null || StringUtils.isNotBlank(String.valueOf(document.get("url")))){
                collection04.updateOne(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("url", String.valueOf(document.get("url")))));
            }
            if (document.get("wordId") != null || StringUtils.isNotBlank(String.valueOf(document.get("wordId")))) {
                collection04.updateOne(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("wordId", String.valueOf(document.get("wordId")))));
            }
            if (document.get("summary") != null || StringUtils.isNotBlank(String.valueOf(document.get("summary")))) {
                collection04.updateOne(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("summary", String.valueOf(document.get("summary")))));
            }
            if (document.get("title") != null || StringUtils.isNotBlank(String.valueOf(document.get("title")))) {
                collection04.updateOne(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("title", String.valueOf(document.get("title")))));
            }
            if (document.get("hots") != null || StringUtils.isNotBlank(String.valueOf(document.get("hots")))){
                collection04.updateOne(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("hots", Double.parseDouble(String.valueOf(document.get("hots"))))));
            }
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document;
    }


    @Override
    public List<Document> findListByWord(String word) {
        wordsDicService.setDatabase(this.mongoDatabase);

        Document doc = new Document();
        doc.put("word", word);
        Document document = wordsDicService.findOne(doc);

        if(null != document){
            Document document01 = new Document();
            document01.put("wordId", String.valueOf(document.get("wordId")));
            document01.put("limit", 10);
            document01.put("skip", 0);
            List<Document> ret = findList(document01);
            return ret;
        }else{
            return null;
        }
    }
}
