package com.spider.ant.impl.thread;

import com.spider.ant.impl.utils.MapUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SpiderThread implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(SpiderThread.class);

    private String threadName;

    public SpiderThread() {

    }

    @Override
    public void run() {
        try {
            logger.info("线程名称" + threadName + "start run");
            while(true){
                //从缓存获取url
                String url = (String)MapUtil.pop();
                if(StringUtils.isBlank(url)){
                    continue;
                }
                //对url进行爬取、解析
                Document docJsoup = getJsoup(url);
                if (docJsoup == null) {
                    continue;
                }
                logger.info("URL: " + url);
                //获取标题
                String title = getTitle(docJsoup);
                //获取正文
                String text = getString(docJsoup);
                //将url存储到发送缓存

            }
        }catch (Exception e){
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
    }

    private String getTitle(Document docJsoup){
        String title = "";
        Elements links_title = docJsoup.getElementsByTag("TITLE");
        if (null != links_title && links_title.size() > 0) {
            title = links_title.get(0).text();
        }
        return title;
    }

    private Document getJsoup(String url){
            org.jsoup.nodes.Document docJsoup = null;
        try {
            Thread.sleep(new Random(10).nextInt(10) * 1000);
            docJsoup = Jsoup.connect(url)
                    .data("query", "Java") // 请求参数
                    .userAgent("I'm jsoup") // 设置 User-Agent
                    .cookie("auth", "token") // 设置 cookie
                    .timeout(3000) // 设置连接超时时间
                    .post(); // 使用 POST 方法访问 URL
        } catch (Exception e) {
            logger.warn("网页爬取异常 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return docJsoup;
    }

    private String getString(org.jsoup.nodes.Document docJsoup){
        String txt = null;
        if (null != docJsoup.text()) {
            txt = docJsoup.text();
            //  把字符串中的的汉字取出来";
            String reg = "[^\u4e00-\u9fa5]";
            String txt01 = txt.replaceAll(reg, "");
            if (txt.length() > 2000) {
                txt = txt.substring(0, 1800);
            } else {
                txt = txt;
            }
        }
        return txt;
    }
}

