package com.spider.dist.service.impl;

import com.spider.base.utils.KeyWordExtractUtil;
import com.spider.dist.service.api.ISpiderKeyWordExtractService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpiderKeyWordExtractServiceImpl implements ISpiderKeyWordExtractService {

    @Override
    public List<String> getKeyWordList(String summary){
        List<String> wordList = KeyWordExtractUtil.stringToArray(summary);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String obj : wordList) {
            if (map.containsKey(obj)) {//判断是否已经有该数值，如有，则将次数加1
                map.put(obj, map.get(obj).intValue() + 1);
            } else {
                map.put(obj, 1);
            }
        }
        //这里将map.entrySet()转换成list
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        List<String> wordRetList = new ArrayList<>();
        int jcount = 0;
        int maxKeyWords = 5;
        for (Map.Entry<String, Integer> entry : list) {
            if (++jcount > maxKeyWords) {
                break;
            }
            wordRetList.add(String.valueOf(entry.getKey()));
        }
        return wordRetList;
    }
}
