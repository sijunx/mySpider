package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy implements InvocationHandler {

    private Object object;

    public Object getProxyObject(Object o){
        object=o;
        try{
            return Proxy.newProxyInstance(this.getClass().getClassLoader(), o.getClass().getInterfaces(),this);
        }catch (IllegalArgumentException e){
            throw new RuntimeException(e);
        }

    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke ...");
        Object result= method.invoke(object,args);
        System.out.println("after invoke ...");
        return result;
    }
}