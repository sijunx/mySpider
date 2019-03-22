package service_loader;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MyServiceLoader {

    public static void main(String[] arg)    {
        ServiceLoader<MyInterface01> serviceLoader = ServiceLoader.load(MyInterface01.class);

        Iterator<MyInterface01> iterator = serviceLoader.iterator();

        while(iterator.hasNext()){
            MyInterface01 myInterface01 = iterator.next();

            System.out.println("xx   :"+myInterface01);
        }
    }
}
