package com.spider.search.service.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.spider.search.service.api.mongo.UrlSimilarService;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlSimilarServiceImpl extends AbstractSpiderBaseService implements UrlSimilarService {

    @Override
    public Document create(Document document) {
        try {
            MongoCollection<Document> collection01 = mongoDatabase.getCollection("urlSimilar");
            Document doc03 = new Document();
            if(document.get("urlId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))){
                doc03.put("urlId",  String.valueOf(document.get("urlId")));
            }else{
                return null;
            }
            if(document.get("urlIdSeed")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlIdSeed")))){
                doc03.put("urlIdSeed",  String.valueOf(document.get("urlIdSeed")));
            }
            if(document.get("similar")!=null && StringUtils.isNotBlank(String.valueOf(document.get("similar")))){
                doc03.put("similar",  Double.parseDouble(String.valueOf(document.get("similar"))));
            }
            collection01.insertOne(doc03);
        }catch (Exception e){
            System.out.println(e);
        }
        return document;
    }

    @Override
    public Document modify(Document document) {
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("urlSimilar");
            if (document.get("urlIdSeed") != null || StringUtils.isNotBlank(String.valueOf(document.get("urlIdSeed")))){
                collection04.updateMany(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("urlIdSeed", String.valueOf(document.get("urlIdSeed")))));
            }
            if (document.get("similar") != null || StringUtils.isNotBlank(String.valueOf(document.get("similar")))){
                collection04.updateMany(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("similar", Double.parseDouble(String.valueOf(document.get("similar"))))));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return document;
    }

    @Override
    public Document findOne(Document document) {
        Document document02 = null;
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("urlSimilar");
            BasicDBObject query04 = new BasicDBObject();
            if(document.get("urlId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))){
                query04.put("urlId", String.valueOf(document.get("urlId")));
            }
            if(document.get("urlIdSeed")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlIdSeed")))){
                query04.put("urlIdSeed", String.valueOf(document.get("urlIdSeed")));
            }
            if(document.get("similar")!=null && StringUtils.isNotBlank(String.valueOf(document.get("similar")))){
                query04.put("similar",  String.valueOf(document.get("similar")));
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
            System.out.println(e);
        }
        return document02;
    }

    @Override
    public List<Document> findList(Document document) {
        List<Document> listDocument = new ArrayList<Document>();
        try{
            MongoCollection<Document> collectionOther = mongoDatabase.getCollection("urlSimilar");
            BasicDBObject queryOther = new BasicDBObject();
            if(document.get("urlId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))){
                queryOther.put("urlId", String.valueOf(document.get("urlId")));
            }
            if(document.get("urlIdSeed")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlIdSeed")))){
                queryOther.put("urlIdSeed", String.valueOf(document.get("urlIdSeed")));
            }
            if(document.get("similar")!=null && StringUtils.isNotBlank(String.valueOf(document.get("similar")))){
                queryOther.put("similar",  String.valueOf(document.get("similar")));
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
            System.out.println(e);
        }
        return listDocument;
    }
}
