package com.spider.base.http;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DingDingUtil {

    private static final String CONTENT_CONSTANT = "content";
    private static final String MSG_TYPE_CONSTANT = "msgtype";
    private static final String TEXT_CONSTANT = "text";
    private static final String UFT_CONSTANT = "utf-8";
    private static final String CONTENT_TYPE_CONSTANT = "Content-Type";
    private static final String APPLICATION_JSON_CONSTANT = "application/json; charset=utf-8";

    public static void sendDingMessage(String url, String content){

        JSONObject contentJsonObject = new JSONObject();
        contentJsonObject.put(CONTENT_CONSTANT, content);
        //消息内容拼接
        JSONObject mesgJsonObject = new JSONObject();
        mesgJsonObject.put(MSG_TYPE_CONSTANT, TEXT_CONSTANT);
        mesgJsonObject.put(TEXT_CONSTANT, contentJsonObject);
        StringEntity textMsg = new StringEntity(mesgJsonObject.toString(), UFT_CONSTANT);
        //调用http请求
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(textMsg);
        httppost.addHeader(CONTENT_TYPE_CONSTANT, APPLICATION_JSON_CONSTANT);
        HttpClient httpclient = HttpClients.createDefault();
        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity(), UFT_CONSTANT);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
