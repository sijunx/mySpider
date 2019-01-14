package com.spider.search.service.util;

import com.spider.search.service.dto.SpiderUrlServiceDTO;
import com.spider.search.service.impl.mongo.FlowServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpiderQueue {

    private final static Logger logger = LoggerFactory.getLogger(SpiderQueue.class);

    /** 存储器 */
    private List<SpiderUrlServiceDTO> queue;
    //  线程池最大数
    private int maxPoolSize = 1024;

    public void setQueueSize(int size) {
        queue = new ArrayList<SpiderUrlServiceDTO>(size);
    }
    public  int getQueueSize(){
        return queue.size();
    }

    public synchronized void add(SpiderUrlServiceDTO FundUrl) {
        queue.add(FundUrl);
    }

    public synchronized SpiderUrlServiceDTO poll() {
        if (queue.isEmpty()) {
            return null;
        }
        //控制台打印结果，方便查看
        SpiderUrlServiceDTO FundUrl = queue.remove(0);
        logger.info("SpiderQueue,poll,SpiderUrlServiceDTO=" + FundUrl.toString() + ",remain size=" + queue.size());
        return FundUrl;
    }

    public synchronized SpiderUrlServiceDTO peek() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.get(0);
    }

    public synchronized boolean isExsit(SpiderUrlServiceDTO spiderUrlServiceDTO) {
        return queue.contains(spiderUrlServiceDTO);
    }

    public synchronized int size() {
        return queue.size();
    }

    public void printAll() {
        logger.info("Enter printAll.");
        for (SpiderUrlServiceDTO spiderUrlServiceDTO : queue) {
            logger.info(spiderUrlServiceDTO);
        }
    }
}
