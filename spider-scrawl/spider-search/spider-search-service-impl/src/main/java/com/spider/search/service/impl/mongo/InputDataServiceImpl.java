package com.spider.search.service.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.spider.search.service.api.mongo.ImageService;
import com.spider.search.service.api.mongo.InputDataService;
import com.spider.search.service.dto.InputDataServiceDTO;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InputDataServiceImpl extends AbstractSpiderBaseService implements InputDataService {

    @Autowired
    private ImageService imageService;

    @Override
    public Document create(Document document) {
        try {
            MongoCollection<Document> collection01 = mongoDatabase.getCollection("inputData");
            Document doc03 = new Document();
            if(document.get("urlId")!=null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))){
                doc03.put("urlId",  String.valueOf(document.get("urlId")));
            }else{
                return null;
            }
            if(document.get("url")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("url")))){
                doc03.put("url",  String.valueOf(document.get("url")));
            }
            if(document.get("txt")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("txt")))){
                doc03.put("txt",  String.valueOf(document.get("txt")));
            }
            if(document.get("summary")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("summary")))){
                doc03.put("summary",  String.valueOf(document.get("summary")));
            }
            if(document.get("title")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("title")))){
                doc03.put("title",  String.valueOf(document.get("title")));
            }
            if(document.get("hots")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("hots")))){
                doc03.put("hots",  Double.parseDouble(String.valueOf(document.get("hots"))));
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
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("inputData");
            if (document.get("url") != null || StringUtils.isNotEmpty(String.valueOf(document.get("url")))){
                collection04.updateMany(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("url", String.valueOf(document.get("url")))));
            }
            if (document.get("txt") != null || StringUtils.isNotEmpty(String.valueOf(document.get("txt")))) {
                collection04.updateMany(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("txt", String.valueOf(document.get("txt")))));
            }
            if (document.get("summary") != null || StringUtils.isNotEmpty(String.valueOf(document.get("summary")))) {
                collection04.updateMany(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("summary", String.valueOf(document.get("summary")))));
            }
            if (document.get("title") != null || StringUtils.isNotEmpty(String.valueOf(document.get("title")))) {
                collection04.updateMany(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("title", String.valueOf(document.get("title")))));
            }
            if (document.get("hots") != null || StringUtils.isNotEmpty(String.valueOf(document.get("hots")))){
                collection04.updateMany(Filters.eq("urlId", document.get("urlId")), new Document("$set", new Document("hots", Double.parseDouble(String.valueOf(document.get("hots"))))));
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
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("inputData");
            BasicDBObject queryOther = new BasicDBObject();
            if(document.get("url")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("url")))){
                queryOther.put("url", String.valueOf(document.get("url")));
            }
            if(document.get("txt")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("txt")))){
                queryOther.put("txt", String.valueOf(document.get("txt")));
            }
            if(document.get("summary")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("summary")))){
                queryOther.put("summary",  String.valueOf(document.get("summary")));
            }
            if(document.get("title")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("title")))){
                queryOther.put("title",  String.valueOf(document.get("title")));
            }
            if(document.get("urlId")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))){
                queryOther.put("urlId",  String.valueOf(document.get("urlId")));
            }
            if(document.get("hots")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("hots")))){
                queryOther.put("hots",  Double.parseDouble(String.valueOf(document.get("hots"))));
            }
            FindIterable<Document> findIterable04 = collection04.find(queryOther);
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
            MongoCollection<Document> collectionOther = mongoDatabase.getCollection("inputData");
            BasicDBObject queryOther = new BasicDBObject();
            if(document.get("url")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("url")))){
                queryOther.put("url", String.valueOf(document.get("url")));
            }
            if(document.get("txt")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("txt")))){
                queryOther.put("txt", String.valueOf(document.get("txt")));
            }
            if(document.get("summary")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("summary")))){
                queryOther.put("summary",  String.valueOf(document.get("summary")));
            }
            if(document.get("title")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("title")))){
                queryOther.put("title",  String.valueOf(document.get("title")));
            }
            if(document.get("urlId")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("urlId")))){
                queryOther.put("urlId",  String.valueOf(document.get("urlId")));
            }
            if(document.get("hots")!=null && StringUtils.isNotEmpty(String.valueOf(document.get("hots")))){
                queryOther.put("hots",  Double.parseDouble(String.valueOf(document.get("hots"))));
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

    @Override
    public List<Document> findListByHotsDesc() {
        List<Document> listDocument = new ArrayList<Document>();
        try {
            MongoCollection<Document> collectionSeed = mongoDatabase.getCollection("inputData");
            BasicDBObject basicDBObjectSeed = new BasicDBObject("hots", -1);
            FindIterable<Document> findIterableSeed = collectionSeed.find().sort(basicDBObjectSeed);
            MongoCursor<Document> mongoCursorSeed = findIterableSeed.iterator();
            int icount = 0;
            while (mongoCursorSeed.hasNext()) {
                Document docSeed = mongoCursorSeed.next();
                listDocument.add(docSeed);
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

    @Override
    public List<Document> findByNotEqualUrlId(Document document) {
        List<Document> listDocument = new ArrayList<Document>();
        try {
            MongoCollection<Document> collectionOther = mongoDatabase.getCollection("inputData");
            BasicDBObject queryOther = new BasicDBObject();
            queryOther.put("urlId", new BasicDBObject("$ne", document.get("urlId")));
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

    @Override
    public List<InputDataServiceDTO> searchByKeyWord(String keyWord, Integer bottomId) {
        imageService.setDatabase(this.mongoDatabase);

        MongoCollection<Document> collection = mongoDatabase.getCollection("inputData");


        BasicDBObject condition= new BasicDBObject();//最后在将查询结果放到一个查询对象中去

//        BasicDBObject basicDBObject = new BasicDBObject("hots",-1);
//        FindIterable<Document> findIterable = collection.find(condition).sort(basicDBObject).limit(10);
        Integer skip = (bottomId==null)?0:bottomId*10;
        FindIterable<Document> findIterable = collection.find(condition).limit(10).skip(skip);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<InputDataServiceDTO> results = new ArrayList<InputDataServiceDTO>();
        int icount=0;

        while(mongoCursor.hasNext()){
            Document document01 = mongoCursor.next();
            icount++;
            if(icount>10){
                break;
            }
            String id = String.valueOf(document01.get("id"));

            InputDataServiceDTO fundInputData01 = new InputDataServiceDTO();
            String url = String.valueOf(document01.get("url"));
            String title = String.valueOf(document01.get("title"));
            String summary = String.valueOf(document01.get("summary"));
            String urlId = String.valueOf(document01.get("urlId"));

            Document doc01 = new Document();
            doc01.put("urlId", urlId);
            List<Document> docImageList = imageService.findList(doc01);
            String imageId=null;
            if(null != docImageList) {
                for (Document docImage : docImageList) {
                    String path01 = docImage.getString("imagePath");
                    if (path01.contains("logo")) {
                        continue;
                    } else {
                        imageId = docImage.getString("imageId");
                        break;
                    }
                }
            }
            fundInputData01.setUrl(url);
            fundInputData01.setTitle(title);
            fundInputData01.setSummary(summary);
            fundInputData01.setId(urlId);
            fundInputData01.setImageId(imageId);
            results.add(fundInputData01);
        }
        return results;
    }
}
