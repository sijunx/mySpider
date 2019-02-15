package com.spider.demo.thread.test;

public class SelectSortTest {

    public static void main(String[] arg){
        int[] a = {2, 1, 6, 5, 4, 9, 3, 7, 8, 0};
        //  每一轮比较找到最小值放到最前面，位置向后移动一个，再下一轮找最小值
        for(int i=0; i<a.length; i++){
            for(int j=i+1; j<a.length; j++){
                if(a[i] > a[j]){
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
    }
}
