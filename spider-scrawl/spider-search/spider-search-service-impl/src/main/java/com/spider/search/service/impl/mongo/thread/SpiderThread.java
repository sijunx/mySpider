package com.spider.search.service.impl.mongo.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoDatabase;
import com.spider.search.service.api.mongo.*;
import com.spider.search.service.dto.DocQueue;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bson.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class SpiderThread implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(SpiderThread.class);

    private String threadName;
    private Document document;
    private DocQueue workQueue;
    private MongoDatabase mongoDatabase;

    private SpiderUrlService fundUrlService;
    private UrlDataExtractNodeService urlDataExtractNodeService;
    private InputDataService fundInputDataService;
    private AuditService auditService;
    private FileDownloadService fileDownloadService;
    private ImageService imageService;

    private String filePath;

    public SpiderThread(DocQueue workQueue, String threadName, MongoDatabase mongoDatabase,
                        SpiderUrlService fundUrlService, UrlDataExtractNodeService urlDataExtractNodeService,
                        InputDataService fundInputDataService, AuditService auditService, FileDownloadService fileDownloadService,
                        ImageService imageService, String filePath) {
        this.workQueue = workQueue;
        this.threadName = threadName;

        this.fundUrlService = fundUrlService;
        this.urlDataExtractNodeService = urlDataExtractNodeService;
        this.fundInputDataService = fundInputDataService;
        this.auditService = auditService;
        this.fileDownloadService = fileDownloadService;
        this.imageService = imageService;

        this.mongoDatabase = mongoDatabase;

        this.fundUrlService.setDatabase(this.mongoDatabase);
        this.urlDataExtractNodeService.setDatabase(this.mongoDatabase);
        this.fundInputDataService.setDatabase(this.mongoDatabase);
        this.auditService.setDatabase(this.mongoDatabase);
        this.imageService.setDatabase(this.mongoDatabase);

        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            logger.info("线程名称" + threadName + "start run");
            int idle = 0;
            while (idle < 10) {
                document = workQueue.poll();
                if (document != null) {
                    try {
                        String urlId = String.valueOf(document.get("urlId"));
                        Document doc = new Document();
                        doc.put("urlId", urlId);
                        document = fundUrlService.findOne(doc);
                        String url = String.valueOf(document.get("url"));
                        String rootUrl = String.valueOf(document.get("rootUrl"));
                        Double deep = Double.parseDouble(String.valueOf(document.get("deep")));
                        //  去重判断
                        String retStr = auditService.duplicateTwoMoreCheck(url);
                        JSONObject jsonObject = JSON.parseObject(retStr);
                        if (!String.valueOf(jsonObject.get("code")).equals("200")) {
                            logger.info("url已经存在两次" + url);
                            //  流程推送
                            urlDataExtractNodeService.endFlowNotPass(urlId);
                            continue;
                        }

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
                        if (docJsoup == null) {
                            continue;
                        }
                        logger.info("URL: " + url);
                        String title = "";
                        String txt = "";
                        String html = "";
                        Elements links_title = docJsoup.getElementsByTag("TITLE");
                        if (null != links_title && links_title.size() > 0) {
                            title = links_title.get(0).text();
                        }else{
                            continue;
                        }

                        //title标题审核
                        String retStr02 = auditService.titleCheck(title);
                        JSONObject jsonObject02 = JSON.parseObject(retStr02);
                        if (!String.valueOf(jsonObject02.get("code")).equals("200")) {
                            logger.info("title审核不通过 title:{}", title);
                            continue;
                        }

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
                        //txt审核
                        String str03 = auditService.txtCheck(txt);
                        JSONObject jsonObject03 = JSON.parseObject(str03);
                        if (!String.valueOf(jsonObject03.get("code")).equals("200")) {
                            logger.info("txt审核不通过 txt:{}", txt);
                            continue;
                        }

                        Elements links = docJsoup.select("a[href]");
                        Elements media = docJsoup.select("[src]");
                        Elements imports = docJsoup.select("link[href]");
                        Elements images = docJsoup.getElementsByTag("img");

                        logger.info("纯文本长度:{} html长度:{} 输出链接个数:{}", txt!=null?txt.length():0, html!=null?html.length():0, links!=null?links.size():0);

                        String url_link = url;
                        String deleteFlag = "0";
                        Date date = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
                        String createTime = simpleDateFormat.format(date);
                        //  搜索热度排行权重，初始都为30
                        Double hots = 30.0;
                        if (title != null && StringUtils.isNotEmpty(title)) {
                            //保存Url数据
                            Document document = new Document("url", url_link).
                                    append("txt", txt).
                                    append("summary", "").
                                    append("title", title).
                                    append("urlId", urlId).
                                    append("hots", hots);
                            fundInputDataService.create(document);
                            logger.info("文档插入成功 urlId:{}", urlId);
                            int mcount=0;
                            for(Element element : images){
                                if(mcount++>=3){
                                    break;
                                }
                                //获取每个img标签的src属性的内容，即图片地址，加"abs:"表示绝对路径
                                String imgSrc = element.attr("abs:src");
//                                //下载图片文件到电脑的本地硬盘上
//                                String imagePath = fileDownloadService.downLoadImage(imgSrc);
//                                Document docImage = new Document();
//                                docImage.put("imageId", UUID.randomUUID().toString().replace("-", ""));
//                                docImage.put("urlId", urlId);
//                                docImage.put("imagePath", imagePath);
//                                imageService.create(docImage);
                            }

                            //  流程推送
                            urlDataExtractNodeService.endFlow(urlId);
                        } else {
                            //  流程推送
                            urlDataExtractNodeService.endFlowNotPass(urlId);
                        }

                        int kcount = 0;
                        for (Element link : links) {
                            if (StringUtils.isBlank(link.attr("abs:href")) || StringUtils.isEmpty(link.attr("abs:href"))) {
                                continue;
                            }
                            String webURL = link.attr("abs:href");
                            //  去重判断
                            String retStr01 = auditService.duplicateCheck(String.valueOf(webURL));
                            JSONObject jsonObject01 = JSON.parseObject(retStr01);
                            if (!String.valueOf(jsonObject01.get("code")).equals("200")) {
                                logger.info("url已经存在{}", String.valueOf(webURL));
                                continue;
                            }
                            Document docNew = new Document();
                            docNew.put("urlId", UUID.randomUUID().toString().replace("-", ""));
                            docNew.put("url", String.valueOf(webURL));
                            docNew.put("deleteFlag", deleteFlag);
                            docNew.put("createTime", createTime);
                            docNew.put("rootUrl", rootUrl);
                            docNew.put("hots", hots);
                            docNew.put("deep", deep + 1);
                            fundUrlService.create(docNew);

                            kcount++;
                            if (kcount > 10) {
                                break;
                            }
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

