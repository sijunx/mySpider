package com.spider.search.service.impl.mongo.cal;

import com.mongodb.client.MongoDatabase;
import com.spider.base.mongo.MongoConnUtil;
import com.spider.search.service.api.mongo.*;
import com.spider.search.service.dto.DocQueue;
import com.spider.search.service.impl.mongo.AbstractSpiderBaseService;
import com.spider.search.service.impl.mongo.thread.SpiderThread;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class SpiderCalServiceImpl extends AbstractSpiderBaseService implements SpiderCalService {

    private final static Logger logger = LoggerFactory.getLogger(SpiderCalServiceImpl.class);

    @Autowired
    private SpiderUrlService fundUrlService;
    @Autowired
    private UrlDataExtractNodeService urlDataExtractNodeService;
    @Autowired
    private InputDataService fundInputDataService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private FileDownloadService fileDownloadService;
    @Autowired
    private ImageService imageService;

    private String filePath = "/mnt/image/";

    @Override
	public void cal(){
        MongoConnUtil mongoConnUtil = new MongoConnUtil();
        MongoDatabase mongoDatabase = mongoConnUtil.initConn();

        fundUrlService.setDatabase(mongoDatabase);
        urlDataExtractNodeService.setDatabase(mongoDatabase);
        fundInputDataService.setDatabase(mongoDatabase);
        auditService.setDatabase(mongoDatabase);
        imageService.setDatabase(mongoDatabase);

        //	线程池核心数
        Integer corePoolSize = 5;
        //	线程池最大数
        Integer maxPoolSize = 10;
		//	队列最大数
        Integer maxQueueSize = 30;
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
                Thread.sleep(5000);
                if ((workQueue.size() > maxPoolSize && executor.getActiveCount() < maxPoolSize)
                        || (workQueue.size() > 0 && executor.getActiveCount() < corePoolSize)) {
                    executor.submit(new SpiderThread(workQueue, "thead-" + icount++, mongoDatabase, fundUrlService,
                            urlDataExtractNodeService, fundInputDataService, auditService,
                            fileDownloadService, imageService, filePath));
                } else if (workQueue.size() <= maxQueueSize / 2) {
                    try {
                        Document doc = new Document();
                        doc.put("deleteFlag", "0");
                        doc.put("deep", 8);
                        List<Document> list = fundUrlService.findList(doc);
                        if (list != null) {
                            for (int jcount = 0; jcount < list.size(); jcount++) {
                                if(workQueue.getQueueSize() < maxQueueSize){
                                    Document doc01 = list.get(jcount);
                                    doc01.put("deleteFlag", "1");
                                    fundUrlService.modify(doc01);
                                    String urlId = String.valueOf(doc01.get("urlId"));
                                    //  流程创建节点
                                    urlDataExtractNodeService.createFlow(urlId);
                                    urlDataExtractNodeService.startFlow(urlId);
                                    Document doc02 = new Document();
                                    doc02.put("urlId", urlId);
                                    workQueue.add(doc02);
                                } else {
                                    break;
                                }
                            }
                        } else {
                            Thread.sleep(10000);
                        }
                    } catch (Exception e) {
                        logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
                    }
                }
            }
        }catch (Exception e){
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }finally {
            mongoConnUtil.connClose();
        }
    }
}



