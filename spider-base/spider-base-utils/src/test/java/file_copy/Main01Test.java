package file_copy;

import java.io.*;

public class Main01Test {

    public static void main(String[] arg)throws Exception{
        //  1 read () 方法，这个方法 从输入流中读取数据的下一个字节。
        //    返回 0 到 255 范围内的int字节值。如果因为已经到达流末尾而没有可用的字节，则返回值 -1 。
        //  2 read (byte[] b,int off,int len)方法，将输入流中最多len个数据字节读入byte数组。
        //    尝试读取len个字节，但读取的字节也可能小于该值。以整数形式返回实际读取的字节数。
        //  3 read (byte[] b) 方法， 从输入流中读取一定数量的字节，并将其存储在缓冲区数组b中。以整数形式返回实际读取的字节数。

        FileInputStream inputStream = new FileInputStream(new File("D:/temp/a/a.txt"));
        FileOutputStream outputStream = new FileOutputStream(new File("D:/temp/b/b.txt"));
        int pos = 0;
        //  一次读一个字节（若输入流为空，则阻塞等待）
        while((pos=inputStream.read()) != -1){
            System.out.println("pos:"+pos);
            outputStream.write(pos);
        }

        //  2 read (byte[] b,int off,int len)方法，将输入流中最多len个数据字节读入byte数组。
        //    尝试读取len个字节，但读取的字节也可能小于该值。以整数形式返回实际读取的字节数。
//        FileInputStream inputStream02 = new FileInputStream(new File("D:/temp/a/a.txt"));
//        FileOutputStream outputStream02 = new FileOutputStream(new File("D:/temp/b/b.txt"));
//        byte[] bytes=new byte[1024];
//        int len = 0;
//        while((len=inputStream02.read(bytes,0,100)) != -1){
//            outputStream02.write(bytes,0,len);
//        }
        //  3 read (byte[] b) 方法， 从输入流中读取一定数量的字节，并将其存储在缓冲区数组b中。以整数形式返回实际读取的字节数。
//        FileInputStream inputStream03 = new FileInputStream(new File("D:/temp/a/a.txt"));
//        FileOutputStream outputStream03 = new FileOutputStream(new File("D:/temp/b/b.txt"));
//        byte[] bytes=new byte[1024];
//        int len = 0;
//        while((len=inputStream03.read(bytes)) != -1){
//            outputStream03.write(bytes,0,len);
//        }

    }
}
