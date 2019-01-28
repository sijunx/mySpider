package com.spider.base.mybatis.dao.mapper;

import com.spider.base.mybatis.dao.entity.SpiderBaseEntity;

import java.util.List;

public interface SpiderBaseMapper<T extends SpiderBaseEntity> {

	T findById(Long id);

	Long insert(T entity);

	Long update(T entity);

	Long deleteById(Long id);

	List<T> findByIds(List<Long> ids);
}
