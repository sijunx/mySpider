package com.spider.demo.thread.test;

public class BubboSortTest {

    public static void main(String[] arg){
        int[] a = {2, 1, 6, 5, 4, 9, 3, 7, 8, 0};
        //  每次比较相邻的两个元素，并交换位置，一趟比较下来找到最大值
        for(int i=0; i<=a.length-1; i++){
            for(int j=0; (j+1)<=a.length-1-i;j++){
                if(a[j] > a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }

        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
    }
}

