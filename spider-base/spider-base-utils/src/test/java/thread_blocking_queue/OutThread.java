package thread_blocking_queue;

import java.util.List;
import java.util.concurrent.Callable;

public class OutThread implements Callable {

    private List<String> list;
    private Boolean flag = true;

    OutThread(List<String> list){
        this.list = list;
    }

    public Integer call(){
        System.out.println("outThread start");
        while(flag){
            synchronized (list){
                if(list==null ||list.size()<=0){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(list!=null && !list.isEmpty()){
                    String str = list.get(0);
                    System.out.println("OutThread list.get0:"+str);
                    list.remove(0);
                }

            }
        }
        return 100;
    }
}
