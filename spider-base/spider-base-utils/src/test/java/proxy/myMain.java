package proxy;

public class myMain {

    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        MyProxy myProxy = new MyProxy();
        HelloWorldInteface helloWorldInteface = (HelloWorldInteface)myProxy.getProxyObject(new HelloWorldImpl());
        helloWorldInteface.sayHello();
    }
}
