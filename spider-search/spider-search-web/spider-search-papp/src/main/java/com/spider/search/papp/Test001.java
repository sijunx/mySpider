//package com.spider.search.papp;
//
//import  java.io.*;
//
//import  java.io.BufferedWriter;
//import  java.io.File;
//import  java.io.FileInputStream;
//import  java.io.FileWriter;
//import  java.io.InputStream;
//
//import  com.alibaba.fastjson.JSON;
//import  org.apache.commons.collections.CollectionUtils;
//import  org.apache.commons.lang.StringUtils;
//import  org.apache.commons.lang.math.NumberUtils;
//import  org.apache.pdfbox.pdfparser.PDFParser;
//import  org.apache.pdfbox.pdmodel.PDDocument;
//import  org.apache.pdfbox.util.PDFTextStripper;
//
//
//import  java.io.File;
//import  java.net.MalformedURLException;
//import  java.util.ArrayList;
//import  java.util.Collections;
//import  java.util.Iterator;
//import  java.util.List;
//
//import  org.apache.pdfbox.pdmodel.PDDocument;
//import  org.apache.pdfbox.util.PDFTextStripper;
//import  org.apache.poi.ss.usermodel.Cell;
//import  org.apache.poi.ss.usermodel.Row;
//import  org.apache.poi.ss.usermodel.Sheet;
//
//public  class  Test001  {
//
//    public  static  String  getText(File  file)throws  Exception{
//        boolean  sort=false;
//        int  startPage=1;
//        int  endPage=10;
//        PDDocument  document=null;
//        try{
//            try{
//                document=PDDocument.load(file);
//            }catch(MalformedURLException  e){
//
//            }
//            PDFTextStripper  stripper=new  PDFTextStripper();
//            stripper.setSortByPosition(sort);
//            stripper.setStartPage(startPage);
//            stripper.setEndPage(endPage);
//            return  stripper.getText(document);
//        }catch(Exception  e){
//            e.printStackTrace();
//            return  "";
//        }finally{
//            if(document!=null){
//                document.close();
//            }
//        }
//    }
//
//    public  static  void  main(String[]  args){
//        File  file01=new  File("C:/myPdf/my01.pdf");
//        List<String[]>  keyData  =  new  ArrayList<>();
//        try{
//            String  cont=getText(file01);
//            System.out.println(cont);
//
//            FileOutputStream  fileOutputStream  =  null;
//            File  file  =  new  File("C:/myPdf/my01.txt");
//            if(!file.exists()){
//                file.createNewFile();
//            }
//            fileOutputStream  =  new  FileOutputStream(file);
//            fileOutputStream.write(cont.getBytes("utf-8"));
//            fileOutputStream.flush();
//            fileOutputStream.close();
//
//            BufferedReader  br  =  new  BufferedReader(new  InputStreamReader(new  FileInputStream(new  File("C:/myPdf/my01.txt")),
//                    "UTF-8"));
//            String  lineTxt  =  null;
//            while  ((lineTxt  =  br.readLine())  !=  null)  {//数据以逗号分隔
//                String[]  names  =  lineTxt.split(" ");
//                System.out.println("names:"+JSON.toJSON(names));
//                keyData.add(names);
//            }
//            //登记证明编号： 0283 6616 0003 4341 8599
//
//        }catch(Exception  e){
//            System.out.println("Strip  failed.");
//            e.printStackTrace();
//        }
//
//
//        //    Excel 工具类
//        Integer rowStart = 2;
//        Integer colSize = 24;
//        //  Excel导出工具类
//        SalesExcelExportUtil excelExportUtil = new SalesExcelExportUtil();
//        String excelTemplatePath = "C:/myPdf/result001.xls";
//        excelExportUtil.setSxssfWorkbook(excelTemplatePath);
//
//        Sheet sheet = excelExportUtil.getSheetByExcel(excelTemplatePath);
//        Row row00 = sheet.getRow(0);
//
//        //
//        List<String> colNameList = new ArrayList<>();
//        for(int icount=0;icount<1000;icount++){
//            Cell cell = row00.getCell(icount);
//            if(cell == null){
//                break;
//            }
//            String str = cell.getStringCellValue();
//            if(StringUtils.isBlank(str)){
//                break;
//            }
//            colNameList.add(str);
//        }
//        List<String[]> keyDataResult = extractEmptyZero(keyData);
//        List<String[]> listData = getData(keyDataResult, colNameList);
//        /** Excel写入数据 */
//        excelExportUtil.writeData(rowStart, listData, colNameList.size());
//        /** 保存Excel */
//        excelExportUtil.saveExcel("C:/myPdf/myResult002.xls");
//    }
//
//    private static List<String[]> extractEmptyZero(List<String[]> keyData){
//        if(CollectionUtils.isEmpty(keyData)){
//            return Collections.emptyList();
//        }
//        List<String[]> keyDataResult = new ArrayList<>();
//        for(String[] strArr: keyData){
//            String[] strRet = new String[strArr.length];
//            keyDataResult.add(strRet);
//            int mcount = 0;
//            StringBuilder sb = new StringBuilder();
//            for(int icount=0; icount<strArr.length; icount++){
//                String str01 = strArr[icount];
//                if(StringUtils.isBlank(str01)){
//                    continue;
//                }
//                if(NumberUtils.isNumber(str01)){
//                    sb = sb.append(str01);
//                    if(!nextEleIsNumber(strArr, icount)){
//                        strRet[mcount++] = sb.toString();
//                        sb = new StringBuilder();
//                        continue;
//                    }
//                }
//                if(!NumberUtils.isNumber(str01)){
//                    strRet[mcount++]= str01;
//                }
//            }
//        }
//        return keyDataResult;
//    }
//
//    private static boolean nextEleIsNumber(String[] strArr, int icount){
//        if(strArr!=null && (icount+1)< strArr.length){
//            if(NumberUtils.isNumber(strArr[icount+1])){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static List<String[]> getData(List<String[]> keyData, List<String> colNameList){
//
//        List<String[]> listData = new ArrayList<>();
//        String[] listArr = new String[colNameList.size()];
//        listData.add(listArr);
//        for(int icount=0; icount<colNameList.size(); icount++){
//            String colName = colNameList.get(icount);
//            String value = getColValue(keyData, colName);
//            listArr[icount] = value;
//        }
//        return listData;
//    }
//
//    private static String getColValue(List<String[]> keyData, String colName){
//        for(String[] strArray : keyData){
//            if(strArray!=null && strArray.length>0){
//                for(int jcount=0; jcount<strArray.length; jcount++){
//                    String str01 = strArray[jcount];
//                    if(StringUtils.isBlank(str01)){
//                        continue;
//                    }
//                    str01 =str01.replace(":","");
//                    str01 = str01.replace("：","");
//                    str01 = str01.trim();
//                    if(StringUtils.isNotBlank(str01) && str01.equals(colName)){
//                        if(jcount+1<strArray.length){
//                            return strArray[jcount+1];
//                        }
//                    }
//                }
//            }
//        }
//        return " ";
//    }
//}