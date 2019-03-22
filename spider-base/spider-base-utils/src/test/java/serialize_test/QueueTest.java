package serialize_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest implements Serializable {

    private final static Logger logger = LoggerFactory.getLogger(QueueTest.class);

    private String name;

    public static Logger getLogger() {
        return logger;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String [] args) {

        Deque<String> deque = new LinkedList<String>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque);//[c, b, a]
        logger.info("deque:{}", deque);
        //获取栈首元素后，元素不会出栈
        String str = deque.peek();
        System.out.println(str);//c
        System.out.println(deque);//[c, b, a]
        while(deque.size() > 0) {
            //获取栈首元素后，元素将会出栈
            System.out.println(deque.pop());//c b a
        }
        System.out.println(deque);//[]
    }
    private void queue01(){
        Queue<String> queue = new LinkedList<String>();


        //追加元素
        queue.offer("one");
        queue.offer("two");
        queue.offer("three");
        queue.offer("four");
        System.out.println(queue);//[one, two, three, four]
        //从队首取出元素并删除
        String poll = queue.poll();
        System.out.println(poll);//one
        System.out.println(queue);//[two, three, four]
        //从队首取出元素但是不删除
        String peek = queue.peek();
        System.out.println(peek);//two
        System.out.println(queue);//[two, three, four]
        //遍历队列，这里要注意，每次取完元素后都会删除，整个
        //队列会变短，所以只需要判断队列的大小即可
        while(queue.size() > 0) {
            System.out.println(queue.poll());//two、  three、  four
        }
    }

    @Override
    public String toString() {
        return "QueueTest{" +
                "name='" + name + '\'' +
                '}';
    }
}
