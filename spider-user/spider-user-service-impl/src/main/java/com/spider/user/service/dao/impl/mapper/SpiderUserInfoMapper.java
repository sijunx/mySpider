package com.spider.user.service.dao.impl.mapper;

import com.spider.base.mybatis.dao.mapper.SpiderBaseMapper;
import com.spider.user.service.dao.entity.SpiderUserInfoEntity;

import java.util.List;

public interface SpiderUserInfoMapper extends SpiderBaseMapper<SpiderUserInfoEntity> {

    /**
     * 根据手机号查找用户信息
     * @param phone
     * @return
     */
    List<SpiderUserInfoEntity> findListByPhone(String phone);
}
