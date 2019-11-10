package com.spider.base.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtil {

    public static void main(String[] args) {
        //产生一个map并添加一些参数
        Map<String,Integer> map = new HashMap<>();
        map.put("ddd", 1);
        map.put("aaa", 2);
        map.put("bbb", 3);
        map.put("ccc", 4);
        //sort
        sort(map);

    }

    public static void sort(Map<String,Integer> map){
        List<Entry<String,Integer>> list = new ArrayList<>(map.entrySet()); //将map的entryset放入list集合
        //对list进行排序，并通过Comparator传入自定义的排序规则
        Collections.sort(list,new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue(); //重写排序规则，小于0表示升序，大于0表示降序
            }
        });
        //用迭代器对list中的键值对元素进行遍历
        Iterator<Entry<String, Integer>> iter = list.iterator();
        while(iter.hasNext()){
            Entry<String, Integer> item = iter.next();
            String key = item.getKey();
            int value = item.getValue();
            System.out.println("键"+key+"值"+value);
        }
    }
}

