package lock_test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main01Test implements Callable {

    private Lock lock = new ReentrantLock();

    public Main01Test(Lock lock){
        this.lock = lock;
    }

    @Override
    public String call()throws Exception{
       boolean result = lock.tryLock(1, TimeUnit.SECONDS);
       if(!result){
           System.out.println("未获取到锁");
       }
//        lock.lockInterruptibly();
        try {
            System.out.println("xxxxxx");
            Thread.sleep(5*1000);
        }finally {
            lock.unlock();
        }
        return "1";
    }

    public static void main(String[] arg)throws Exception{

        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactoryBuilder().setNameFormat("SpiderExcutor").build(), new ThreadPoolExecutor.AbortPolicy());
        Lock lock = new ReentrantLock();
        for(int icount=0; icount<5; icount++){
            Main01Test main01Test = new Main01Test(lock);
            FutureTask futureTask = new FutureTask(main01Test);
            executorService.submit(futureTask);
//            Object ob = futureTask.get();
//            System.out.println(ob);
        }
    }
}
