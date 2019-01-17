package com.spider.search.service.dto;

import java.io.Serializable;

public class InputDataServiceDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private String url;

    private String txt;

    private String summary;

    private String title;

    private String urlId;

    private Double hots;

    private String imageId;

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

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public Double getHots() {
        return hots;
    }

    public void setHots(Double hots) {
        this.hots = hots;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}