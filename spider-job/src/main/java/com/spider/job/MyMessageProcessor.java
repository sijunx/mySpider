package com.spider.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoDatabase;
import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.base.mongo.MongoConnUtil;
import com.spider.search.service.api.mongo.ReverseIndexService;
import com.spider.search.service.api.mongo.WordsDicService;
import com.spider.search.service.impl.mongo.ReverseIndexServiceImpl;
import com.spider.search.service.impl.mongo.WordsDicServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.Document;

import java.util.UUID;

@Service
public class MyMessageProcessor implements ISpiderMessageProcessor {

    private final static Logger logger = LoggerFactory.getLogger(MyMessageProcessor.class);

    private ReverseIndexService reverseIndexService = new ReverseIndexServiceImpl();

    private WordsDicService wordsDicService = new WordsDicServiceImpl();

    @Override
    public void messageProcess(String message){
        logger.info("--------------------消息处理，收到的消息内容:{}", message);
        JSONArray jsonArray = JSON.parseArray(message);

        MongoConnUtil mongoConnUtil = new MongoConnUtil();
        MongoDatabase mongoDatabase = mongoConnUtil.initConn();
        wordsDicService.setDatabase(mongoDatabase);
        reverseIndexService.setDatabase(mongoDatabase);

        if(jsonArray!=null && jsonArray.size()>0){
            for(int icount=0; icount<jsonArray.size(); icount++){
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String title = jsonObject.getString("title");
                String url = jsonObject.getString("url");
                String urlId = jsonObject.getString("urlId");
                String summary = jsonObject.getString("summary");
                JSONArray keyWordsList = jsonObject.getJSONArray("keyWords");
                if(keyWordsList!=null && keyWordsList.size()>0){
                    for(int jcount=0; jcount<keyWordsList.size(); jcount++){
                        String keyWord = keyWordsList.getString(jcount);
                        logger.info("keyWord:{}", keyWord);
                        String wordId = saveKeyWord(keyWord);
                        //倒序索引创建
                        Document docNew02 = new Document();
                        docNew02.put("id",UUID.randomUUID().toString().replace("-", ""));
                        docNew02.put("wordId", wordId);
                        docNew02.put("urlId", String.valueOf(urlId));
                        docNew02.put("hots", 30);
                        docNew02.put("title", title);
                        docNew02.put("summary", summary);
                        docNew02.put("url", url);
                        reverseIndexService.create(docNew02);
                    }
                }
                logger.info("title:{} url:{} urlId:{} summary:{}", title, url, urlId, summary);
            }
        }
        logger.info("--------------------消息处理结束------------------------------");
    }

    private String saveKeyWord(String keyWord){
        //判断单词是否存在词典中，如果不存在，那么，添加。否则，取词的id
        Document doc03 = new Document();
        doc03.put("word", keyWord);
        Document doc04 = wordsDicService.findOne(doc03);
        String wordId = "";
        if(null == doc04){
            Document docNew = new Document();
            docNew.put("wordId", UUID.randomUUID().toString().replace("-", ""));
            docNew.put("word", keyWord);
            wordsDicService.create(docNew);
            wordId = String.valueOf(docNew.get("wordId"));
            logger.info("新增进去------------------------------------------------------wordId:{} keyWord:{}", wordId, keyWord);
        }else{
            wordId = String.valueOf(doc04.get("wordId"));
        }
        return wordId;
    }
}
