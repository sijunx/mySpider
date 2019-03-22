package file_copy;

import java.io.*;

public class BufferReaderTest {

    public static void main(String[] args) throws Exception {
        File fileSource = new File("d:/temp/a/a.txt");
        File fileTarget = new File("D:/temp/b/b.txt");
        //  如果父目录不存在，那么递归创建目录
//        if( !fileSource.exists()
//        &&  !fileSource.isDirectory()){
//            File parentFile = fileSource.getParentFile();
//            //    目录递归创建
//            parentFile.mkdirs();
//        }
        String[]  strList = fileSource.list();
        if(strList!=null){
            for(int icount=0; icount<strList.length; icount++){
                System.out.println(strList[icount]);
            }
        }
        //指定读写格式为gbk
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileSource), "utf-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileTarget), "utf-8"));
        try {
            String temp;
            while ((temp = br.readLine()) != null) {
                //每次写入一行.
                bw.write(temp);
                //并且进行换行
                bw.newLine();
                bw.flush();
                //读取文本
                System.out.println("文件内容:" + temp);
//                temp = br.readLine();
//                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            br.close();
            bw.close();
        }
    }


}
