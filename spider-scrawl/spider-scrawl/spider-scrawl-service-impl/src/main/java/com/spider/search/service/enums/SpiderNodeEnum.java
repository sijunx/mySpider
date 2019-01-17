package com.spider.search.service.enums;

public enum SpiderNodeEnum {
    //  节点01：根据url获取数据，存入data表
    //  节点02：根据url对应的data，抽取关键词
    //  节点03：根据关键词，计算摘要

    //  节点01：计算url对应data和主题之间的相似度
    //  节点02：根据url与主题之间的相似度，计算热点
    URLDATAEXTRACT("UrlDataExtract","URL数据抽取"),
    KEYEXTRACT("KeyExtract", "关键字抽取"),
    SUMMARYEXTRACT("SummaryExtract", "摘要抽取"),
    REVERSEINDEXCAL("ReverseIndexCal","倒序索引"),

    SIMILARCAL("SimilarCal", "相似度计算"),
    HOTSCAL("HotsCal", "热点计算"),

    ;

    private String value;
    private String desc;

    SpiderNodeEnum(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public String getValue(){
        return this.value;
    }

}
