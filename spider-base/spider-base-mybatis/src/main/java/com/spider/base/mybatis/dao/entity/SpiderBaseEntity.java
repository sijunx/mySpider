package com.spider.base.mybatis.dao.entity;

import java.io.Serializable;

public abstract class SpiderBaseEntity implements Serializable {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "SpiderBaseEntity{" +
                "id=" + id +
                '}';
    }
}
