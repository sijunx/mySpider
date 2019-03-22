package spi;

public class ImageHello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("Image Hello");
    }
}
