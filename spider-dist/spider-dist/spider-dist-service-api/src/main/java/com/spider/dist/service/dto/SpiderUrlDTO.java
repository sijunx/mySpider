package com.spider.dist.service.dto;

import java.io.Serializable;
import java.util.List;

public class SpiderUrlDTO implements Serializable {
    private String urlId;
    private String url;
    private Integer valid;
    private String summary;
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

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
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

    @Override
    public String toString() {
        return "SpiderUrlDTO{" +
                "urlId='" + urlId + '\'' +
                ", url='" + url + '\'' +
                ", valid=" + valid +
                ", summary='" + summary + '\'' +
                ", keyWords=" + keyWords +
                '}';
    }
}
