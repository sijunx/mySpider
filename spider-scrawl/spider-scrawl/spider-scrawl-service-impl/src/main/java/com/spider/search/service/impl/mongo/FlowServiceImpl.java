package com.spider.search.service.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.spider.search.service.api.mongo.FlowService;
import com.spider.search.service.impl.mongo.thread.HotsThread;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程服务
 * @author SK_ZARD
 * @date 2018.06.01
 */
@Service
public class FlowServiceImpl extends AbstractSpiderBaseService implements FlowService {

    private final static Logger logger = LoggerFactory.getLogger(FlowServiceImpl.class);

    @Override
    public Document create(Document document) {
        try {
            MongoCollection<Document> collection01 = mongoDatabase.getCollection("urlflow");
            Document doc03 = new Document();
            if (document.get("flowId") != null && StringUtils.isNotBlank(String.valueOf(document.get("flowId")))) {
                doc03.put("flowId", String.valueOf(document.get("flowId")));
            }else{
                return null;
            }
            if (document.get("urlId") != null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))) {
                doc03.put("urlId", String.valueOf(document.get("urlId")));
            }
            if (document.get("nodeCode") != null && StringUtils.isNotBlank(String.valueOf(document.get("nodeCode")))) {
                doc03.put("nodeCode", String.valueOf(document.get("nodeCode")));
            }
            if (document.get("startFlag") != null && StringUtils.isNotBlank(String.valueOf(document.get("startFlag")))) {
                doc03.put("startFlag", String.valueOf(document.get("startFlag")));
            }
            if (document.get("pushQueueFlag") != null && StringUtils.isNotBlank(String.valueOf(document.get("pushQueueFlag")))) {
                doc03.put("pushQueueFlag", String.valueOf(document.get("pushQueueFlag")));
            }
            if (document.get("endFlag") != null && StringUtils.isNotBlank(String.valueOf(document.get("endFlag")))) {
                doc03.put("endFlag", String.valueOf(document.get("endFlag")));
            }
            if (document.get("pushCounts") != null && StringUtils.isNotBlank(String.valueOf(document.get("pushCounts")))) {
                doc03.put("pushCounts", Double.parseDouble(String.valueOf(document.get("pushCounts"))));
            }
            if (document.get("seqNo") != null && StringUtils.isNotBlank(String.valueOf(document.get("seqNo")))) {
                doc03.put("seqNo", Double.parseDouble(String.valueOf(document.get("seqNo"))));
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
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("urlflow");
            if (document.get("urlId") != null || StringUtils.isNotBlank(String.valueOf(document.get("urlId")))) {
                collection04.updateMany(Filters.eq("flowId", document.get("flowId")), new Document("$set", new Document("urlId", String.valueOf(document.get("urlId")))));
            }
            if (document.get("nodeCode") != null || StringUtils.isNotBlank(String.valueOf(document.get("nodeCode")))) {
                collection04.updateMany(Filters.eq("flowId", document.get("flowId")), new Document("$set", new Document("nodeCode", String.valueOf(document.get("nodeCode")))));
            }
            if (document.get("startFlag") != null || StringUtils.isNotBlank(String.valueOf(document.get("startFlag")))) {
                collection04.updateMany(Filters.eq("flowId", document.get("flowId")), new Document("$set", new Document("startFlag", String.valueOf(document.get("startFlag")))));
            }
            if (document.get("pushQueueFlag") != null || StringUtils.isNotBlank(String.valueOf(document.get("pushQueueFlag")))) {
                collection04.updateMany(Filters.eq("flowId", document.get("flowId")), new Document("$set", new Document("pushQueueFlag", String.valueOf(document.get("pushQueueFlag")))));
            }
            if (document.get("endFlag") != null || StringUtils.isNotBlank(String.valueOf(document.get("endFlag")))) {
                collection04.updateMany(Filters.eq("flowId", document.get("flowId")), new Document("$set", new Document("endFlag", String.valueOf(document.get("endFlag")))));
            }
            if (document.get("pushCounts") != null || StringUtils.isNotBlank(String.valueOf(document.get("pushCounts")))) {
                collection04.updateMany(Filters.eq("flowId", document.get("flowId")), new Document("$set", new Document("pushCounts", Double.parseDouble(String.valueOf(document.get("pushCounts"))))));
            }
            if (document.get("seqNo") != null || StringUtils.isNotBlank(String.valueOf(document.get("seqNo")))) {
                collection04.updateMany(Filters.eq("flowId", document.get("flowId")), new Document("$set", new Document("seqNo", Double.parseDouble(String.valueOf(document.get("seqNo"))))));
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
            MongoCollection<Document> collection04 = mongoDatabase.getCollection("urlflow");
            BasicDBObject query04 = new BasicDBObject();
            if (document.get("flowId") != null && StringUtils.isNotBlank(String.valueOf(document.get("flowId")))) {
                query04.put("flowId", String.valueOf(document.get("flowId")));
            }
            if (document.get("urlId") != null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))) {
                query04.put("urlId", String.valueOf(document.get("urlId")));
            }
            if (document.get("nodeCode") != null && StringUtils.isNotBlank(String.valueOf(document.get("nodeCode")))) {
                query04.put("nodeCode", String.valueOf(document.get("nodeCode")));
            }
            if (document.get("startFlag") != null && StringUtils.isNotBlank(String.valueOf(document.get("startFlag")))) {
                query04.put("startFlag", String.valueOf(document.get("startFlag")));
            }
            if (document.get("pushQueueFlag") != null && StringUtils.isNotBlank(String.valueOf(document.get("pushQueueFlag")))) {
                query04.put("pushQueueFlag", String.valueOf(document.get("pushQueueFlag")));
            }
            if (document.get("endFlag") != null && StringUtils.isNotBlank(String.valueOf(document.get("endFlag")))) {
                query04.put("endFlag", String.valueOf(document.get("endFlag")));
            }
            if (document.get("pushCounts") != null && StringUtils.isNotBlank(String.valueOf(document.get("pushCounts")))) {
                query04.put("pushCounts", Double.parseDouble(String.valueOf(document.get("pushCounts"))));
            }
            if (document.get("seqNo") != null && StringUtils.isNotBlank(String.valueOf(document.get("seqNo")))) {
                query04.put("seqNo", Double.parseDouble(String.valueOf(document.get("seqNo"))));
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
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document02;
    }

    @Override
    public List<Document> findList(Document document) {
        List<Document> listDocument = new ArrayList<Document>();
        try {
            MongoCollection<Document> collectionOther = mongoDatabase.getCollection("urlflow");
            BasicDBObject queryOther = new BasicDBObject();
            if (document.get("flowId") != null && StringUtils.isNotBlank(String.valueOf(document.get("flowId")))) {
                queryOther.put("flowId", String.valueOf(document.get("flowId")));
            }
            if (document.get("urlId") != null && StringUtils.isNotBlank(String.valueOf(document.get("urlId")))) {
                queryOther.put("urlId", String.valueOf(document.get("urlId")));
            }
            if (document.get("nodeCode") != null && StringUtils.isNotBlank(String.valueOf(document.get("nodeCode")))) {
                queryOther.put("nodeCode", String.valueOf(document.get("nodeCode")));
            }
            if (document.get("startFlag") != null && StringUtils.isNotBlank(String.valueOf(document.get("startFlag")))) {
                queryOther.put("startFlag", String.valueOf(document.get("startFlag")));
            }
            if (document.get("pushQueueFlag") != null && StringUtils.isNotBlank(String.valueOf(document.get("pushQueueFlag")))) {
                queryOther.put("pushQueueFlag", String.valueOf(document.get("pushQueueFlag")));
            }
            if (document.get("endFlag") != null && StringUtils.isNotBlank(String.valueOf(document.get("endFlag")))) {
                queryOther.put("endFlag", String.valueOf(document.get("endFlag")));
            }
            if (document.get("pushCounts") != null && StringUtils.isNotBlank(String.valueOf(document.get("pushCounts")))) {
                queryOther.put("pushCounts", Double.parseDouble(String.valueOf(document.get("pushCounts"))));
            }
            if (document.get("seqNo") != null && StringUtils.isNotBlank(String.valueOf(document.get("seqNo")))) {
                queryOther.put("seqNo", Double.parseDouble(String.valueOf(document.get("seqNo"))));
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
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return listDocument;
    }

}
