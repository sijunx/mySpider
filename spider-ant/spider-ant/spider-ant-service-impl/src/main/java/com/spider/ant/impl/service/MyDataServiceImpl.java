package com.spider.ant.impl.service;

import com.spider.ant.service.api.MyDataService;
import org.springframework.stereotype.Service;

@Service
public class MyDataServiceImpl implements MyDataService {

    public String getListByWordId(){

        return null;
    }

    public Long start(){
        System.out.println("test               start   spider");
        return 0L;
    }
}
