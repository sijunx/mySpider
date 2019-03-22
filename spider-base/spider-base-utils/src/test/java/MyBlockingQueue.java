import java.util.LinkedList;
import java.util.Random;

public class MyBlockingQueue {
    private LinkedList<Integer> list = new LinkedList<>();
    final static int MAX_SIZE = 10;

    public void in(Integer i) throws Exception{
        synchronized (list) {
            while (list.size() == 10) {
                try {
                    System.out.println("in 队列已满，阻塞");
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            list.add(i);
            list.notifyAll();
            System.out.println("in 已读进值此时队列大小为" + list.size());
        }
    }

    public void out() {
        synchronized (list) {
            while (list.size() == 0) {
                try {
                    System.out.println("out 队列已空，阻塞");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove();
//            list.notifyAll();
            System.out.println("out 已删除值此时队列大小为" + list.size());
        }
    }

    public static void main(String[] args) throws Exception{
        MyBlockingQueue queue = new MyBlockingQueue();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程"+Thread.currentThread().getName());
                    Random r = new Random();
                    int temp = r.nextInt(100);
                    if (temp > 70) {
                        try {
                            queue.in(2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        queue.out();
                    }
                }
            }).start();
        }
    }
}
