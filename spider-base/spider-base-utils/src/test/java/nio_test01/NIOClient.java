package nio_test01;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class NIOClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8000);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        //  输出
        PrintWriter out = new PrintWriter(outputStream, true);
        out.println("getPublicKey你好！");
        out.flush();
        //  输入
        Scanner in = new Scanner(inputStream);
        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            sb.append(line);
        }
        String response = sb.toString();
        System.out.println("response=" + response);
        Thread.sleep(10000);
        socket.shutdownOutput();// 输出结束
    }
}
