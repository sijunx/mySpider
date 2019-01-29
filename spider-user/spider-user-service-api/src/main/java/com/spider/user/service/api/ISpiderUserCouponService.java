package com.spider.user.service.api;

import com.spider.user.service.dto.SpiderUserInfoServiceDto;

import java.util.List;

/**
 * 用户信息基础服务
 */
public interface ISpiderUserCouponService {

    List<SpiderUserInfoServiceDto> findListByPhone(String phone);

    List<SpiderUserInfoServiceDto> findListByName(String name);
}
