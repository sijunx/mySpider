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
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

public class MyParseJDBCUtil {

    private MyParseJDBCUtil(){}

//    public static String url_encrypt="jdbc:mysql://106.13.38.23:3306/spider_dev?useUnicode=true&characterEncoding=UTF-8";
    public static String url_encrypt = null;
    public static String user = null;
//    public static String user = "root";
    public static String password = null;
//    public static String password = "moongrubby";

    public static void main(String[] args) throws Exception{
//        String updateMsg = "{ \t\"data\": [{ \t\t\"id\": \"267\", \t\t\"item_code\": \"color\", \t\t\"item_desc\": \"vvvvvvvvvvvvvvvvvvvvvvvv\", \t\t\"item_cname\": \"颜色\", \t\t\"item_ename\": \"color\", \t\t\"item_type\": \"0\", \t\t\"item_len\": \"20\", \t\t\"item_remark\": \"\", \t\t\"create_time\": \"2020-02-08 12:01:11\", \t\t\"create_user_id\": \"0\", \t\t\"update_time\": \"2020-02-25 00:15:27\", \t\t\"update_user_id\": \"0\", \t\t\"delete_flag\": \"0\" \t}], \t\"database\": \"spider_dev\",  \t\"table\": \"item_info\", \t\"ts\": 1582601972199, \t\"type\": \"UPDATE\" }";
//        MyParseJDBCUtil.parseBinlog(updateMsg);
//        String delMsg = "{ \t\"data\": [{ \t\t\"id\": \"267\", \t\t\"item_code\": \"color\", \t\t\"item_desc\": \"vvvvvvvvvvvvvvvvvvvvvvvv\", \t\t\"item_cname\": \"颜色\", \t\t\"item_ename\": \"color\", \t\t\"item_type\": \"0\", \t\t\"item_len\": \"20\", \t\t\"item_remark\": \"\", \t\t\"create_time\": \"2020-02-08 12:01:11\", \t\t\"create_user_id\": \"0\", \t\t\"update_time\": \"2020-02-25 00:15:27\", \t\t\"update_user_id\": \"0\", \t\t\"delete_flag\": \"0\" \t}], \t\"database\": \"spider_dev\",  \t\"table\": \"item_info\", \t\"ts\": 1582601972199, \t\"type\": \"DELETE\" }";
//        MyParseJDBCUtil.parseBinlog(delMsg);
//        String insertMsg = "{ \t\"data\": [{ \t\t\"item_code\": \"color\", \t\t\"item_desc\": \"vvvvvvvvvvvvvvvvvvvvvvvv\", \t\t\"item_cname\": \"颜色\", \t\t\"item_ename\": \"color\", \t\t\"item_type\": \"0\", \t\t\"item_len\": \"20\", \t\t\"item_remark\": \"\", \t\t\"create_time\": \"2020-02-08 12:01:11\", \t\t\"create_user_id\": \"0\", \t\t\"update_time\": \"2020-02-25 00:15:27\", \t\t\"update_user_id\": \"0\", \t\t\"delete_flag\": \"0\" \t}], \t\"database\": \"spider_dev\",  \t\"table\": \"item_info\", \t\"ts\": 1582601972199, \t\"type\": \"INSERT\" }";
//        MyParseJDBCUtil.parseBinlog(insertMsg);

        String msg = "{ \t\"data\": [{ \t\t\"id\": \"267\", \t\t\"item_code\": \"color\", \t\t\"item_desc\": \"vvvvvvvvvvvvvvvvvvvvvvvv\", \t\t\"item_cname\": \"颜色\", \t\t\"item_ename\": \"color\", \t\t\"item_type\": \"0\", \t\t\"item_len\": \"20\", \t\t\"item_remark\": \"\", \t\t\"create_time\": \"2020-02-08 12:01:11\", \t\t\"create_user_id\": \"0\", \t\t\"update_time\": \"2020-02-25 00:15:27\", \t\t\"update_user_id\": \"0\", \t\t\"delete_flag\": \"0\", \t    \"ds_latest_stamp\":\"1970-02-25 14:24:00.001\", \t    \"ds_check_time\":\"2020-02-25 14:24:00.001\" \t}], \t\"database\": \"spider_dev\",  \t\"table\": \"item_info\", \t\"ts\": 1582601972199, \t\"type\": \"INSERT\" }";
        boolean flag = MyParseJDBCUtil.checkData(msg);
        System.out.println("flag:"+flag);
    }

    public static void parseBinlog(String msg) {
        JSONObject jsonObject = JSON.parseObject(msg);
        String tableName = jsonObject.getString("table");
        String dataBase = jsonObject.getString("database");
        String operateType = jsonObject.getString("type");

        List<Map<String, String>> mapList = getDataFromJson(jsonObject);

        if (!CollectionUtils.isEmpty(mapList)) {
            for (Map<String, String> map : mapList) {
                excuteSql(map, tableName, dataBase, operateType);
            }
        }
    }

    public static List<Map<String, String>> getDataFromJson(JSONObject jsonObject){
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

    private static void excuteSql(Map<String, String> map, String tableName, String dataBase,String operateType){
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
        Date date = new Date();
        for(Map.Entry<String, String> entry: map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if(StringUtils.isNotBlank(key) && key.equals("ds_latest_stamp")){
                continue;
            }
            if(StringUtils.isBlank(colStr)){
                colStr = new StringBuilder().append(colStr).append("ds_latest_stamp,").append(key).toString();
                valueStr = new StringBuilder().append(valueStr).append("'").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")).append("',").append("'").append(value).append("'").toString();
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
                sql = new StringBuilder().append(sql).append(" ds_latest_stamp = CURRENT_TIMESTAMP(3) ,").append(key).append("=").append("'").append(value).append("'").toString();
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

    public static boolean checkData(String message){
        try{
        //检查数据是否为本地操作记录，否则，无需重复同步
        JSONObject jsonObject = JSONObject.parseObject(message);
        String tableName = jsonObject.getString("table");
        String dataBase = jsonObject.getString("database");
        String operateType = jsonObject.getString("type");

        List<Map<String, String>> maps = MyParseJDBCUtil.getDataFromJson(jsonObject);
        if(CollectionUtils.isEmpty(maps)){
            return true;
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(operateType) && operateType.equals("INSERT")) {
            Map<String, String> map = maps.get(0);
            String dsCheckTimeStr = map.get("ds_check_time");
            String dsLatestTime = map.get("ds_latest_stamp");
            Date checkDate =  DateUtils.parseDate(dsCheckTimeStr, "yyyy-MM-dd HH:mm:ss.SSS");
            Date lastDate = DateUtils.parseDate(dsLatestTime, "yyyy-MM-dd HH:mm:ss.SSS");

            //新增默认lastaDate为1970年
            if(checkDate!=null && lastDate!=null && DateUtils.addYears(lastDate, 40).after(checkDate)){
                return false;
            }
        }
        if(org.apache.commons.lang3.StringUtils.isNotBlank(operateType) && operateType.equals("UPDATE")){
            Map<String, String> map = maps.get(0);
            String dsCheckTimeStr = map.get("ds_check_time");
            String dsLatestTime = map.get("ds_latest_stamp");
            //在做时间更新的时候，这两个值会赋值为相等
            if(     org.apache.commons.lang3.StringUtils.isNotBlank(dsCheckTimeStr)
                    &&  org.apache.commons.lang3.StringUtils.isNotBlank(dsLatestTime)
                    &&  dsCheckTimeStr.equals(dsLatestTime)){
                return false;
            }
        }
        }catch (Exception e){
            System.out.println("异常了e:"+ExceptionUtils.getFullStackTrace(e));
        }
        return true;
    }
}