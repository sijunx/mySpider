package com.spider.user.service.dao.impl.mapper;

import com.spider.user.service.dao.entity.SpiderUserInfoEntity;
import com.spider.user.service.dbcp.dao.mapper.SpiderBaseMapper;

import java.util.List;

public interface SpiderUserInfoMapper extends SpiderBaseMapper<SpiderUserInfoEntity> {

    /**
     * 根据手机号查找用户信息
     * @param phone
     * @return
     */
    List<SpiderUserInfoEntity> findListByPhone(String phone);

    /**
     * 根据姓名查找用户信息
     * @param name
     * @return
     */
    List<SpiderUserInfoEntity> findListByName(String name);
}
