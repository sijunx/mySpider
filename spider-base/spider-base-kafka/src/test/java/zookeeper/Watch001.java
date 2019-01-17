package zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created by SK_ZARD on 2018/5/13.
 */
public class Watch001 implements Watcher {


    @Override
    public void process(WatchedEvent event){
        System.out.println("########################################");
        System.out.println("监控watch001");
        System.out.println("########################################");
    }
}
