package com.spider.dist.service.enums;

public enum SpiderUrlStatusEnum {

    START(10, "创建"),
    PROCESSSING(20, "处理中"),
    UNPASSED(25, "未通过"),
    PASSED(30, "已通过"),
    SENDED_SUCCESS(40, "已发送"),
    SENDED_FAIL(50, "发送失败"),
    CAN_DELETE(60, "可以删除");

    SpiderUrlStatusEnum(Integer code, String desc) {
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
            SpiderUrlStatusEnum[] enums = SpiderUrlStatusEnum.values();
            for(SpiderUrlStatusEnum e : enums){
                if(e.getCode() == index.intValue()){
                    return e.getDesc();
                }
            }
        }
        return null;
    }
}