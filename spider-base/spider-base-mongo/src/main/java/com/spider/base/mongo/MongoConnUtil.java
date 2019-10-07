package com.spider.base.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoConnUtil {

    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;

    public MongoDatabase initConn(){
        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
        //ServerAddress()两个参数分别为 服务器地址 和 端口  134.175.107.11
        ServerAddress serverAddress01 = new ServerAddress("134.175.107.11",27091);
//        ServerAddress serverAddress02 = new ServerAddress("134.175.107.11",27092);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress01);
//        addrs.add(serverAddress02);
        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential credential01 = MongoCredential.createScramSha1Credential("moon62", "myDb", "grubby62".toCharArray());
//        MongoCredential credential02 = MongoCredential.createScramSha1Credential("moon62", "myDb", "grubby62".toCharArray());

        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential01);
//        credentials.add(credential02);

        //通过连接认证获取MongoDB连接
        this.mongoClient = new MongoClient(addrs,credentials);

        //连接到数据库  spider_dev
        this.mongoDatabase = this.mongoClient.getDatabase("myDb");

        return mongoDatabase;
    }

    public  void connClose(){
        this.mongoClient.close();
    }

    public static void main(String[] arg){
        MongoConnUtil mongoConnUtil = new MongoConnUtil();
        mongoConnUtil.initConn();
        MongoDatabase mongoDatabase = mongoConnUtil.mongoDatabase;

        MongoCollection<Document> collection = mongoDatabase.getCollection("urlInfo");
        Document doc03 = new Document();
            doc03.put("urlId",  "1");
            doc03.put("urlIdSeed",  "2");
            doc03.put("similar",  0.9);
        collection.insertOne(doc03);
        int i = 0;
    }
}
