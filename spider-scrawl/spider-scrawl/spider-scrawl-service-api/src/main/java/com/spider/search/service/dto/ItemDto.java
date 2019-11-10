package com.spider.search.service.dto;

import java.io.Serializable;

public class ItemDto implements Serializable {
    /** id */
    private String id;

    private String itemCode;

    private String itemEname;

    private String itemCname;

    private String itemType;

    private String itemLen;

    private String itemDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemEname() {
        return itemEname;
    }

    public void setItemEname(String itemEname) {
        this.itemEname = itemEname;
    }

    public String getItemCname() {
        return itemCname;
    }

    public void setItemCname(String itemCname) {
        this.itemCname = itemCname;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemLen() {
        return itemLen;
    }

    public void setItemLen(String itemLen) {
        this.itemLen = itemLen;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}