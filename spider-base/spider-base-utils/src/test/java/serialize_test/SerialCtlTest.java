package serialize_test;

import java.io.*;

public class SerialCtlTest implements Serializable{
    String a;
    transient String b;
    public SerialCtlTest(String aa, String bb){
        a="Not Transient:"+aa;
        b="Transient:"+bb;
    }
    public String toString(){
        return a+"\n"+b;
    }

    public static void main(String[] args){
        SerialCtlTest serialCtlTest = new SerialCtlTest("Test1","Test2");
        System.out.println("Before:\n"+serialCtlTest);
        try{
            //  序列化
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            ObjectOutputStream out1 = new ObjectOutputStream(buf);
            out1.writeObject(serialCtlTest);
            byte [] inByte = buf.toByteArray();
            //  反序列化
            ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(inByte));
            SerialCtlTest sc2 = (SerialCtlTest)in1.readObject();
            System.out.println("After:\n"+sc2);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}