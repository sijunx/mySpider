package com.spider.base.compress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class DecompressFiles {
    public static void main(String[] args) {
        // 将刚创建的图片.zip文件解压缩到D盘的temp目录下
        String fileName = "C:/test/myzip01";
        String zipName = "C:/test/my.zip";
        DecompressFiles.upzipFile(zipName, fileName);
    }
    // 将指定的zip文件解压到指定目录下，其中：zipName：文件名，targetDirName：需解压到的目录
    public static void upzipFile(String zipName, String targetDirName) {
//        if (!targetDirName.endsWith(File.separator)) {
//            targetDirName += File.separator;
//        }
        try {
            // 根据zip文件创建ZipFile对象，此类的作用是从zip文件读取条目
            ZipFile zipFile = new ZipFile(zipName);
            ZipEntry zn = null;
            String entryName = null;
            String targetFileName = null;
            byte[] buffer = new byte[4096];
            int bytes_read;
            Enumeration entrys = zipFile.entries();			// 获取ZIP文件里所有的文件条目的名字
            while (entrys.hasMoreElements()) {				// 循环遍历所有的文件条目的名字
                zn = (ZipEntry) entrys.nextElement();
                entryName = zn.getName();				// 获得每一条文件的名字
                targetFileName = targetDirName + entryName;
//                targetFileName = targetDirName;
                if (zn.isDirectory()) {
                    new File(targetFileName).mkdirs();		// 如果zn是一个目录，则创建目录
                    continue;
                } else {
                    new File(targetFileName).getParentFile().mkdirs();// 如果zn是文件，则创建父目录
                }
                File targetFile = new File(targetFileName);	// 否则创建文件
                System.out.println("正在创建文件：" + targetFile.getAbsolutePath());
                FileOutputStream os = new FileOutputStream(targetFile);// 打开文件输出流
                InputStream is = zipFile.getInputStream(zn);	// 从ZipFile对象中打开entry的输入流
                while ((bytes_read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytes_read);
                }
                os.close();								// 关闭流
                is.close();
            }
            System.out.println("解压缩"+zipName+"成功！");
        } catch (Exception err) {
            System.err.println("解压缩"+zipName+"失败: " + err);
        }
    }
}

