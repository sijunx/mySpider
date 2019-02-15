package com.spider.demo.thread.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//Server端，循环监听
public class SocketServer {
    final static int PROT = 8765;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PROT);
            System.out.println(" server start .. ");
            while (true) {
                // 进行阻塞
                Socket socket = server.accept();
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
                String msg = "";
                while(true){
                    byte[] b = new byte[1024];
                    //  获取输入流 阻塞等待客户端发送数据
                    int len = inputStream.read(b);
                    if (len==-1){
                        continue;
                    }
                    msg = new String(b, 0, len);
                    System.out.println("收到客户端发送的数据:"+msg);
                    outputStream.write("我是server，消息已经收到了".getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

