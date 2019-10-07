package com.spider.ant.impl.thread;

import com.alibaba.fastjson.JSON;
import com.spider.ant.impl.constant.DataCenterUrlConstant;
import com.spider.ant.service.dto.SpiderUrlDto;
import com.spider.base.http.SpiderHttpUtil;
import com.spider.base.rsa.RSACodeUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpiderSendThread implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(SpiderSendThread.class);

    private String threadName;

    private Map<String, SpiderUrlDto> urlMap = new ConcurrentHashMap<String, SpiderUrlDto>();

    public SpiderSendThread() {

    }

    @Override
    public void run() {
        try {
            logger.info("线程名称" + threadName + "start run");

            while(true){
                SpiderUrlDto myD = new SpiderUrlDto();
                myD.setUrl("baidu.com");
                myD.setTitle("title test");
                myD.setContent("content test");
                urlMap.put("baidu.com", myD);
                try {
                    if (urlMap != null && urlMap.size() <= 0) {
                        continue;
                    }
                    SpiderUrlDto spiderUrlDto = null;
                    synchronized (urlMap) {
                        for (Map.Entry entry : urlMap.entrySet()) {
                            String key = (String) entry.getKey();
                            spiderUrlDto = (SpiderUrlDto) entry.getValue();
                            urlMap.remove(key);
                        }
                    }
                    //发送数据中心
                    String sendUrl = DataCenterUrlConstant.getPushUrl();
                    Map<String, String> headMap = new HashMap<>();
                    String sign = RSACodeUtil.encode("whoAmI");
                    headMap.put("signature", sign);

                    headMap.put("data", JSON.toJSONString(spiderUrlDto));
                    Map<String, String> paramsMap = new HashMap<>();
                    paramsMap.put("test", "test");

                    String charset = "UTF-8";
                    long milliseconds = 10 * 1000;
                    System.out.println("发送开始");
                    SpiderHttpUtil.sendPostJson(sendUrl, headMap, paramsMap, charset, milliseconds);
                    System.out.println("发送结束");
                    Thread.sleep(10 * 1000);

                }catch (Exception e){
                    System.out.println("异常信息"+ExceptionUtils.getStackTrace(e));
                }
            }
        }catch (Exception e){
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
    }

    public static void main(String[] ar){
        SpiderSendThread spiderSendThread = new SpiderSendThread();
        spiderSendThread.run();
    }
}

