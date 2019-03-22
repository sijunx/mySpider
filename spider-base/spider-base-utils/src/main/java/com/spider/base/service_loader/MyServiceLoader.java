package com.spider.base.service_loader;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MyServiceLoader {

    public static void main(String[] arg)    {
        ServiceLoader<MyInterface01> serviceLoader = ServiceLoader.load(MyInterface01.class);

        Class<?> claz = null;
        try {
            claz = Thread.currentThread().getContextClassLoader().loadClass("MyInterface01");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Iterator<MyInterface01> iterator = serviceLoader.iterator();

        while(iterator.hasNext()){
            MyInterface01 myInterface01 = iterator.next();
            myInterface01.test();
            System.out.println("xx   :"+myInterface01);
        }
    }
}
