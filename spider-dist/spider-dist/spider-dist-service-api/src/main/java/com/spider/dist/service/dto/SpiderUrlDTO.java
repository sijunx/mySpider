package com.spider.dist.service.dto;

import java.io.Serializable;
import java.util.List;

public class SpiderUrlDTO implements Serializable {
    //  id
    private String urlId;
    //  url
    private String url;
    //  状态 SpiderUrlStatusEnum
    private Integer status;
    //  标题
    private String title;
    //  摘要
    private String summary;
    //  关键词
    private List<String> keyWords;

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SpiderUrlDTO{" +
                "urlId='" + urlId + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", keyWords=" + keyWords +
                '}';
    }
}
