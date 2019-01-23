package com.spider.dist.service.impl;

import com.spider.dist.service.api.ISpiderSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpiderSummaryServiceImpl implements ISpiderSummaryService {

    private final static Logger logger = LoggerFactory.getLogger(SpiderSummaryServiceImpl.class);

    @Override
    public String getSummary(String txt){
        //  获取摘要
        String summary = "";
        if (null != txt) {
            //  把字符串中的的汉字取出来";
            String reg = "[^\u4e00-\u9fa5]";
            summary = txt.replaceAll(reg, "");
            if (txt.length() > 2000) {
                summary = summary.substring(0, 1800);
            }
        }
        return summary;
    }
}
