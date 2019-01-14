package com.spider.search.service.impl.mongo;

import com.spider.search.service.api.mongo.ImageService;
import com.spider.search.service.api.mongo.ReverseIndexService;
import com.spider.search.service.api.mongo.SoService;
import com.spider.search.service.util.StringHandlerUtil;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class SoServiceImpl extends AbstractSpiderBaseService implements SoService {

    private final static Logger logger = LoggerFactory.getLogger(SoServiceImpl.class);

    @Autowired
    private ReverseIndexService reverseIndexService;
    @Autowired
    private ImageService imageService;

    @Override
    public List<Document> soBySentence(String sentence){
        reverseIndexService.setDatabase(this.mongoDatabase);
        imageService.setDatabase(this.mongoDatabase);

        //分词
        List<String> list01 = new ArrayList<String>();
        StringHandlerUtil sh = new StringHandlerUtil();
        String str = sentence;
        list01 = sh.stringToArray(str);

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String obj : list01) {
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

        //取前三个分词，根据分词到倒序查询，拼接成集合
        ArrayList<Document> list03 = new ArrayList<Document>();
        int jcount = 0;
        int maxKeyWords = 3;
        for (Map.Entry<String, Integer> mapping : list) {
            if (++jcount > maxKeyWords) {
                break;
            }
            String keyWord = mapping.getKey();
            Document reverseIndex = new Document();

            List<Document> list05 = reverseIndexService.findListByWord(keyWord);
            if(null == list05 || list05.size() <=0){
                continue;
            }else{
                for(Document reverseIndex01: list05){
                    list03.add(reverseIndex01);
                }
            }
        }

        //对集合按照热度降序拍
        Map<Document, Double> map01 = new HashMap<Document, Double>();
        for (Document obj : list03) {
            map01.put(obj, Double.parseDouble(String.valueOf(obj.get("hots"))));
        }

        //这里将map.entrySet()转换成list
        List<Map.Entry<Document, Double>> list04 = new ArrayList<Map.Entry<Document, Double>>(map01.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list04, new Comparator<Map.Entry<Document, Double>>() {
            //降序排序
            public int compare(Map.Entry<Document, Double> o1,
                               Map.Entry<Document, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        ArrayList<Document> listRet = new ArrayList<Document>();

        //每页显示10条
        int mcount = 0;
        for (Map.Entry<Document, Double> mapping : list04) {
            if (++mcount > 10) {
                break;
            }
            Document reverseIndex = mapping.getKey();
            String urlId = reverseIndex.getString("urlId");
            Document doc01 = new Document();
            doc01.put("urlId", urlId);
            List<Document> docImageList = imageService.findList(doc01);
            String imageId=null;
            if(!CollectionUtils.isEmpty(docImageList)){
                for(Document docImage: docImageList){
                    String path01 = docImage.getString("imagePath");
                    if(path01.contains("logo")){
                        continue;
                    }else{
                        imageId = docImage.getString("imageId");
                        break;
                    }
                }
            }
            reverseIndex.put("imageId", imageId);
            listRet.add(reverseIndex);
        }
        return listRet;
    }
}
