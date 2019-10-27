package com.spider.scrawl.provider.service;

import com.spider.scrawl.provider.entity.SpiderUrlEntity;
import com.spider.scrawl.provider.entity.Student;
import com.spider.search.service.api.SpiderUrlService;
import com.spider.search.service.dto.SpiderUrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("spiderUrlService")
public class SpiderUrlServiceImpl implements SpiderUrlService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertList(){

        List<SpiderUrlEntity> spiderUrlList = new ArrayList<>();
        SpiderUrlEntity spiderUrlEntity = new SpiderUrlEntity();

        spiderUrlList.add(spiderUrlEntity);
        mongoTemplate.insert(spiderUrlList, SpiderUrlEntity.class);


    }

    @Override
    public List<SpiderUrlDto> getUseAbleSpiderUrl(){


        return null;
    }
}
