package com.spider.base.excel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public  class PdfToExcel {

    public  static  String  getText(File  file)throws  Exception{
        boolean  sort=false;
        int  startPage=1;
        int  endPage=10;
        PDDocument  document=null;
        try{
            try{
                document=PDDocument.load(file);
            }catch(MalformedURLException  e){

            }
            PDFTextStripper  stripper=new  PDFTextStripper();
            stripper.setSortByPosition(sort);
            stripper.setStartPage(startPage);
            stripper.setEndPage(endPage);
            return  stripper.getText(document);
        }catch(Exception  e){
            e.printStackTrace();
            return  "";
        }finally{
            if(document!=null){
                document.close();
            }
        }
    }

    public  static  void  convertToExcel(String fileSavePath, String pdfDirPath, String templatePath) {

        File file02 = new File(pdfDirPath);
        String[] strArray = new String[100];
        if (file02.isDirectory()) {
            strArray = file02.list();
            System.out.println("xx");
        }
        Integer rowStart = 1;
        //  Excel导出工具类
        ExcelExportUtil excelExportUtil = new ExcelExportUtil();
        excelExportUtil.setSxssfWorkbook(templatePath);
        //获取列名称
        List<String> colNameList = getColNameList(excelExportUtil, templatePath);
        for(int icount=0; icount<strArray.length; icount++){
            String txtPath = new StringBuilder(pdfDirPath).append(System.currentTimeMillis()).append(".txt").toString();
            String fileName = strArray[icount];
            if(StringUtils.isBlank(fileName)){
                continue;
            }
            String pdfPath = pdfDirPath+"/"+fileName;
            File file01 = new File(pdfPath);
            List<String> lineDataList = new ArrayList<>();
            try {
                String cont = getText(file01);
                System.out.println(cont);
                FileOutputStream fileOutputStream = null;
                File file = new File(txtPath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(cont.getBytes("utf-8"));
                fileOutputStream.flush();
                fileOutputStream.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(txtPath)),
                        "UTF-8"));
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {//数据以逗号分隔
                    if (StringUtils.isBlank(lineTxt)) {
                        continue;
                    }
                    lineDataList.add(lineTxt);
                }
                //登记证明编号： 0283 6616 0003 4341 8599

            } catch (Exception e) {
                System.out.println("Strip  failed.");
                e.printStackTrace();
            }
            //    Excel 工具类

            List<String[]> listData = getData(lineDataList, colNameList);
            /** Excel写入数据 */
            rowStart = excelExportUtil.writeData(rowStart, listData, colNameList.size());
        }
        /** 保存Excel */
        excelExportUtil.saveExcel(fileSavePath);
    }

    private static List<String> getColNameList(ExcelExportUtil excelExportUtil, String excelTemplatePath){
        Sheet sheet = excelExportUtil.getSheetByExcel(excelTemplatePath);
        Row row00 = sheet.getRow(0);
        List<String> colNameList = new ArrayList<>();
        for (int jcount = 0; jcount < 1000; jcount++) {
            Cell cell = row00.getCell(jcount);
            if (cell == null) {
                break;
            }
            String str = cell.getStringCellValue();
            if (StringUtils.isBlank(str)) {
                break;
            }
            colNameList.add(str);
        }
        return colNameList;
    }

    private static List<String[]> extractEmptyZero(List<String[]> keyData){
        if(CollectionUtils.isEmpty(keyData)){
            return Collections.emptyList();
        }
        List<String[]> keyDataResult = new ArrayList<>();
        for(String[] strArr: keyData){
            String[] strRet = new String[strArr.length];
            keyDataResult.add(strRet);
            int mcount = 0;
            StringBuilder sb = new StringBuilder();
            for(int icount=0; icount<strArr.length; icount++){
                String str01 = strArr[icount];
                if(StringUtils.isBlank(str01)){
                    continue;
                }
                if(NumberUtils.isNumber(str01)){
                    sb = sb.append(str01);
                    if(!nextEleIsNumber(strArr, icount)){
                        strRet[mcount++] = sb.toString();
                        sb = new StringBuilder();
                        continue;
                    }
                }
                if(!NumberUtils.isNumber(str01)){
                    strRet[mcount++]= str01;
                }
            }
        }
        return keyDataResult;
    }

    private static boolean nextEleIsNumber(String[] strArr, int icount){
        if(strArr!=null && (icount+1)< strArr.length){
            if(NumberUtils.isNumber(strArr[icount+1])){
                return true;
            }
        }
        return false;
    }

    private static List<String[]> getData(List<String> keyData, List<String> colNameList){

        List<String[]> listData = new ArrayList<>();
        String[] listArr = new String[colNameList.size()];
        listData.add(listArr);
        for(int icount=0; icount<colNameList.size(); icount++){
            String colName = colNameList.get(icount);
            String nextColName = "";
            if((icount+1)< colNameList.size()){
                nextColName = colNameList.get(icount+1);
            }
            String value = getColValue(keyData, colName, nextColName);
            listArr[icount] = value;
        }
        return listData;
    }

    private static String getColValue(List<String> keyData, String colName, String nextColName){
        if(CollectionUtils.isEmpty(keyData)){
            return "";
        }
        for(int icount=0; icount<keyData.size(); icount++){
            String str = keyData.get(icount);
            if(StringUtils.isBlank(str)){
                continue;
            }
            //如果列结尾是/符号，那么，内容为截取后一列之间内容
            String lineEndStr = colName.substring(colName.length()-1, colName.length());
            if(lineEndStr.equals("/")){
                int firstColIndex = getColIndex(keyData, colName);;
                int lastColIndex = getColIndex(keyData, nextColName);
                StringBuilder sb = new StringBuilder("");
                for(int jcount=firstColIndex; jcount<lastColIndex; jcount++){
                    String str01 = keyData.get(jcount);
                    sb = sb.append(str01);
                }
                return sb.toString().trim();
            }

             if(str.contains(colName)){
                 str = str.substring(str.indexOf(colName)+colName.length(), str.length());
                 if(str.contains("：")){
                     str = str.replace("：", "");
                 }
                 if(!(colName.contains("时间") || colName.contains("日期"))){
                     str = str.replace(":", "");
                     str = str.replace(" ", "");
                 }
                 return str.trim();
             }
        }
        return " ";
    }

    private static int getColIndex(List<String> keyData, String nextColName){
        nextColName = nextColName.replace("/", "");
        nextColName = nextColName.trim();
        if(CollectionUtils.isEmpty(keyData)){
            return 0;
        }
        for(int icount=0; icount<keyData.size(); icount++){
            String str = keyData.get(icount);
            if(StringUtils.isBlank(str)){
                continue;
            }
            if(str.contains(nextColName)){
                return icount;
            }
        }
        return 0;
    }

    public static void main(String[] arg){
//        convertToExcel(String fileSavePath, String pdfDirPath, String templatePath)
        String fileSavePath = "C:\\myPdf\\save_excel\\result.xls";
        String pdfDirPath = "C:\\myPdf\\pdf";
        String templatePath = "C:\\myPdf\\excel_template\\result001.xls";
        PdfToExcel.convertToExcel(fileSavePath, pdfDirPath, templatePath);
    }

}