package com.spider.scrawl.provider.service;

import com.spider.scrawl.provider.entity.Student;
import com.spider.search.service.api.MyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class MyDataServiceImpl implements MyDataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public String getListByWordId(){
        //查询记录数
        long count = mongoTemplate.count(Query.query(new Criteria("age").is(18)), Student.class);
        System.out.println(count);

        //查询第一条
        Student student = mongoTemplate.findOne(Query.query(new Criteria("age").is(18)), Student.class);
        System.out.println(student);
        return null;
    }

    public Long start(){

        return 0L;
    }
}
