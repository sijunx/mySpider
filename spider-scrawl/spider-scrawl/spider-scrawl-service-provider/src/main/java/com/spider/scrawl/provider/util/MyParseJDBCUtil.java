package com.spider.scrawl.provider.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyParseJDBCUtil {

    private MyParseJDBCUtil(){}

//    public static String url_encrypt="jdbc:mysql://106.12.161.30:3306/spider_dev?useUnicode=true&characterEncoding=UTF-8";
    public static String url_encrypt = null;
    public static String user = null;
    public static String password = null;

    public static void main(String[] args) throws Exception{
        String msg = "";
        parseBinlog(msg);
    }

    public static void parseBinlog(String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        String tableName = jsonObject.getString("table");
        String dataBase = jsonObject.getString("database");
        String operateType = jsonObject.getString("type");

        List<Map<String, String>> mapList = getDataFromJson(jsonObject);

        if (CollectionUtils.isEmpty(mapList)) {
            for (Map<String, String> map : mapList) {
                excuteSql(map, tableName, dataBase, operateType);
            }
        }
    }

    private static List<Map<String, String>> getDataFromJson(JSONObject jsonObject){
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        if(jsonArray == null ||jsonArray.size()<=0){
            return null;
        }
        List<Map<String, String>> dataList = new ArrayList<>();
        for(int icount=0; icount<jsonArray.size(); icount++){
            JSONObject object = jsonArray.getJSONObject(icount);
            Map<String, String> map = new HashMap<>();
            if(object==null || object.size()<=0){
                continue;
            }
            for(JSONObject.Entry entry: object.entrySet()){
                map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
            dataList.add(map);
        }
        return dataList;
    }

    public static void excuteSql(Map<String, String> map, String tableName, String dataBase,String operateType){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //建立数据库对象
            if( StringUtils.isBlank(url_encrypt)||StringUtils.isBlank(user)||StringUtils.isBlank(password)){
                Config appConfig = ConfigService.getAppConfig();
                url_encrypt = appConfig.getProperty("data.syn.jdbc.url", "");
                user = appConfig.getProperty("data.syn.jdbc.username", "");
                password = appConfig.getProperty("data.syn.jdbc.password", "");
            }
            conn  = DriverManager.getConnection(url_encrypt,user,password);
            //建立操作对象
            stmt= conn.createStatement();
            if (operateType.equals("INSERT")) {
                String insertSql = getInsertSql(map, tableName, dataBase);
                stmt.execute(insertSql);
            }
            if (operateType.equals("UPDATE")) {
                String id = map.get("id");
                String updateSql = getUpdateSql(map, tableName, dataBase);
                stmt.execute(updateSql);
            }
            if (operateType.equals("DELETE")) {
                String id = map.get("id");
                String delSql = getDeleteSql(map, tableName, dataBase);
                stmt.execute(delSql);
            }
        }catch (Exception e) {
            System.out.println("异常："+ ExceptionUtils.getFullStackTrace(e));
        }finally {
            try{
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch (Exception e){
                System.out.println("异常:"+ExceptionUtils.getFullStackTrace(e));
            }
        }
    }

    private static String getInsertSql(Map<String, String> map, String tableName, String dataBase){
        if(MapUtils.isEmpty(map) || StringUtils.isBlank(tableName) || StringUtils.isBlank(dataBase)){
            return null;
        }
        String sql = " insert into " + dataBase + "." + tableName + " ";
        String colStr = "";
        String valueStr = "";
        for(Map.Entry<String, String> entry: map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if(StringUtils.isBlank(colStr)){
                colStr = new StringBuilder().append(colStr).append(key).toString();
                valueStr = new StringBuilder().append(valueStr).append("'").append(value).append("'").toString();
            }else{
                colStr = new StringBuilder().append(colStr).append(",").append(key).toString();
                valueStr = new StringBuilder().append(valueStr).append(",").append("'").append(value).append("'").toString();
            }
        }
        sql = new StringBuilder().append(sql).append("(").append(colStr).append(")").append(" value ( ").append(valueStr).append(")").toString();
        return sql;
    }

    private static String getUpdateSql(Map<String, String> map, String tableName, String dataBase){
        if(MapUtils.isEmpty(map) || StringUtils.isBlank(tableName) || StringUtils.isBlank(dataBase)){
            return null;
        }
        String sql = " update " + dataBase + "." +tableName + " set ";
        String colStr = "";
        String id = map.get("id");
        for(Map.Entry<String, String> entry: map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if(StringUtils.isBlank(colStr)){
                colStr = key;
                sql = new StringBuilder().append(sql).append(key).append("=").append("'").append(value).append("'").toString();
            }else{
                sql = new StringBuilder().append(sql).append(" , ").append(key).append("=").append("'").append(value).append("'").toString();
            }
        }
        sql = new StringBuilder().append(sql).append(" where id=").append(id).toString();
        return sql;
    }

    private static String getDeleteSql(Map<String, String> map, String tableName, String dataBase){
        if(MapUtils.isEmpty(map) || StringUtils.isBlank(tableName) || StringUtils.isBlank(dataBase)){
            return null;
        }
        String id = map.get("id");
        String sql = " delete from  " + dataBase + "." +tableName + " where id= " + id;
        return sql;
    }
}