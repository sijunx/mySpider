package com.spider.user.service.impl;

import com.spider.user.service.api.ISpiderUserInfoService;
import com.spider.user.service.dao.api.ISpiderUserInfoDao;
import com.spider.user.service.dao.entity.SpiderUserInfoEntity;
import com.spider.user.service.dto.SpiderUserInfoServiceDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpiderUserInfoServiceImpl implements ISpiderUserInfoService {

    private final static Logger logger = LoggerFactory.getLogger(SpiderUserInfoServiceImpl.class);

    @Autowired
    private ISpiderUserInfoDao spiderUserInfoDao;

    @Override
    public List<SpiderUserInfoServiceDto> findListByPhone(String phone){
        List<SpiderUserInfoEntity> spiderUserInfoEntityList = spiderUserInfoDao.findListByPhone(phone);
        //  查询结果转换
        List<SpiderUserInfoServiceDto> spiderUserInfoServiceDtos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(spiderUserInfoEntityList)){
            for(SpiderUserInfoEntity spiderUserInfoEntity:spiderUserInfoEntityList){
                SpiderUserInfoServiceDto spiderUserInfoServiceDto = new SpiderUserInfoServiceDto();
                BeanUtils.copyProperties(spiderUserInfoEntity, spiderUserInfoServiceDto);
                spiderUserInfoServiceDtos.add(spiderUserInfoServiceDto);
            }
        }
        return spiderUserInfoServiceDtos;
    }
}
