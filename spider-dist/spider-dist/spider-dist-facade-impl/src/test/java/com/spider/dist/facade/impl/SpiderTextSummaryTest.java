package com.spider.dist.facade.impl;

public class SpiderTextSummaryTest {


    public static void main(String[] arg){
        //  获取摘要
        String txt = "徐徐徐徐徐行行行";
        String html = "";

            //  把字符串中的的汉字取出来";
            String reg = "[^\u4e00-\u9fa5]";
            String txt01 = txt.replaceAll(reg, "");
            if (txt01.length() > 2000) {
                txt = txt01.substring(0, 1800);
            } else {
                txt = txt01;
            }

        System.out.println(txt);
    }

}
