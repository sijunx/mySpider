package com.spider.demo.thread.test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

//客户端
public class SocketClient {

    final static String ADDRESS = "127.0.0.1";
    final static int PORT = 8765;

    public static void main(String[] args) throws Exception{
        Socket socket = null;
        //创建一个socket
        socket = new Socket(ADDRESS, PORT);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要发送的信息");
        while(scanner.hasNext()){
            String msg = scanner.nextLine();
            //  将输入的信心发送到服务端
            outputStream.write(msg.getBytes());
            //  读取打印服务端发来的消息
            byte[] b = new byte[1024];
            int len = inputStream.read(b);
            if(len == -1){
                break;
            }
            System.out.println("收到服务端的消息:"+new String(b,0,len));

        }
    }
}


