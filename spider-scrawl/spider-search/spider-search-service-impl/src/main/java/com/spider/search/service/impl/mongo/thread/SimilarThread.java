package com.spider.search.service.impl.mongo.thread;

import com.mongodb.client.MongoDatabase;
import com.spider.search.service.api.mongo.KeyWordsService;
import com.spider.search.service.api.mongo.SimilarCalNodeService;
import com.spider.search.service.api.mongo.TopicService;
import com.spider.search.service.api.mongo.UrlSimilarService;
import com.spider.search.service.dto.DocQueue;
import com.spider.search.service.util.SimilarUtils;
import org.bson.Document;

import java.util.List;
import java.util.Vector;

public class SimilarThread implements Runnable{

    private String threadName;
    private Document document;
    private DocQueue workQueue;
    private KeyWordsService fundKeyWordsService;
    private UrlSimilarService fundUrlSimilarService;
    private TopicService fundTopicService;
    private SimilarCalNodeService similarCalNodeService;

    public SimilarThread(DocQueue workQueue, String threadName, MongoDatabase mongoDatabase, KeyWordsService fundKeyWordsService, UrlSimilarService fundUrlSimilarService, TopicService fundTopicService, SimilarCalNodeService similarCalNodeService) {
        this.workQueue = workQueue;
        this.threadName = threadName;
        this.fundKeyWordsService = fundKeyWordsService;
        this.fundUrlSimilarService = fundUrlSimilarService;
        this.fundTopicService = fundTopicService;
        this.similarCalNodeService = similarCalNodeService;

        this.fundKeyWordsService.setDatabase(mongoDatabase);
        this.fundUrlSimilarService.setDatabase(mongoDatabase);
        this.fundTopicService.setDatabase(mongoDatabase);
        this.similarCalNodeService.setDatabase(mongoDatabase);
    }

    @Override
    public void run() {
        System.out.println(threadName + " start run");
        int idle = 0;
        while (idle < 10) {
            document = workQueue.poll();
            if (document != null) {
                try {
                    String urlId = String.valueOf(document.get("urlId"));

                    Document doc01 = new Document();
                    doc01.put("topicCode", "IT");
                    Document docTopic = fundTopicService.findOne(doc01);
                    String urlIdSeed = String.valueOf(docTopic.get("urlId"));

                    Vector<String> t2 = new Vector<String>();
                    Document docSeed = new Document();
                    docSeed.put("urlId",String.valueOf(docTopic.get("urlId")));
                    List<Document> listSeed = fundKeyWordsService.findByEqualUrlId(docSeed);
                    for(int icount=0; icount<listSeed.size(); icount++){
                        String keyWord = String.valueOf(listSeed.get(icount).get("keyWord"));
                        t2.add(keyWord);
                    }

                    Vector<String> t1 = new Vector<String>();
                    List<Document> list = fundKeyWordsService.findByEqualUrlId(document);
                    for(int icount=0; icount<list.size(); icount++){
                        String keyWord = String.valueOf(list.get(icount).get("keyWord"));
                        t1.add(keyWord);
                    }
                    Double similar = SimilarUtils.getSimilarity(t1, t2);
                    Document doc04 = new Document("urlId", urlId).
                            append("urlIdSeed", urlIdSeed).
                            append("similar", similar);
                    fundUrlSimilarService.create(doc04);
                    //  流程推送
                    similarCalNodeService.endFlow(document.getString("urlId"));
                } catch (Exception e) {
                    System.out.println("Similar Thread ClientThread: exception:" + e.getMessage());
                }
            } else {
                idle++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(threadName + " end run...");
    }
}
