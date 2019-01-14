package com.spider.search.service.impl.mongo;

import com.mongodb.client.MongoDatabase;
import com.spider.search.service.api.mongo.FileDownloadService;
import com.spider.search.service.impl.mongo.thread.HotsThread;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class FileDownloadServiceImpl implements FileDownloadService {

    private final static Logger logger = LoggerFactory.getLogger(FileDownloadServiceImpl.class);

    private MongoDatabase mongoDatabase;

//    @Value("${com.file.path}")
    private String filePath = "/mnt/image/";
//    com.file.path=/mnt/image/

    public void setDatabase(MongoDatabase mongoDatabase){
        this.mongoDatabase = mongoDatabase;
    }

    public String downLoadImage(String imgUrl){
        String fileName=null;
        try {
            //图片url中的前面部分：例如"http://images.csdn.net/"
            String beforeUrl = imgUrl.substring(0,imgUrl.lastIndexOf("/")+1);
            //图片url中的后面部分：例如“20150529/PP6A7429_副本1.jpg”
            fileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1);
            //编码之后的fileName，空格会变成字符"+"
            String newFileName = URLEncoder.encode(fileName, "UTF-8");
            //把编码之后的fileName中的字符"+"，替换为UTF-8中的空格表示："%20"
            newFileName = newFileName.replaceAll("\\+", "\\%20");
            //编码之后的url
            imgUrl = beforeUrl + newFileName;
            //创建文件目录
            File files = new File(filePath);
            if (!files.exists()) {
                files.mkdirs();
            }
            //获取下载地址
            URL url = new URL(imgUrl);
            //链接网络地址
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            //获取链接的输出流
            InputStream is = connection.getInputStream();
            //创建文件，fileName为编码之前的文件名
            File file = new File(filePath + fileName);
            //根据输入流写入文件
            FileOutputStream out = new FileOutputStream(file);
            int i = 0;
            while((i = is.read()) != -1){
                out.write(i);
            }
            out.close();
            is.close();
        } catch (Exception e) {
            logger.info("异常信息 e:{}", ExceptionUtils.getStackTrace(e));
        }
        return (filePath+fileName);
    }
}