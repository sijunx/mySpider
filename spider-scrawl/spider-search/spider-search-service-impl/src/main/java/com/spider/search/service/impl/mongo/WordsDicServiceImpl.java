package com.spider.search.service.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.spider.search.service.api.mongo.WordsDicService;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordsDicServiceImpl extends AbstractSpiderBaseService implements WordsDicService {

    public Document create(Document document) {
        try {
            MongoCollection<Document> collection = mongoDatabase.getCollection("wordsDic");
            Document doc03 = new Document();
            if(document.get("wordId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("wordId")))) {
                doc03.put("wordId", String.valueOf(document.get("wordId")));
            }else{
                return null;
            }
            if(document.get("word")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("word")))){
                doc03.put("word",  String.valueOf(document.get("word")));
            }
            collection.insertOne(doc03);
        }catch (Exception e){
            System.out.println(e);
        }
        return document;
    }

    public Document modify(Document document) {
        try{
            if(document.get("wordId")==null || StringUtils.isBlank(String.valueOf(document.get("wordId")))){
                return null;
            }
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("wordsDic");
            if(document.get("word") != null || StringUtils.isNotEmpty(String.valueOf(document.get("word")))){
                collection04.updateMany(Filters.eq("wordId", String.valueOf(document.get("wordId"))), new Document("$set",new Document("word",String.valueOf(document.get("word")))));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return document;
    }

    public Document findOne(Document document) {
        Document docOther = null;
        try {
            MongoCollection<Document> collectionOther = mongoDatabase.getCollection("wordsDic");
            BasicDBObject queryOther = new BasicDBObject();
            if (null != document.get("wordId") && StringUtils.isNotEmpty(String.valueOf(document.get("wordId")))) {
                queryOther.put("wordId", String.valueOf(document.get("wordId")));
            }
            if (null != document.get("word") && StringUtils.isNotEmpty(String.valueOf(document.get("word")))) {
                queryOther.put("word", String.valueOf(document.get("word")));
            }
            FindIterable<Document> findIterableOther = collectionOther.find(queryOther);
            MongoCursor<Document> mongoCursorOther = findIterableOther.iterator();
            int icount = 0;
            if (mongoCursorOther.hasNext()) {
                docOther = mongoCursorOther.next();
                icount++;
            }
            if (icount <= 0) {
                docOther = null;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return docOther;
    }

    public List<Document> findList(Document document) {
        List<Document> listDocument = new ArrayList<Document>();
        try {
            MongoCollection<Document> collectionOther = mongoDatabase.getCollection("wordsDic");
            BasicDBObject queryOther = new BasicDBObject();
            if (null != document.get("wordId") && StringUtils.isNotEmpty(String.valueOf(document.get("wordId")))) {
                queryOther.put("wordId", String.valueOf(document.get("wordId")));
            }
            if (null != document.get("word") && StringUtils.isNotEmpty(String.valueOf(document.get("word")))) {
                queryOther.put("word", String.valueOf(document.get("word")));
            }
            FindIterable<Document> findIterableOther = collectionOther.find(queryOther);
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
            System.out.println(e);
        }
        return listDocument;
    }
}
