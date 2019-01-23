package com.spider.dist.facade.impl;

import com.spider.base.utils.SpiderThreadPoolExcutorUtil;
import com.spider.dist.facade.api.ISpiderDistScrawlFacade;
import com.spider.dist.facade.thread.SpiderDistPollThread;
import com.spider.dist.facade.thread.SpiderDistProcessThread;
import com.spider.dist.facade.thread.SpiderDistPushThread;
import com.spider.dist.service.api.ISpiderAuditService;
import com.spider.dist.service.api.ISpiderKeyWordExtractService;
import com.spider.dist.service.api.ISpiderScrawlUrlService;
import com.spider.dist.service.api.ISpiderSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpiderDistScrawlFacadeImpl implements ISpiderDistScrawlFacade {

    @Autowired
    private ISpiderAuditService spiderAuditService;
    @Autowired
    private ISpiderScrawlUrlService spiderScrawlUrlService;
    @Autowired
    private ISpiderSummaryService spiderSummaryService;
    @Autowired
    private ISpiderKeyWordExtractService spiderKeyWordExtractService;

    public void start(){
        //  获取线程数据
        SpiderDistPollThread spiderDistPollThread = new SpiderDistPollThread();
        SpiderThreadPoolExcutorUtil.getThreadPoolExcutor().submit(spiderDistPollThread);
        //  加工数据线程
        SpiderDistProcessThread spiderDistProcessThread = new SpiderDistProcessThread(spiderAuditService, spiderScrawlUrlService, spiderSummaryService, spiderKeyWordExtractService);
        SpiderThreadPoolExcutorUtil.getThreadPoolExcutor().submit(spiderDistProcessThread);
        //  推送数据线程
        SpiderDistPushThread spiderDistPushThread = new SpiderDistPushThread();
        SpiderThreadPoolExcutorUtil.getThreadPoolExcutor().submit(spiderDistPushThread);
    }

}
