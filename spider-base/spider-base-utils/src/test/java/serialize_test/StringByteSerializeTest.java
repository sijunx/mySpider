package serialize_test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

public class StringByteSerializeTest {

    public static void main(String[] arg)throws Exception{
        //  用String的编码、解码
        String str01 = "你好!lucy";
        Charset charset = Charset.forName("UTF8");
        byte[] bytes01 = str01.getBytes(charset);
        String out01 = new String(bytes01, charset);
        System.out.println("out01:"+out01);
        String out11 = Charset.forName("UTF8").newDecoder().decode(ByteBuffer.wrap(bytes01)).toString();
        System.out.println("out11:"+out11);
        //  传输过程借助Base64的编码、解码
        String str02 = "吃过了吗?jack";
        byte[] bytes02 = str02.getBytes(charset);
        String temp02 = Base64.getEncoder().encodeToString(bytes02);
        byte[] bytesTemp02 = Base64.getDecoder().decode(temp02);
        String out02 = new String(bytesTemp02, charset);
        System.out.println("out02:"+out02);

    }
}
