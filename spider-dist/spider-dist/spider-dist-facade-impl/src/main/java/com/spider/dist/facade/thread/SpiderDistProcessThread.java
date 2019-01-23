package com.spider.dist.facade.thread;

import com.spider.dist.service.api.ISpiderAuditService;
import com.spider.dist.service.api.ISpiderKeyWordExtractService;
import com.spider.dist.service.api.ISpiderScrawlUrlService;
import com.spider.dist.service.api.ISpiderSummaryService;
import com.spider.dist.service.dto.SpiderUrlDTO;
import com.spider.dist.service.enums.SpiderUrlStatusEnum;
import com.spider.dist.service.util.SpiderDistScrawlContext;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 爬取URL、标题审核、摘要提取、内容审核、关键词抽取
 * @author xusijun
 * @date 2019.1.23
 */
public class SpiderDistProcessThread implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(SpiderDistProcessThread.class);

    private ISpiderAuditService spiderAuditService;

    private ISpiderScrawlUrlService spiderScrawlUrlService;

    private ISpiderSummaryService spiderSummaryService;

    private ISpiderKeyWordExtractService spiderKeyWordExtractService;

    public SpiderDistProcessThread(ISpiderAuditService spiderAuditService,
                            ISpiderScrawlUrlService spiderScrawlUrlService,
                            ISpiderSummaryService spiderSummaryService,
                            ISpiderKeyWordExtractService spiderKeyWordExtractService){
        this.spiderAuditService = spiderAuditService;
        this.spiderScrawlUrlService = spiderScrawlUrlService;
        this.spiderSummaryService = spiderSummaryService;
        this.spiderKeyWordExtractService = spiderKeyWordExtractService;
    }

    /** 流程：爬取标题和文本信息->标题审核->摘要提取->摘要审核->获取关键词信息 */
    @Override
    public void run(){

        while(true) {
                try {
                Thread.sleep(5*1000);
                //  启动线程爬取、加工数据
                SpiderUrlDTO spiderUrlDTO = SpiderDistScrawlContext.getAndSetStartUrl();
                logger.info("处理线程开始，获取url:{}", spiderUrlDTO!=null?spiderUrlDTO.getUrl():null);
                if(spiderUrlDTO == null){
                    continue;
                }
                //  URL爬取(获取title和文本内容)
                spiderScrawlUrlService.startScrawl(spiderUrlDTO);
                //  标题审核
                boolean titleCheckPass = spiderAuditService.titleCheckPass(spiderUrlDTO.getTitle());
                if (!titleCheckPass) {
                    spiderUrlDTO.setStatus(SpiderUrlStatusEnum.UNPASSED.getCode());
                    return;
                }
                //  摘要提取
                String summary = spiderSummaryService.getSummary(spiderUrlDTO.getSummary());
                spiderUrlDTO.setSummary(summary);
                //  摘要审核
                boolean summaryCheckPass = spiderAuditService.summaryCheckPass(spiderUrlDTO.getSummary());
                if (!summaryCheckPass) {
                    spiderUrlDTO.setStatus(SpiderUrlStatusEnum.UNPASSED.getCode());
                    return;
                }
                //  更新状态为处理通过
                spiderUrlDTO.setStatus(SpiderUrlStatusEnum.PASSED.getCode());
                //  关键词提取
                List<String> keyWordsList = spiderKeyWordExtractService.getKeyWordList(spiderUrlDTO.getSummary());
                spiderUrlDTO.setKeyWords(keyWordsList);
            }catch (Exception e){
                logger.warn("从数据中心拉去数据出现异常 e:{}", ExceptionUtils.getStackTrace(e));
            }
        }
    }
}
