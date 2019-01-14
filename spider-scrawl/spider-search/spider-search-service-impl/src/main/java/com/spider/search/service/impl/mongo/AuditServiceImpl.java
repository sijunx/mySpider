package com.spider.search.service.impl.mongo;

import com.alibaba.fastjson.JSONObject;
import com.spider.search.service.api.mongo.AuditService;
import com.spider.search.service.api.mongo.BlackWordsService;
import com.spider.search.service.api.mongo.InputDataService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl extends AbstractSpiderBaseService implements AuditService {

    @Autowired
    private InputDataService inputDataService;
    @Autowired
    private BlackWordsService blackWordsService;

    @Override
    public String duplicateCheck(String url){
        inputDataService.setDatabase(this.mongoDatabase);

        JSONObject jsonObject = new JSONObject();
        Document doc = new Document();
        doc.put("url", url);
        Document doc01 = inputDataService.findOne(doc);
        if(null != doc01){
            jsonObject.put("code","400");
            jsonObject.put("message","url已经存在"+url);
            return jsonObject.toString();
        }
        jsonObject.put("code","200");
        jsonObject.put("message","审核通过");
        return jsonObject.toString();
    }

    @Override
    public String duplicateTwoMoreCheck(String url){
        inputDataService.setDatabase(this.mongoDatabase);

        JSONObject jsonObject = new JSONObject();
        Document doc = new Document();
        doc.put("url", url);
        List<Document> list = inputDataService.findList(doc);
        if(null != list && list.size() >= 2){
            jsonObject.put("code","400");
            jsonObject.put("message","url已经存在两次"+url);
            return jsonObject.toString();
        }
        jsonObject.put("code","200");
        jsonObject.put("message","审核通过");
        return jsonObject.toString();
    }

    @Override
    public String blackWordsExists(String word){
        blackWordsService.setDatabase(this.mongoDatabase);

        JSONObject jsonObject = new JSONObject();
        Document doc02 = new Document();
        doc02.put("word",word);
        List<Document> listBlack = blackWordsService.findList(doc02);
        if(null != listBlack && listBlack.size()>0){
            jsonObject.put("code","400");
            jsonObject.put("message","审核不通过");
            return jsonObject.toString();
        }else{
            jsonObject.put("code","200");
            jsonObject.put("message","审核通过");
            return jsonObject.toString();
        }
    }

    @Override
    public String titleCheck(String title){
        inputDataService.setDatabase(this.mongoDatabase);
        JSONObject jsonObject = new JSONObject();
        String passFlag = "1";//默认通过
        //如果同一标题在同一网站出现两次以上，那么，审核不通过
        Document document = new Document();
        document.put("title", title);
        List<Document> list = inputDataService.findList(document);
        if(null != list && list.size() >=1){
            passFlag = "0";
        }

        //如果title包含不存在等关键词，那么，审核不通过
        if(title.contains("页面不存在") ||title.contains("访问出错了")) {
            passFlag = "0";
        }

        if(passFlag.equals("0")){
            jsonObject.put("code","400");
            jsonObject.put("message","title审核不通过"+title);
            return jsonObject.toString();
        }else {
            jsonObject.put("code", "200");
            jsonObject.put("message", "审核通过");
            return jsonObject.toString();
        }
    }

    @Override
    public String txtCheck(String txt){
        JSONObject jsonObject = new JSONObject();
        if(txt.length()<30){
            jsonObject.put("code","400");
            jsonObject.put("message","title审核不通过"+txt);
            return jsonObject.toString();
        }else {
            jsonObject.put("code", "200");
            jsonObject.put("message", "审核通过");
            return jsonObject.toString();
        }
    }
}
