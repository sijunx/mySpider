package thread_blocking_queue;

import java.util.List;
import java.util.concurrent.Callable;

public class InThread implements Callable {

    private List<String> list;

    private Boolean flag = true;

    InThread(List<String> list){
        this.list = list;
    }

    @Override
    public Integer call(){
        System.out.println("inThread start");
        while(flag){
            synchronized (list) {
                if(list!=null && list.size()>3){
                    try {
                       wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add("xxx");
            }
        }
        return 59;
    }
}
