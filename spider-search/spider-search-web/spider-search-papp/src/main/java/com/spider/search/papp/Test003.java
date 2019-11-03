package com.spider.search.papp;

import java.io.File;

public class Test003 {


    public static void main(String[] arg){
        String pdfDirPath = "C:\\test\\a\\1572761981849";
        File file02 = new File(pdfDirPath);
        if (file02.isDirectory()){
            String[] str = file02.list();
            System.out.println("xx");
        }

    }
}
