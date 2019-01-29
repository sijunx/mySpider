package com.spider.user.service.dbcp.dao.entity;

import java.io.Serializable;

public abstract class SpiderBaseEntity implements Serializable {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SpiderBaseEntity{" +
                "id=" + id +
                '}';
    }
}
