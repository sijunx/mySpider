package com.spider.demo.thread.test;

public class QuickSortTest {

    public static void main(String[] arg){
        int[] a = {2, 1, 6, 5, 4, 9, 3, 7, 8, 0};
        sort(0, a.length-1, a);
        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
    }

    private static void sort(int minIndex, int maxIndex, int[] a){
        System.out.println("\n入参 minIndex:"+minIndex + " maxIndex:"+maxIndex);
        if(minIndex >= maxIndex) {
            return;
        }
        int key = a[minIndex];
        int i = minIndex;
        int j = maxIndex;
        while(i < j) {
            //  <----
            for (; j > i; j--) {
                if (key > a[j]) {
                    a[i] = a[j];
                    i++;
                    break;
                }
            }
            System.out.println("i="+i);
            //  ---->
            for (; i < j; i++) {
                if (a[i] > key) {
                    a[j] = a[i];
                    j--;
                    break;
                }
            }
            System.out.println("j="+j);
        }
        a[i] = key;
        System.out.println("一轮结束 i:"+i + "j:"+j);
        sort(minIndex, i-1, a);
        sort(i+1, maxIndex, a);
    }
}
