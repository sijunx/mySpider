import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolTest {


    public static void main(String[] ar){
        ExecutorService executorService =  new ThreadPoolExecutor(5,10, 1000, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(), new ThreadFactoryBuilder().setNameFormat("SpiderExcutor").build(), new ThreadPoolExecutor.AbortPolicy());

        MyCallAble myCallAble = new MyCallAble();
//        ListenableFuture future = executorService.submit(myCallAble);

        ListenableFuture listenableFuture = MoreExecutors.listeningDecorator(executorService).submit(myCallAble);
        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试监听----------------------1");
            }
        }, executorService);

        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer){
                System.out.println("进入回调--------------------------");
            }
            @Override
            public void onFailure(Throwable t){
                t.printStackTrace();
            }
        });



    }
}
