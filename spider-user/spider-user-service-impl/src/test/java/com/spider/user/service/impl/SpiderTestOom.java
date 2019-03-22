//package com.spider.user.service.impl;
//
//import com.google.common.util.concurrent.ThreadFactoryBuilder;
//
//import java.util.concurrent.*;
//
//public class SpiderTestOom {
//
//    public static void main(String[] arg) throws Exception{
////        ExecutorService excutorService = new ThreadPoolExecutor(2,4, 3, TimeUnit.SECONDS, );
////        excutorService.submit();
//
//
//        ExecutorService executorService =  new ThreadPoolExecutor(5,10, 1000, TimeUnit.SECONDS,
//                new LinkedBlockingDeque<Runnable>(), new ThreadFactoryBuilder().setNameFormat("SpiderExcutor").build(), new ThreadPoolExecutor.AbortPolicy());
//
//
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> getAmount());
//
//        String xx = future.get();
//
//        System.out.println("xx:" + xx);
//    }
//
//    private static String getAmount(){
//
//        return "1111";
//    }
//}
//
//
//
//
