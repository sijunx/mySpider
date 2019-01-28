package com.spider.user.service.dao.api;

import com.spider.base.mybatis.dao.api.ISpiderBaseDao;
import com.spider.user.service.dao.entity.SpiderUserInfoEntity;

import java.util.List;

/**
 * 用户信息基础服务
 */
public interface ISpiderUserInfoDao extends ISpiderBaseDao<SpiderUserInfoEntity> {

	/**
	 * 根据手机号查询用户信息
	 * @param phone
	 * @return
	 */
	List<SpiderUserInfoEntity> findListByPhone(String phone);

}
