package com.spider.search.service.dto;

public class SpiderUrlDto {

    private String id;

    private String url;

    private String rootUrl;

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