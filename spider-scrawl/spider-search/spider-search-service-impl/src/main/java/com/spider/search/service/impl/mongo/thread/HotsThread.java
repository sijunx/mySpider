package com.spider.search.service.impl.mongo.thread;

import com.mongodb.client.MongoDatabase;
import com.spider.search.service.api.mongo.HotsCalNodeService;
import com.spider.search.service.api.mongo.InputDataService;
import com.spider.search.service.api.mongo.UrlSimilarService;
import com.spider.search.service.dto.DocQueue;
import org.bson.Document;

/**
 *
 * 功能概要：爬虫工作类，主要实现类
 *
 * @author hwz
 */
public class HotsThread implements Runnable{

    private String threadName;
    private Document document;
    private DocQueue workQueue;
    private InputDataService fundInputDataService;
    private UrlSimilarService fundUrlSimilarService;
    private HotsCalNodeService hotsCalNodeService;

    public HotsThread(DocQueue workQueue, String threadName, MongoDatabase mongoDatabase, InputDataService fundInputDataService, UrlSimilarService fundUrlSimilarService, HotsCalNodeService hotsCalNodeService) {
        this.workQueue = workQueue;
        this.threadName = threadName;
        this.fundInputDataService = fundInputDataService;
        this.fundUrlSimilarService = fundUrlSimilarService;
        this.hotsCalNodeService = hotsCalNodeService;

        this.fundInputDataService.setDatabase(mongoDatabase);
        this.fundUrlSimilarService.setDatabase(mongoDatabase);
        this.hotsCalNodeService.setDatabase(mongoDatabase);
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

                    Document doc = new Document();
                    doc.put("urlId", urlId);
                    document = fundInputDataService.findOne(doc);

                    ///////////////////////////////////////////////////////////////////////////////////////////////////
                    //  根据与主题相似度表中的相似度数据，来计算热度值
                    //  找到主题信息
                    //  热度值=主题热度*与主题相似度
                    ///////////////////////////////////////////////////////////////////////////////////////////////////

                    Document doc01 = new Document();
                    doc01.put("urlId", urlId);
                    Document docSimilar = fundUrlSimilarService.findOne(doc01);

                    Document doc02 = new Document();
                    doc02.put("urlId", docSimilar.getString("urlIdSeed"));
                    Document docSeed = fundInputDataService.findOne(doc02);

                    Double hots = Double.parseDouble(String.valueOf(docSeed.get("hots")))*Double.parseDouble(String.valueOf(docSimilar.get("similar")));
                    document.put("hots", hots);
                    fundInputDataService.modify(document);
                    //推送流程节点结束
                    hotsCalNodeService.endFlow(document.getString("urlId"));
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
