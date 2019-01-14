package com.spider.search.service.impl.mongo;

import com.spider.search.service.api.mongo.FlowService;
import com.spider.search.service.api.mongo.SummaryExtractNodeService;
import com.spider.search.service.enums.SpiderNodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SummaryExtractNodeServiceImpl extends AbstractSpiderBaseService implements SummaryExtractNodeService {

    private final static Logger logger = LoggerFactory.getLogger(SummaryExtractNodeServiceImpl.class);

    @Autowired
    private FlowService flowService;

    //  节点01：根据url获取数据，存入data表
    //  节点02：根据url对应的data，抽取关键词
    //  节点03：根据关键词，计算摘要

    //  节点01：计算url对应data和主题之间的相似度
    //  节点02：根据url与主题之间的相似度，计算热点

    @Override
    public void startFlow(String urlId) {
        try {
            flowService.setDatabase(this.mongoDatabase);
            Document flow = this.getFlow(urlId);
            logger.info("开始节点【SUMMARYEXTRACT】");
            if(flow.get("flowId")!=null && StringUtils.isNotBlank(String.valueOf(flow.get("flowId")))){
                flow.put("startFlag", "1");
                flow.put("pushQueueFlag", "1");
                flowService.modify(flow);
            }
        } catch (Exception e) {
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public void endFlow(String urlId) {
        try {
            flowService.setDatabase(this.mongoDatabase);
            Document flow = this.getFlow(urlId);
            logger.info("结束节点【SUMMARYEXTRACT】");

            if(flow.get("flowId")!=null && StringUtils.isNotBlank(String.valueOf(flow.get("flowId")))){
                flow.put("endFlag", "1");
                flowService.modify(flow);
            }
            logger.info("插入下一个节点【REVERSEINDEXCAL】");

            Document doc = new Document();
            doc.put("flowId", UUID.randomUUID().toString().replace( "-", ""));
            doc.put("urlId", urlId);
            doc.put("nodeCode", SpiderNodeEnum.REVERSEINDEXCAL.getValue());
            doc.put("startFlag", "0");
            doc.put("pushQueueFlag", "0");
            doc.put("endFlag", "0");
            doc.put("pushCounts", 0);
            doc.put("seqNo", Double.parseDouble(String.valueOf(flow.get("seqNo")))+1);
            flowService.create(doc);
        } catch (Exception e) {
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
    }

    public Document getFlow(String urlId) {
        Document document=null;
        try{
            flowService.setDatabase(mongoDatabase);
            Document doc = new Document();
            doc.put("urlId", urlId);
            doc.put("nodeCode", SpiderNodeEnum.SUMMARYEXTRACT.getValue());
            document = flowService.findOne(doc);
            logger.info("获取当前节点【SUMMARYEXTRACT】");
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return document;
    }

    @Override
    public void createFlow(String urlId) {
        try {
            flowService.setDatabase(mongoDatabase);
            Document doc = new Document();
            doc.put("flowId", UUID.randomUUID().toString().replace("-", ""));
            doc.put("urlId", urlId);
            doc.put("nodeCode", SpiderNodeEnum.SUMMARYEXTRACT.getValue());
            doc.put("startFlag", "0");
            doc.put("pushQueueFlag", "0");
            doc.put("endFlag", "0");
            doc.put("pushCounts", 0);
            doc.put("seqNo", 1);
            flowService.create(doc);
            logger.info("创建当前节点【SUMMARYEXTRACT】");
        }catch (Exception e){
            logger.warn("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public void endFlowNotPass(String urlId) {
        try {
            flowService.setDatabase(this.mongoDatabase);
            Document flow = this.getFlow(urlId);
            logger.info("结束节点【SUMMARYEXTRACT】");

            if(flow.get("flowId")!=null && StringUtils.isNotBlank(String.valueOf(flow.get("flowId")))){
                flow.put("endFlag", "1");
                flowService.modify(flow);
            }
        } catch (Exception e) {
            logger.info("endFlowNotPass异常-------------------------------");
        }
    }
}