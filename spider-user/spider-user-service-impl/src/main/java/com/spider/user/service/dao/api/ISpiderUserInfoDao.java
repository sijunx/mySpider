package com.spider.user.service.dao.api;

import com.spider.user.service.dao.entity.SpiderUserInfoEntity;
import com.spider.user.service.dbcp.dao.api.ISpiderBaseDao;

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

	/**
	 * 根据姓名查询用户信息
	 * @param name
	 * @return
	 */
	List<SpiderUserInfoEntity> findListByName(String name);
}
