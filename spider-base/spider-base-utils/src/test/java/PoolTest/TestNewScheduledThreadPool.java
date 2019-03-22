package PoolTest;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
 * @author zhuzhisheng
 * @Description
 * @date on 2016/6/1.
 */
public class TestNewScheduledThreadPool {

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

        scheduleAtFixedRate(service,1000);
        scheduleAtFixedRate(service,6000);

        scheduleWithFixedDelay(service,1000);
        scheduleWithFixedDelay(service,6000);


    }

    private static void scheduleAtFixedRate(ScheduledExecutorService service, final int sleepTime){
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long start = new Date().getTime();
                System.out.println("scheduleAtFixedRate 开始执行时间:" +
                        DateFormat.getTimeInstance().format(new Date()));
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = new Date().getTime();
                System.out.println("scheduleAtFixedRate 执行花费时间=" + (end -start)/1000 + "m");
                System.out.println("scheduleAtFixedRate 执行完成时间："
                        + DateFormat.getTimeInstance().format(new Date()));
                System.out.println("======================================");
            }
        },1000,5000, TimeUnit.MILLISECONDS);
    }

    private static void scheduleWithFixedDelay(ScheduledExecutorService service,final int sleepTime){
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                long start = new Date().getTime();
                System.out.println("scheduleWithFixedDelay 开始执行时间:" +
                        DateFormat.getTimeInstance().format(new Date()));
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = new Date().getTime();
                System.out.println("scheduleWithFixedDelay执行花费时间=" + (end -start)/1000 + "m");
                System.out.println("scheduleWithFixedDelay执行完成时间："
                        + DateFormat.getTimeInstance().format(new Date()));
                System.out.println("======================================");
            }
        },1000,5000,TimeUnit.MILLISECONDS);
    }
}

