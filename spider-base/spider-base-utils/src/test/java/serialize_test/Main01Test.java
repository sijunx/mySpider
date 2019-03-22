package serialize_test;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

public class Main01Test implements Serializable {

    private String name = "hello张三";
    transient private Integer age = 20;

    private  static String ISO85591 = "UTF-8";//"ISO-8859-1";

    public static void main(String[] arg){
//        Main01Test main01Test = new Main01Test();
        QueueTest queueTest  = new QueueTest();
        queueTest.setName("tom同名");
        String str = serialize(queueTest);

//        Object obj = deserialization(str);

//        QueueTest queueTest01 = (QueueTest)obj;
//        System.out.println("queueTest01:"+queueTest01);
    }

    public static String serialize(Object obj) {
        String serStr = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(obj);
            //serStr = byteArrayOutputStream.toString(ISO85591);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String str01 = Base64.getEncoder().encodeToString(bytes);
            System.out.println("str01:"+str01);
            byte[] byte01 = Base64.getDecoder().decode(str01);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byte01);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object obj01 = objectInputStream.readObject();
            QueueTest queueTest = (QueueTest)obj01;
            System.out.println("queueTest:"+queueTest.toString());
//            serStr = URLEncoder.encode(serStr, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serStr;
    }

    public static Object deserialization(String str) {
        Object newObj = null;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(URLDecoder.decode(str, "UTF-8").getBytes(ISO85591));
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            newObj = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newObj;
    }

    @Override
    public String toString() {
        return "Main01Test{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
