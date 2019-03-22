package thread_blocking_queue;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainTest {

    private static List<String> list = new ArrayList<>();

    public static void main(String[] arg){

        InThread inThread = new InThread(list);
        OutThread outThread = new OutThread(list);

//        new ThreadFactoryBuilder().setNameFormat("SpiderExcutor").build()

        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();

        ExecutorService executorService = new ThreadPoolExecutor(3,5,10, TimeUnit.SECONDS, linkedBlockingDeque,
                new ThreadFactoryBuilder().setNameFormat("spiderExecutor").build(),
                new ThreadPoolExecutor.AbortPolicy());

        Future inFuture = executorService.submit(inThread);


        Future outFuture = executorService.submit(outThread);

        Integer result01 = null;
        try {
            result01 = (Integer)inFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("result01:"+result01);

        Integer result02 = null;
        try {
            result02 = (Integer)outFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("result02:"+result02);





    }
}
