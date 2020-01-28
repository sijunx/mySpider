package com.spider.base.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.List;
import java.util.Map;


public class ExcelExportUtil {

    private SXSSFWorkbook sxssfWorkbook;
    private Sheet sheet;

    public Sheet getSheet() {
        return sheet;
    }

    public SXSSFWorkbook getSxssfWorkbook() {
        return sxssfWorkbook;
    }

    public Sheet getSheetByExcel(String templateFilePath){
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(templateFilePath);
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        }
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
           e.printStackTrace();
        }

        return workbook.getSheetAt(0);
    }

    public void  setSxssfWorkbook(String templateFilePath) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(templateFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        sxssfWorkbook=  new SXSSFWorkbook(workbook);
        //压缩临时文件，否则会将空间占的很大，20M会产生1G以上的大文件
        sxssfWorkbook.setCompressTempFiles(true);
        //  获取下标未0的sheet页
        sheet = sxssfWorkbook.getSheetAt(0);
        //只设置默认的宽度，不要再设置全部的宽度值了
        sheet.setDefaultColumnWidth((short) 15);
    }

    /**
     * 创建book and sheet
     * 并设置超过1000行的数据需要导出到磁盘，减少内存使用率
     * 设置压缩临时文件，减少磁盘使用
     */
    private void initWorkbook() {
        // keep 1000 rows in memory, exceeding rows will be flushed to disk
        sxssfWorkbook = new SXSSFWorkbook(1000);
        //压缩临时文件，否则会将空间占的很大，20M会产生1G以上的大文件
        sxssfWorkbook.setCompressTempFiles(true);
        sheet = sxssfWorkbook.createSheet();
        //只设置默认的宽度，不要再设置全部的宽度值了
        sheet.setDefaultColumnWidth((short) 15);
    }

    /**
     * 创建表头
     *
     * @param headTitlesMap KEY：表头英文名称，Value:表头中文名
     * @param columnWidthMap 列宽度：KEY：表头英文名称，Value:表头列宽（字符数）
     * @return
     */
    public SXSSFWorkbook writeHead(Map<String, String> headTitlesMap, Map<String,Integer> columnWidthMap) {
        initWorkbook();
        org.apache.poi.ss.usermodel.Row row = this.getSheet().createRow(0);
        int i = 0;
        Cell cell;
        for (Map.Entry<String, String> entry : headTitlesMap.entrySet()) {
            cell = row.createCell(i);
            RichTextString richTextString = new XSSFRichTextString(entry.getValue());
            cell.setCellValue(richTextString);
            //设置列宽
            if(columnWidthMap.containsKey(entry.getKey())) {
                sheet.setColumnWidth(i,columnWidthMap.get(entry.getKey())*256);
            }
            i++;
        }
        return sxssfWorkbook;
    }

    /**
     * 所有的输出格式都是字符串，如果需要处理输出格式的问题，请将数据处理好再输出
     * @param rowStart
     * @param values
     * @param columnLength
     * @return
     */
    public Integer writeData(Integer rowStart, List<String[]> values, Integer columnLength){
        int rownum = rowStart;
        if(CollectionUtils.isEmpty(values)){
            return null;
        }
        for(int i=0; i<values.size(); i++){
            org.apache.poi.ss.usermodel.Row row = sheet.createRow(rownum);
            String[] rowValue = values.get(i);
            for(int j=0;j<columnLength;j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(rowValue[j]);
            }
            rownum++;
        }
        return rownum;
    }

    /**
     * 由于要将临时文件写到实体的物理文件中去，视导出文件的大小花时间不同
     *
     * @param fileName 文件的绝对路径名称，如：d:/downloads/2th quarter.xlsx
     * @return 文件的绝对路径，下载时使用
     */
    public void saveExcel(String fileName) {
        File file = new File(fileName);
        //  如果父目录不存在，那么递归创建目录
        if(     !file.exists()
                &&  !file.isDirectory()){
            File parentFile = file.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
        }
        try(FileOutputStream out = new FileOutputStream(fileName)){
            sxssfWorkbook.write(out);
            // dispose of temporary files backing this workbook on disk
            sxssfWorkbook.dispose();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 输出到输出流
     * @param outputStream
     * @return
     */
    public Integer toOutputStrean(OutputStream outputStream) {
        try {
            sxssfWorkbook.write(outputStream);
            outputStream.close();
            // dispose of temporary files backing this workbook on disk
            sxssfWorkbook.dispose();
            outputStream.close();
            outputStream.flush();
            return 0;
        }catch (IOException e) {
           e.printStackTrace();
        }
        return 1;
    }
}