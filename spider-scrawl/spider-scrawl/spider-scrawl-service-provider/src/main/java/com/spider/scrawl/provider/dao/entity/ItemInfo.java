package com.spider.scrawl.provider.dao.entity;

import java.util.Date;

public class ItemInfo {
    private Long id;

    private String itemCode;

    private String itemCname;

    private String itemEname;

    private String itemDesc;

    private Integer itemType;

    private String itemLen;

    private String itemRemark;

    private Date createTime;

    private Long createUserId;

    private Date updateTime;

    private Long updateUserId;

    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getItemLen() {
        return itemLen;
    }

    public void setItemLen(String itemLen) {
        this.itemLen = itemLen;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getItemCname() {
        return itemCname;
    }

    public void setItemCname(String itemCname) {
        this.itemCname = itemCname;
    }

    public String getItemEname() {
        return itemEname;
    }

    public void setItemEname(String itemEname) {
        this.itemEname = itemEname;
    }
}