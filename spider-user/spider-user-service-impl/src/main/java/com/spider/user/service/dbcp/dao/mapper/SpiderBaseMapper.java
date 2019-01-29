package com.spider.user.service.dbcp.dao.mapper;


import com.spider.user.service.dbcp.dao.entity.SpiderBaseEntity;

import java.util.List;

public interface SpiderBaseMapper<T extends SpiderBaseEntity> {

	T findById(Long id);

	Long insert(T entity);

	Long update(T entity);

	Long deleteById(Long id);

	List<T> findByIds(List<Long> ids);
}
