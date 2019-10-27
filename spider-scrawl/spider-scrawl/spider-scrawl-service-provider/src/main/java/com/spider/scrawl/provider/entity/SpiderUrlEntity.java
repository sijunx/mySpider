package com.spider.scrawl.provider.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="SpiderUrl")
public class SpiderUrlEntity {
    @Id
    private String id;
    @Field
    private String url;
    @Field
    private String rootUrl;
    @Field
    private Long deep;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public Long getDeep() {
        return deep;
    }

    public void setDeep(Long deep) {
        this.deep = deep;
    }
}