package com.spider.search.service.impl.mongo.thread;

import com.mongodb.client.MongoDatabase;
import com.spider.search.service.api.mongo.InputDataService;
import com.spider.search.service.api.mongo.KeyWordsService;
import com.spider.search.service.api.mongo.SummaryExtractNodeService;
import com.spider.search.service.dto.DocQueue;
import com.spider.search.service.impl.mongo.cal.SummaryExtractServiceImpl;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SummaryExtractThread implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(SummaryExtractThread.class);

    private String threadName;
    private Document document;
    private DocQueue workQueue;
    private KeyWordsService fundKeyWordsService;
    private InputDataService fundInputDataService;
    private SummaryExtractNodeService summaryExtractNodeService;

    public SummaryExtractThread(DocQueue workQueue, String threadName, MongoDatabase mongoDatabase, InputDataService fundInputDataService, KeyWordsService fundKeyWordsService, SummaryExtractNodeService summaryExtractNodeService) {
        this.workQueue = workQueue;
        this.threadName = threadName;
        this.fundKeyWordsService = fundKeyWordsService;
        this.fundInputDataService = fundInputDataService;
        this.summaryExtractNodeService = summaryExtractNodeService;

        this.fundKeyWordsService.setDatabase(mongoDatabase);
        this.fundInputDataService.setDatabase(mongoDatabase);
        this.summaryExtractNodeService.setDatabase(mongoDatabase);
    }

    @Override
    public void run() {
        try {
            logger.info(threadName + " start run");
            int idle = 0;
            while (idle < 10) {
                document = workQueue.poll();
                if (document != null) {
                    try {
                        String urlId = String.valueOf(document.get("urlId"));
                        Document doc01 = fundInputDataService.findOne(document);
                        List<Document> list01 = fundKeyWordsService.findByEqualUrlId(document);

                        if(null == list01 || list01.size() <= 0){
                            summaryExtractNodeService.endFlowNotPass(urlId);
                        }else {
                            String keyWord = String.valueOf(list01.get(0).get("keyWord"));
                            String txt = String.valueOf(doc01.get("txt") == null ? "" : doc01.get("txt"));
                            int indx = txt.indexOf(keyWord);
                            String summary = "";
                            if (indx > 70) {
                                summary = txt.substring(indx - 70, indx);
                            }
                            if (txt.length() > 140) {
                                summary = summary + txt.substring(indx + 1, indx + 70);
                            } else {
                                summary = txt;
                            }
                            doc01.put("summary", summary);
                            fundInputDataService.modify(doc01);
                            //  流程推送
                            summaryExtractNodeService.endFlow(urlId);
                        }
                    } catch (Exception e) {
                        logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
                    }
                } else {
                    idle++;
                    Thread.sleep(1000);
                }
            }
            logger.info(new StringBuilder().append(threadName).append("end run...").toString());
        }catch (Exception e){
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
