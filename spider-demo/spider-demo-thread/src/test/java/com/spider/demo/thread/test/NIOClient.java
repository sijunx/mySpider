package com.spider.demo.thread.test;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NIOClient {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket s = new Socket("localhost", 8765);
        InputStream inStream = s.getInputStream();
        OutputStream outStream = s.getOutputStream();
        // 输出
        PrintWriter out = new PrintWriter(outStream, true);
        out.println("getPublicKey你好！");
        out.flush();
        s.shutdownOutput();// 输出结束
        // 输入
        Scanner in = new Scanner(inStream);
        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            sb.append(line);
        }
        String response = sb.toString();
        System.out.println("response=" + response);
    }
}
