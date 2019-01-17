package zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by SK_ZARD on 2018/5/13.
 */
public class ZookeeperWatchTest {

    public static void main(String[] aurg){
        Watch001 watch001 = new Watch001();
        ZooKeeper zk = null;
        try {
            // 连接zookeeper
            zk = new ZooKeeper("134.175.107.11:2181", 30*1000, watch001);
            Stat stat = zk.exists("/test01", false);
            if (stat == null) {
                // 如果根节点不存在，则创建根节点
                zk.create("/test01", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
