package com.spider.search.service.impl.mongo.thread;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoDatabase;
import com.spider.base.utils.KeyWordExtractUtil;
import com.spider.search.service.api.mongo.AuditService;
import com.spider.search.service.api.mongo.InputDataService;
import com.spider.search.service.api.mongo.KeyExtractNodeService;
import com.spider.search.service.api.mongo.KeyWordsService;
import com.spider.search.service.dto.DocQueue;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class KeyExtractThread implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(KeyExtractThread.class);

    private String threadName;
    private Document document;
    private DocQueue workQueue;
    private int maxQueueSize = 1024;
    private int maxKeyWords = 5;
    private KeyWordsService fundKeyWordsService;
    private InputDataService fundInputDataService;
    private KeyExtractNodeService keyExtractNodeService;
    private AuditService auditService;

    public KeyExtractThread(DocQueue workQueue, String threadName, MongoDatabase mongoDatabase,
                            InputDataService fundInputDataService, KeyWordsService fundKeyWordsService,
                            KeyExtractNodeService keyExtractNodeService, AuditService auditService) {
        this.workQueue = workQueue;
        this.threadName = threadName;
        this.fundKeyWordsService = fundKeyWordsService;
        this.fundInputDataService = fundInputDataService;
        this.keyExtractNodeService = keyExtractNodeService;
        this.auditService = auditService;

        this.fundKeyWordsService.setDatabase(mongoDatabase);
        this.fundInputDataService.setDatabase(mongoDatabase);
        this.keyExtractNodeService.setDatabase(mongoDatabase);
        this.auditService.setDatabase(mongoDatabase);
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
                        Document doc = new Document();
                        doc.put("urlId", urlId);
                        document = fundInputDataService.findOne(doc);

                        List<String> list01 = new ArrayList<String>();
                        KeyWordExtractUtil sh = new KeyWordExtractUtil();
                        String str = document.get("txt") == null ? "" : String.valueOf(document.get("txt"));
                        if(str.length()<=0){
                            keyExtractNodeService.endFlowNotPass(urlId);
                            continue;
                        }

                        list01 = sh.stringToArray(str);
                        Map<String, Integer> map = new HashMap<String, Integer>();
                        for (String obj : list01) {
                            if (map.containsKey(obj)) {//判断是否已经有该数值，如有，则将次数加1
                                map.put(obj, map.get(obj).intValue() + 1);
                            } else {
                                map.put(obj, 1);
                            }
                        }
                        //这里将map.entrySet()转换成list
                        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
                        //然后通过比较器来实现排序
                        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                            //升序排序
                            public int compare(Map.Entry<String, Integer> o1,
                                               Map.Entry<String, Integer> o2) {
                                return o2.getValue().compareTo(o1.getValue());
                            }
                        });
                        //黑单词判断
                        String passStatus = "P";
                        int icount = 0;
                        for (Map.Entry<String, Integer> mapping : list) {
                            if (++icount > maxKeyWords) {
                                break;
                            }
                            String retStr = auditService.blackWordsExists(mapping.getKey());
                            JSONObject jsonObject = JSONObject.parseObject(retStr);
                            if (!String.valueOf(jsonObject.get("code")).equals("200")) {
                                passStatus = "NP";
                                break;
                            }
                        }

                        if ("NP".equals(passStatus)) {
                            keyExtractNodeService.endFlowNotPass(urlId);
                            continue;
                        }

                        int jcount = 0;
                        for (Map.Entry<String, Integer> mapping : list) {
                            if (++jcount > maxKeyWords) {
                                break;
                            }
                            Document doc01 = new Document();
                            doc01.put("keyWordId",  StringUtils.replace(String.valueOf(UUID.randomUUID()), "-", ""));

                            doc01.put("urlId", urlId);
                            doc01.put("keyWord", mapping.getKey());
                            doc01.put("counts", mapping.getValue());
                            fundKeyWordsService.create(doc01);
                            logger.info("key:{} value:{}", mapping.getKey(), mapping.getValue());
                        }
                        //  流程推送
                        if (jcount > 0) {
                            keyExtractNodeService.endFlow(urlId);
                        } else {
                            keyExtractNodeService.endFlowNotPass(urlId);
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