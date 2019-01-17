package com.spider.search.service.dto;


import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DocQueue {

    /** 存储器 */
    private List<Document> queue;
    //  线程池最大数
    private int maxPoolSize = 1024;

    public void setQueueSize(int size) {
        queue = new ArrayList<Document>(size);
    }
    public  int getQueueSize(){
        return queue.size();
    }

    public synchronized void add(Document Document) {
        queue.add(Document);
    }

    public synchronized Document poll() {
        if (queue.isEmpty()) {
            return null;
        }
        //控制台打印结果，方便查看
        Document Document = queue.remove(0);
        System.out.println("SpiderQueue,poll,Document=" + Document.toString() + ",remain size=" + queue.size());
        return Document;
    }

    public synchronized Document peek() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.get(0);
    }

    public synchronized boolean isExsit(Document Document) {
        return queue.contains(Document);
    }

    public synchronized int size() {
        return queue.size();
    }

    public void printAll() {
        System.out.println("Enter printAll.");
        for (org.bson.Document Document : queue) {
            System.out.println(Document);
        }
    }
}
