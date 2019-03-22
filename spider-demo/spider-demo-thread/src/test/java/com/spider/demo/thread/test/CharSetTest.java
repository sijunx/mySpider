package com.spider.demo.thread.test;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class CharSetTest {

    public static void main(String[] arg) throws Exception{

        String str = "hi 青蛙";
        byte[] a  = str.getBytes("unicode");
        System.out.println(new String(a,"unicode"));

        String s01 = URLEncoder.encode(new String(a,"unicode"), "utf-8");
        System.out.println("s01:"+s01);

        String s02  = URLDecoder.decode(new String(a,"unicode"),"utf-8");
        System.out.println("s02:"+s02);

    }
}
