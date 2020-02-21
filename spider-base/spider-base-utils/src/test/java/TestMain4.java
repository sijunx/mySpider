import com.spider.base.rsa.TokenUtil;
import org.apache.commons.lang.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Date;

public class TestMain4 extends Thread {
    public void run() {            //永真循环线程
        for(int i=0;;i++){
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {   }
            System.out.println(i);
        }
    }

    public static void main(String [] args){

        boolean ret = TokenUtil.checkToken(740172);

        System.out.println("ret:"+ret);


        TestMain4 test = new TestMain4();
        test.setDaemon(false);    //调试时可以设置为false，那么这个程序是个死循环，没有退出条件。设置为true，即可主线程结束，test线程也结束。
        test.start();
        System.out.println("isDaemon = " + test.isDaemon());
//        try {
//            System.in.read();   // 接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束
//        } catch (IOException ex) {}
    }
}
