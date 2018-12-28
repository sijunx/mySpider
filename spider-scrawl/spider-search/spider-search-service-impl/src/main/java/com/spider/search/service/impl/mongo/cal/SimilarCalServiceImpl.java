package com.spider.search.service.impl.mongo.cal;

import com.mongodb.client.MongoDatabase;
import com.spider.search.service.api.mongo.*;
import com.spider.search.service.dto.DocQueue;
import com.spider.search.service.enums.SpiderNodeEnum;
import com.spider.search.service.impl.mongo.AbstractSpiderBaseService;
import com.spider.search.service.impl.mongo.thread.SimilarThread;
import com.spider.search.service.util.MongoConnUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class SimilarCalServiceImpl extends AbstractSpiderBaseService implements SimilarCalService {

    @Autowired
    private FlowService flowService;
    @Autowired
    private KeyWordsService fundKeyWordsService;
    @Autowired
    private UrlSimilarService fundUrlSimilarService;
    @Autowired
    private TopicService fundTopicService;
    @Autowired
    private SimilarCalNodeService similarCalNodeService;

    @Override
	public void cal(){
        MongoConnUtil mongoConnUtil = new MongoConnUtil();
        MongoDatabase mongoDatabase = mongoConnUtil.initConn();

        flowService.setDatabase(mongoDatabase);
        fundKeyWordsService.setDatabase(mongoDatabase);
        fundUrlSimilarService.setDatabase(mongoDatabase);
        fundTopicService.setDatabase(mongoDatabase);
        similarCalNodeService.setDatabase(mongoDatabase);

        //	线程池核心数
        Integer corePoolSize = 1;
        //	线程池最大数
        Integer maxPoolSize = 1;
		//	队列最大数
        Integer maxQueueSize = 2;
        //	线程池执行器
        ThreadPoolExecutor executor;
        //	工作队列
        DocQueue workQueue = new DocQueue();
		//	线程池定义
		LinkedBlockingDeque<Runnable> excutorQueue = new LinkedBlockingDeque<Runnable>(maxPoolSize);
		executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,60L, TimeUnit.SECONDS, excutorQueue);
		workQueue.setQueueSize(maxQueueSize);
		int icount = 0;
		try {
            while (true) {
                if ((workQueue.size() > maxPoolSize && executor.getActiveCount() < maxPoolSize)
                        || (workQueue.size() > 0 && executor.getActiveCount() < corePoolSize)) {
                    executor.submit(new SimilarThread(workQueue, "thead-" + icount++, mongoDatabase, fundKeyWordsService, fundUrlSimilarService, fundTopicService, similarCalNodeService));
                } else if (workQueue.size() <= maxQueueSize / 2) {
                    try {
                        Document doc = new Document();
                        doc.put("nodeCode", SpiderNodeEnum.SIMILARCAL.getValue());
                        doc.put("startFlag", "0");
                        List<Document> list = flowService.findList(doc);
                        if(null != list){
                            for(int jcount=0; jcount<list.size(); jcount++){
                                if(workQueue.getQueueSize() < maxQueueSize){
                                    Document doc01 = list.get(jcount);
                                    String urlId = String.valueOf(doc01.get("urlId"));
                                    similarCalNodeService.startFlow(urlId);
                                    Document doc02 = new Document();
                                    doc02.put("urlId", urlId);
                                    workQueue.add(doc02);
                                } else {
                                    break;
                                }
                            }
                        }else{
                            Thread.sleep(10000);
                        }
                    } catch (Exception e) {
                        System.out.println("查询数据库url失败");
                    } finally {
//                    Thread.sleep(10000);
                    }
                } else {
//				System.out.println("发生异常------------");
                }
            }
        }catch (RuntimeException e){
		    ;
        }finally {
            mongoConnUtil.connClose();
        }
    }
}



