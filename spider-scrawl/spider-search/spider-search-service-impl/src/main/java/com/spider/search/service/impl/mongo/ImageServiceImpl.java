package com.spider.search.service.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.spider.search.service.api.mongo.ImageService;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sh00815 on 2017/9/14.
 */
@Service
public class ImageServiceImpl extends AbstractSpiderBaseService implements ImageService {

    public Document create(Document document) {
        try {
            MongoCollection<Document> collection = mongoDatabase.getCollection("urlImage");
            Document doc03 = new Document();
            if(document.get("imageId")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("imageId")))){
                doc03.put("imageId",  String.valueOf(document.get("imageId")));
            }
            if(document.get("urlId")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))) {
                doc03.put("urlId", String.valueOf(document.get("urlId")));
            }
            if(document.get("imagePath")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("imagePath")))) {
                doc03.put("imagePath", String.valueOf(document.get("imagePath")));
            }
            collection.insertOne(doc03);
        }catch (Exception e){
            System.out.println(e);
        }
        return document;
    }

    public Document modify(Document document) {
        try{
            if(document.get("imageId")==null || StringUtils.isBlank(String.valueOf(document.get("imageId")))){
                return null;
            }
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("urlImage");
            if(document.get("urlId") != null || StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))){
                collection04.updateMany(Filters.eq("imageId", String.valueOf(document.get("imageId"))), new Document("$set",new Document("urlId",String.valueOf(document.get("urlId")))));
            }
            if(document.get("imagePath") != null || StringUtils.isNotEmpty(String.valueOf(document.get("imagePath")))){
                collection04.updateMany(Filters.eq("imageId", String.valueOf(document.get("imageId"))), new Document("$set",new Document("imagePath",String.valueOf(document.get("imagePath")))));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return document;
    }

    public Document findOne(Document document) {
        Document document02 = null;
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("urlImage");
            BasicDBObject query04 = new BasicDBObject();
            if (null != document.get("imageId") && StringUtils.isNotEmpty(String.valueOf(document.get("imageId")))) {
                query04.put("imageId", document.getString("imageId"));
            }
            if (null != document.get("urlId") && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))) {
                query04.put("urlId", document.getString("urlId"));
            }
            if (null != document.get("imagePath") && StringUtils.isNotEmpty(String.valueOf(document.get("imagePath")))) {
                query04.put("imagePath", document.getString("imagePath"));
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

    public List<Document> findList(Document document) {
        List<Document> listDocument = new ArrayList<Document>();
        try {
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("urlImage");
            BasicDBObject query04 = new BasicDBObject();
            if (null != document.get("imageId") && StringUtils.isNotEmpty(String.valueOf(document.get("imageId")))) {
                query04.put("imageId", document.get("imageId"));
            }
            if (null != document.get("urlId") && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))) {
                query04.put("urlId", document.get("urlId"));
            }
            if (null != document.get("imagePath") && StringUtils.isNotEmpty(String.valueOf(document.get("imagePath")))) {
                query04.put("imagePath", document.get("imagePath"));
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
            System.out.println(e);
        }
        return listDocument;
    }
}