package com.spider.search.service.impl.mongo.thread;

import com.mongodb.client.MongoDatabase;
import com.spider.search.service.api.mongo.*;
import com.spider.search.service.dto.DocQueue;
import org.bson.Document;

import java.util.List;
import java.util.UUID;

public class ReverseIndexThread implements Runnable{

    private String threadName;
    private Document document;
    private DocQueue workQueue;
    private KeyWordsService fundKeyWordsService;
    private InputDataService fundInputDataService;
    private ReverseIndexCalNodeService reverseIndexCalNodeService;
    private AuditService auditService;
    private WordsDicService wordsDicService;
    private ReverseIndexService reverseIndexService;

    public ReverseIndexThread(DocQueue workQueue, String threadName, MongoDatabase mongoDatabase,
                              InputDataService fundInputDataService, KeyWordsService fundKeyWordsService,
                              ReverseIndexCalNodeService reverseIndexCalNodeService, AuditService auditService,
                              WordsDicService wordsDicService, ReverseIndexService reverseIndexService) {
        this.workQueue = workQueue;
        this.threadName = threadName;
        this.fundKeyWordsService = fundKeyWordsService;
        this.fundInputDataService = fundInputDataService;
        this.reverseIndexCalNodeService = reverseIndexCalNodeService;
        this.auditService = auditService;
        this.wordsDicService = wordsDicService;
        this.reverseIndexService = reverseIndexService;

        this.fundKeyWordsService.setDatabase(mongoDatabase);
        this.fundInputDataService.setDatabase(mongoDatabase);
        this.reverseIndexCalNodeService.setDatabase(mongoDatabase);
        this.auditService.setDatabase(mongoDatabase);
        this.wordsDicService.setDatabase(mongoDatabase);
        this.reverseIndexService.setDatabase(mongoDatabase);
    }

    @Override
    public void run() {
        try {
            System.out.println(threadName + " start run");
            int idle = 0;
            while (idle < 10) {
                document = workQueue.poll();
                if (document != null) {
                    try {
                        String urlId = String.valueOf(document.get("urlId"));
                        Document doc = new Document();
                        doc.put("urlId", urlId);
                        document = fundInputDataService.findOne(doc);

                        Document doc01 = new Document();
                        doc01.put("urlId",urlId);
                        List<Document> list = fundKeyWordsService.findList(doc01);

                        //存储urlId对应的关键词信息
                        for(Document doc02: list){
                            //判断单词是否存在词典中，如果不存在，那么，添加。否则，取词的id
                            String keyWord = String.valueOf(doc02.get("keyWord"));
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
                            }else{
                                wordId = String.valueOf(doc04.get("wordId"));
                            }

                            //倒序索引创建
                            Document docNew02 = new Document();
                            docNew02.put("id",UUID.randomUUID().toString().replace("-", ""));
                            docNew02.put("wordId", wordId);
                            docNew02.put("urlId", String.valueOf(document.get("urlId")));
                            docNew02.put("hots", Double.parseDouble(String.valueOf(document.get("hots"))));
                            docNew02.put("title", String.valueOf(document.get("title")));
                            docNew02.put("summary", String.valueOf(document.get("summary")));
                            docNew02.put("url", String.valueOf(document.get("url")));
                            reverseIndexService.create(docNew02);
                        }

                        //流程推送
                        reverseIndexCalNodeService.endFlow(urlId);
                    } catch (Exception e) {
                        System.out.println("ClientThread: exception:" + e.getMessage());
                    }
                } else {
                    idle++;
                    Thread.sleep(1000);
                }
            }
            System.out.println(threadName + " end run...");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}