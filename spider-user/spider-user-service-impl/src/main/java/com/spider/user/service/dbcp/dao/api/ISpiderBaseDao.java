package com.spider.user.service.dbcp.dao.api;


import com.spider.user.service.dbcp.dao.entity.SpiderBaseEntity;

import java.util.List;

public interface ISpiderBaseDao<T extends SpiderBaseEntity>{

	T findById(Long id);
	
	Long insert(T t);
	
	boolean update(T t);
	
	boolean deleteById(Long id);

	List<T> findByIds(List<Long> ids);
}
