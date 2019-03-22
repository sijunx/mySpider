import com.google.common.util.concurrent.ListenableFutureTask;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class main01 implements Serializable {
    public static void main(String[] args) throws ClassNotFoundException, IOException, Exception {   //   序列话读入和写入Object可能会有这两个异常

        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                System.out.println("DisplayName:" + ni.getDisplayName());
                System.out.println("Name:" + ni.getName());
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    System.out.println("IP:"
                            + ips.nextElement().getHostAddress());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        List<String> strList = new ArrayList<>();
        strList.add("abc");
        strList.add("efg");
        strList.add("hij");

        String xx = strList.remove(0);
        System.out.println("xx:"+xx);

        String yy = strList.get(0);
        System.out.println("yy:"+yy);




        Vector vector = new Vector();

        Set hashSet = new HashSet<>();

        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        lock.tryLock(10, TimeUnit.SECONDS);
        MyCallAble myCallAble = new MyCallAble();




        List<QueueTest> queueTests = new ArrayList<>();
//        QueueTest queueTest = new QueueTest();
//        queueTest.setName("xxx");
//        queueTests.add(queueTest);
//
//        QueueTest queueTest02 = new QueueTest();
//        queueTest02.setName("yyy");
//        queueTests.add(queueTest02);


        for(QueueTest queueTest1:queueTests){
            System.out.println("queueTest1"+queueTest1);
        }



        //   将你要序列化的object，保留到一个文件中
        Random rand = new Random();
        SerialzeTest02 d = new SerialzeTest02(rand.nextInt(10));   //构建你需要序列话的Object
        System.out.println("d = " + d);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("c://temp/worm.out"));   //   准备写入的文件
        out.writeObject(d);
        out.flush();
        out.close();   //   执行到这里你可以看见worm.out这个文件，
        //   以下的代码读出你刚刚写入Object
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("c://temp/worm.out"));   // 读你刚刚写入的文件
        SerialzeTest02 d2 = (SerialzeTest02)in.readObject();   // 重新构建你刚刚写入的Object
        System.out.println("d2=" + d2);





    }
}

