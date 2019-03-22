package com.spider.demo.thread.test;

public class SwitchTest {

    public static void main(String[] arg){

       FirstDemo firstDemo = new FirstDemo();
       SecondDemo secondDemo = new SecondDemo();
       ThirdDemo thirdDemo = new ThirdDemo();

       if(thirdDemo instanceof Object){
           System.out.println("111111");
       }
    }
}
