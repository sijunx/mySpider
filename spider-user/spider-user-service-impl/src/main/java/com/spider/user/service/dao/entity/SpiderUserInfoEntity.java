package com.spider.user.service.dao.entity;


import com.spider.user.service.dbcp.dao.entity.SpiderBaseEntity;

public class SpiderUserInfoEntity extends SpiderBaseEntity {

    private String name;

    private String idCard;

    private String phone;

    private Integer gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "SpiderUserInfoEntity{" +
                "name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", id=" + id +
                '}';
    }
}