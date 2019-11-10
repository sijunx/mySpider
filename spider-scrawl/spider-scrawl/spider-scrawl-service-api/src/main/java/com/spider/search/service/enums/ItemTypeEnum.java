package com.spider.search.service.enums;

public enum ItemTypeEnum {

    BIGINT_TYPE(1, "bigint"),
    TINYINT_TYPE(2, "tinyint"),
    DATE_TYPE(3, "date"),
    VARCHAR_TYPE(4, "varchar"),
    DECIMAL_TYPE(5, "decimal"),
    INT_TYPE(6, "int")
    ;

    private int code;
    private String desc;

    ItemTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ItemTypeEnum getByCode(int code) {
        for (ItemTypeEnum indexEnum : values()) {
            if (indexEnum.getCode()== code) {
                return indexEnum;
            }
        }
        return null;
    }

    public static ItemTypeEnum getByDesc(String desc) {
        for (ItemTypeEnum indexEnum : values()) {
            if (indexEnum.getDesc().equals(desc)) {
                return indexEnum;
            }
        }
        return null;
    }
}