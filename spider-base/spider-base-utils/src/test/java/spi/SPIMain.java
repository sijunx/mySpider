package spi;

import java.util.ServiceLoader;

public class SPIMain {
    public static void main(String[] args) {
        TextHello textHello = new TextHello();

        ImageHello imageHello = new ImageHello();

        ServiceLoader<HelloInterface> loaders = ServiceLoader.load(HelloInterface.class);
        for (HelloInterface in : loaders) {
            in.sayHello();
            System.out.println("xxxxx");
        }
    }
}