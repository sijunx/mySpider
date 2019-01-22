package com.spider.search.service.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.spider.search.service.api.mongo.SpiderUrlService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpiderUrlServiceImpl extends AbstractSpiderBaseService implements SpiderUrlService {

    private final static Logger logger = LoggerFactory.getLogger(SpiderUrlServiceImpl.class);

    @Override
    public Document create(Document document) {
        try {
            MongoCollection<Document> collection = mongoDatabase.getCollection("urlInfo");
            Document doc03 = new Document();
            if(document.get("urlId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))){
                doc03.put("urlId",  String.valueOf(document.get("urlId")));
            }else {
                return null;
            }
            if(document.get("url")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("url")))){
                doc03.put("url",  String.valueOf(document.get("url")));
            }
            if(document.get("deleteFlag")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("deleteFlag")))){
                doc03.put("deleteFlag",  String.valueOf(document.get("deleteFlag")));
            }
            if(document.get("createTime")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("createTime")))){
                doc03.put("createTime",  String.valueOf(document.get("createTime")));
            }
            if(document.get("rootUrl")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("rootUrl")))){
                doc03.put("rootUrl",  String.valueOf(document.get("rootUrl")));
            }
            if(document.get("deep")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("deep")))){
                doc03.put("deep",  Double.parseDouble(String.valueOf(document.get("deep"))));
            }
            collection.insertOne(doc03);
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document;
    }

    @Override
    public Document modify(Document document) {
        try{
            if(document.get("urlId")==null || StringUtils.isBlank(String.valueOf(document.get("urlId")))){
                return null;
            }
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("urlInfo");
            if(document.get("url") != null || StringUtils.isNotEmpty(String.valueOf(document.get("url")))){
                collection04.updateMany(Filters.eq("urlId", String.valueOf(document.get("urlId"))), new Document("$set",new Document("url",String.valueOf(document.get("url")))));
            }
            if(document.get("deleteFlag") != null || StringUtils.isNotEmpty(String.valueOf(document.get("deleteFlag")))){
                collection04.updateMany(Filters.eq("urlId", String.valueOf(document.get("urlId"))), new Document("$set",new Document("deleteFlag",String.valueOf(document.get("deleteFlag")))));
            }
            if(document.get("createTime") != null || StringUtils.isNotEmpty(String.valueOf(document.get("createTime")))){
                collection04.updateMany(Filters.eq("urlId", String.valueOf(document.get("urlId"))), new Document("$set",new Document("createTime",String.valueOf(document.get("createTime")))));
            }
            if(document.get("rootUrl") != null || StringUtils.isNotEmpty(String.valueOf(document.get("rootUrl")))){
                collection04.updateMany(Filters.eq("urlId", String.valueOf(document.get("urlId"))), new Document("$set",new Document("rootUrl",String.valueOf(document.get("rootUrl")))));
            }
            if(document.get("deep") != null || StringUtils.isNotEmpty(String.valueOf(document.get("deep")))){
                collection04.updateMany(Filters.eq("urlId", String.valueOf(document.get("urlId"))), new Document("$set",new Document("deep", Double.parseDouble(String.valueOf(document.get("deep"))))));
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
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("urlInfo");
            BasicDBObject query04 = new BasicDBObject();
            if (document.get("urlId") != null && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))) {
                query04.put("urlId", String.valueOf(document.get("urlId")));
            }
            if(document.get("url") != null && StringUtils.isNotEmpty(String.valueOf(document.get("url")))) {
                query04.put("url", String.valueOf(document.get("url")));
            }
            if(document.get("deleteFlag") != null && StringUtils.isNotEmpty(String.valueOf(document.get("deleteFlag")))) {
                query04.put("deleteFlag", String.valueOf(document.get("deleteFlag")));
            }
            if(document.get("rootUrl") != null && StringUtils.isNotEmpty(String.valueOf(document.get("rootUrl")))) {
                query04.put("rootUrl", String.valueOf(document.get("rootUrl")));
            }
            if(document.get("deep") != null && StringUtils.isNotEmpty(String.valueOf(document.get("deep")))) {
                query04.put("deep", new BasicDBObject("$lte", Double.parseDouble(String.valueOf(document.get("deep")))));
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
        }
        catch (Exception e){
            logger.info("findOne异常");
        }
        return document02;
    }

    @Override
    public List<Document> findList(Document document) {
        List<Document> listDocument = new ArrayList<Document>();
        try{
            MongoCollection<Document> collectionOther = mongoDatabase.getCollection("urlInfo");
            BasicDBObject queryOther = new BasicDBObject();
            if(document.get("urlId")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))){
                queryOther.put("urlId",  String.valueOf(document.get("urlId")));
            }
            if(document.get("url")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("url")))){
                queryOther.put("url",  String.valueOf(document.get("url")));
            }
            if(document.get("deleteFlag")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("deleteFlag")))){
                queryOther.put("deleteFlag",  String.valueOf(document.get("deleteFlag")));
            }
            if(document.get("createTime")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("createTime")))){
                queryOther.put("createTime",  String.valueOf(document.get("createTime")));
            }
            if(document.get("rootUrl")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("rootUrl")))){
                queryOther.put("rootUrl",  String.valueOf(document.get("rootUrl")));
            }
            if(document.get("deep")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("deep")))){
                queryOther.put("deep", new BasicDBObject("$lte", Double.parseDouble(String.valueOf(document.get("deep")))));
            }
            FindIterable<Document> findIterableOther = collectionOther.find(queryOther).limit(10);
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