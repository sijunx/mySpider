package com.spider.user.service.dao.impl;

import com.spider.base.dbcp.SpiderGetDataSource;
import com.spider.base.mybatis.dao.impl.SpiderBaseDaoImpl;
import com.spider.user.service.dao.api.ISpiderUserInfoDao;
import com.spider.user.service.dao.entity.SpiderUserInfoEntity;
import com.spider.user.service.dao.impl.mapper.SpiderUserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpiderUserInfoDaoImpl extends SpiderBaseDaoImpl<SpiderUserInfoMapper, SpiderUserInfoEntity> implements ISpiderUserInfoDao {

    @Resource(type = SpiderUserInfoMapper.class)
    @Override
    public void setEntityMapper(SpiderUserInfoMapper mapper) {
        entityMapper = mapper;
    }

    @Override
    public List<SpiderUserInfoEntity> findListByPhone(String phone){
        return entityMapper.findListByPhone(phone);
    }
}
