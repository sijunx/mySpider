//package com.spider.scrawl.provider;
//
//import com.spider.search.service.util.SpiderKeyWordExtractUtil;
//import org.apache.commons.lang.exception.ExceptionUtils;
//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.exception.InvalidConfigurationException;
//import org.mybatis.generator.exception.XMLParserException;
//import org.mybatis.generator.internal.DefaultShellCallback;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * MybatisGenerator
// *
// * @author hanyang
// */
//public class MybatisGenerator {
//
//    private final static Logger LOGGER = LoggerFactory.getLogger(SpiderKeyWordExtractUtil.class);
//
//    public static void main(String[] args) {
//        try {
//            List<String> warnings = new ArrayList<>();
//            boolean overwrite = true;
//            //src的一级目录下RestaurantIdCustomerIdLog.java
//            String genCfg = "/generator/generator.xml";
//            File configFile = new File(MybatisGenerator.class.getResource(genCfg).getFile());
//            ConfigurationParser cp = new ConfigurationParser(warnings);
//            org.mybatis.generator.config.Configuration config = null;
//            try {
//                config = cp.parseConfiguration(configFile);
//            } catch (IOException e) {
//                LOGGER.error("MybatisGenerator has IOException", e);
//                System.out.println("e:" + ExceptionUtils.getFullStackTrace(e));
//            } catch (XMLParserException e) {
//                LOGGER.error("MybatisGenerator has XMLParserException", e);
//
//                System.out.println("e:" + ExceptionUtils.getFullStackTrace(e));
//            }
//            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//            MyBatisGenerator myBatisGenerator = null;
//            try {
//                myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//            } catch (InvalidConfigurationException e) {
//                LOGGER.error("MybatisGenerator has InvalidConfigurationException", e);
//
//                System.out.println("e:" + ExceptionUtils.getFullStackTrace(e));
//            }
//            try {
//                if (myBatisGenerator != null) {
//                    myBatisGenerator.generate(null);
//                }
//            } catch (SQLException e) {
//                LOGGER.error("MybatisGenerator has SQLException", e);
//
//                System.out.println("e:" + ExceptionUtils.getFullStackTrace(e));
//            } catch (IOException e) {
//                LOGGER.error("MybatisGenerator has IOException 2", e);
//
//                System.out.println("e:" + ExceptionUtils.getFullStackTrace(e));
//            } catch (InterruptedException e) {
//                LOGGER.error("MybatisGenerator has InterruptedException", e);
//
//                System.out.println("e:" + ExceptionUtils.getFullStackTrace(e));
//                Thread.currentThread().interrupt();
//            }
//
//            System.out.println("generator success");
//
//        }catch(Exception e){
//            System.out.println("e:"+ExceptionUtils.getFullStackTrace(e));
//        }
//    }
//}