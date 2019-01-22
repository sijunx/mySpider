package com.spider.search.service.dto;

import java.io.Serializable;
import java.util.Date;

public class SpiderUrlSimilarServiceDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private String urlIdSeed;

    private String urlIdOther;

    private Double similar;

    private String delteFlag;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlIdSeed() {
        return urlIdSeed;
    }

    public void setUrlIdSeed(String urlIdSeed) {
        this.urlIdSeed = urlIdSeed;
    }

    public String getUrlIdOther() {
        return urlIdOther;
    }

    public void setUrlIdOther(String urlIdOther) {
        this.urlIdOther = urlIdOther;
    }

    public Double getSimilar() {
        return similar;
    }

    public void setSimilar(Double similar) {
        this.similar = similar;
    }

    public String getDelteFlag() {
        return delteFlag;
    }

    public void setDelteFlag(String delteFlag) {
        this.delteFlag = delteFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
