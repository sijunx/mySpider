package com.spider.base.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MyHttpUtil {

    private static final String CONTENT_CONSTANT = "content";
    private static final String MSG_TYPE_CONSTANT = "msgtype";
    private static final String TEXT_CONSTANT = "text";
    private static final String UFT_CONSTANT = "utf-8";
    private static final String CONTENT_TYPE_CONSTANT = "Content-Type";
    private static final String APPLICATION_JSON_CONSTANT = "application/json; charset=utf-8";

    public static String send(String url, String message){
        //消息内容拼接
        JSONObject mesgJsonObject = new JSONObject();
        mesgJsonObject.put("message", message);
        StringEntity textMsg = new StringEntity(mesgJsonObject.toString(), UFT_CONSTANT);
        //调用http请求
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(textMsg);
        httppost.addHeader(CONTENT_TYPE_CONSTANT, APPLICATION_JSON_CONSTANT);
        HttpClient httpclient = HttpClients.createDefault();
        String result = null;
        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), UFT_CONSTANT);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static void main(String[] arg){


        String url = "http://106.12.161.30:8091/mySpider/recvHttpMessage";
        send(url, "ssssss");
    }
}
