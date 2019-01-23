package com.spider.base.http;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SpiderHttpUtil {

    private final static Logger logger = LoggerFactory.getLogger(SpiderHttpUtil.class);

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static OkHttpClient okHttpClient = null;
    private static final String UNEXPECTED_CODE = "Unexpected code ";
    static{
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .sslSocketFactory(getSSLSocketFactory())
                .hostnameVerifier(getHostnameVerifier())
                .build();
    }

    public static String sendPostJson(String url, Map<String,String> headMap, Map<String, String> paramsMap, String charset, long milliseconds) throws IOException {
        long startTime = System.currentTimeMillis();
        logger.info("url:{}",url);
        //  发送体构建
        RequestBody body = new FormBody.Builder().build();
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if(paramsMap!=null && paramsMap.size()>0){
            for(Map.Entry entry:paramsMap.entrySet()){
                formBodyBuilder.add(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
            body = formBodyBuilder.build();
        }
        okhttp3.Request.Builder builder2 = createBuilderWithHeader(headMap);
        Request request = builder2.url(url).post(body).build();
        String response = handleRequest(request, charset, milliseconds);
        long endTime = System.currentTimeMillis();
        logger.info("time:{}", (endTime - startTime) / 1000);
        return response;
    }

    private static okhttp3.Request.Builder createBuilderWithHeader(Map<String,String> headMap) {
        okhttp3.Request.Builder builder2 = new Request.Builder();
        if(null != headMap){
            Iterator<String> iter = headMap.keySet().iterator();
            while(iter.hasNext()){
                String key = iter.next();
                String value = headMap.get(key);
                builder2.addHeader(key, value);
            }
        }
        return builder2;
    }

    private static String handleRequest(Request request, String charset, long milliseconds) throws IOException {
        Response response;
        if (milliseconds <= 0) {
            response = okHttpClient.newCall(request).execute();
        } else {
            response = okHttpClient.newBuilder().connectTimeout(milliseconds, TimeUnit.MILLISECONDS)
                    .readTimeout(milliseconds, TimeUnit.MILLISECONDS).build().newCall(request).execute();
        }
        try (ResponseBody body = response.body()) {
            if (response.isSuccessful()) {
                return new String(response.body().bytes(), charset);
            } else {
                logger.error("handleRequest失败，Unexpected code  = " + response.code());
                throw new IOException(UNEXPECTED_CODE + response);
            }
        }
    }

    private static SSLSocketFactory getSSLSocketFactory(){
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }

    private static TrustManager[] getTrustManager() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }
}
