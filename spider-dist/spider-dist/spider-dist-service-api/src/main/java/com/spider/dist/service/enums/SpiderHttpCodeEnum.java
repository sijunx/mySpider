package com.spider.dist.service.enums;

public enum SpiderHttpCodeEnum {

    SUCCESS(10000, "成功");

    SpiderHttpCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDesc(Integer index){
        if(index != null){
            SpiderHttpCodeEnum[] enums = SpiderHttpCodeEnum.values();
            for(SpiderHttpCodeEnum e : enums){
                if(e.getCode() == index.intValue()){
                    return e.getDesc();
                }
            }
        }
        return null;
    }
}
