package com.spider.dist.service.impl;

import com.spider.dist.service.api.ISpiderAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpiderAuditServiceImpl  implements ISpiderAuditService {

    private final static Logger logger = LoggerFactory.getLogger(SpiderAuditServiceImpl.class);

    public boolean duplicateCheckPass(String url){


        return true;
    }

    public boolean duplicateTwoMoreCheckPass(String url){

        return true;
    }


    public boolean blackWordsExistsCheckPass(String word){
        return true;
    }

    public boolean titleCheckPass(String title){

        return true;
    }

    public boolean summaryCheckPass(String summary){

        return true;
    }
}
