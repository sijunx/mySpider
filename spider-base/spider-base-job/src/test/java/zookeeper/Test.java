package zookeeper;

public class Test {
    static int n = 500;

    public static void secskill() {
        System.out.println(--n);
    }

    public static void main(String[] args) {

        //192.168.60.103:2182,192.168.60.103:2183,192.168.60.103:2184
        Runnable runnable = new Runnable() {
            public void run() {
                DistributedLock lock = null;
                try {
                    //lock = new DistributedLock("121.40.187.38:2181", "test1");
                    lock = new DistributedLock("192.168.60.103:2184", "test1");
                    lock.lock();
                    secskill();
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}
