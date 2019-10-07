package com.spider.ant.impl.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spider.ant.impl.constant.DataCenterUrlConstant;
import com.spider.ant.impl.utils.MapUtil;
import com.spider.base.http.SpiderHttpUtil;
import com.spider.base.rsa.RSACodeUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SpiderRcvThread implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(SpiderRcvThread.class);

    private String threadName;

    public SpiderRcvThread() {

    }

    @Override
    public void run() {
        try {
            logger.info("线程名称" + threadName + "start run");
            while(true){
                try {
                    //如果当前的url存储容量低于0，那么，向数据中心请求url
                    if (MapUtil.getCacheSize() > 10) {
                        continue;
                    }

                    //发送数据中心
                    String sendUrl = DataCenterUrlConstant.getPollUrl();
                    Map<String, String> headMap = new HashMap<>();
                    String sign = RSACodeUtil.encode("whoAmI");
                    headMap.put("signature", sign);

                    Map<String, String> paramsMap = new HashMap<>();
                    paramsMap.put("test", "test");

                    String charset = "UTF-8";
                    long milliseconds = 10 * 1000;
                    System.out.println("xxx");
                    String result = SpiderHttpUtil.sendPostJson(sendUrl, headMap, paramsMap, charset, milliseconds);
                    logger.info("获取的结果:{}", result);
                    System.out.println("获取的结果:"+ JSON.toJSONString(result));
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    JSONArray jsonArray = JSON.parseArray(jsonObject.getString("data"));
                    if(jsonArray!=null && jsonArray.size()>0){
                        for(int icount=0; icount<jsonArray.size(); icount++){
                           String url01 = jsonArray.getString(icount);
                           MapUtil.setCache(url01, url01);
                        }
                    }
                    System.out.println("缓存大小:"+MapUtil.getCacheSize());

                    Thread.sleep(10 * 1000);
                }catch (Exception e){
                    System.out.println("异常信息"+ExceptionUtils.getStackTrace(e));
                }
            }
        }catch (Exception e){
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
    }

    public static void main(String[] arg){
        Thread thread = new Thread(new SpiderRcvThread());
        thread.start();
    }
}

