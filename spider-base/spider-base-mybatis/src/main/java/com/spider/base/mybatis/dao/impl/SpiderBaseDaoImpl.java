package com.spider.base.mybatis.dao.impl;

import com.spider.base.mybatis.dao.api.ISpiderBaseDao;
import com.spider.base.mybatis.dao.entity.SpiderBaseEntity;
import com.spider.base.mybatis.dao.mapper.SpiderBaseMapper;

import java.util.List;

/**
 * 基础dao模板
 * @param <M>
 * @param <T>
 */
public abstract class SpiderBaseDaoImpl<M extends SpiderBaseMapper<T>, T extends SpiderBaseEntity> implements ISpiderBaseDao<T> {

    protected M entityMapper;

    public abstract void setEntityMapper(M mapper);

    @Override
    public T findById(Long id) {
        return entityMapper.findById(id);
    }

    @Override
    public List<T> findByIds(List<Long> ids) {
        return entityMapper.findByIds(ids);
    }

    @Override
    public Long insert(T t) {
        entityMapper.insert(t);
        return t.getId();
    }

    @Override
    public boolean update(T t) {
        return entityMapper.update(t) == 1;
    }

    @Override
    public boolean deleteById(Long id) {
        return entityMapper.deleteById(id) == 1;
    }
}
