package com.spider.dist.service.impl;

import com.spider.dist.service.api.ISpiderScrawlUrlService;
import com.spider.dist.service.dto.SpiderUrlDTO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SpiderScrawlUrlServivceImpl implements ISpiderScrawlUrlService {

    private final static Logger logger = LoggerFactory.getLogger(SpiderScrawlUrlServivceImpl.class);

    @Override
    public void startScrawl(SpiderUrlDTO spiderUrlDTO){
        if(spiderUrlDTO==null || spiderUrlDTO.getUrl()==null){
            return ;
        }
        org.jsoup.nodes.Document docJsoup = null;
        try {
            Thread.sleep(new Random(10).nextInt(10) * 1000);
            docJsoup = Jsoup.connect(spiderUrlDTO.getUrl())
                    .data("query", "Java") // 请求参数
                    .userAgent("I'm jsoup") // 设置 User-Agent
                    .cookie("auth", "token") // 设置 cookie
                    .timeout(10000) // 设置连接超时时间
                    .post(); // 使用 POST 方法访问 URL
        } catch (Exception e) {
            logger.warn("网页爬取异常 e:{}", ExceptionUtils.getStackTrace(e));
        }
        if (docJsoup == null) {
            return;
        }
        //  获取标题
        String title = null;
        Elements links_title = docJsoup.getElementsByTag("TITLE");
        if (null != links_title && links_title.size() > 0) {
            title = links_title.get(0).text();
        }else{
            return ;
        }
        spiderUrlDTO.setTitle(title);
        logger.info("爬取的网页文本:{}", docJsoup.text());
        //  获取摘要
        String summary = "";
        if (null != docJsoup.text()) {
            //  把字符串中的的汉字取出来";
            String reg = "[^\u4e00-\u9fa5]";
            String txt = docJsoup.text().replaceAll(reg, "");
            if (txt.length() > 2000) {
                summary = txt.substring(0, 1800);
            } else {
                summary = txt;
            }
        }
        logger.info("摘要为 :{}", summary);
        spiderUrlDTO.setSummary(summary);
        return ;
    }
}
