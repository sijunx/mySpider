package com.spider.scrawl.provider.dto;

import java.io.Serializable;

public class KafkaMonitorDto implements Serializable {

    private String zookeeperAddress;

    private Integer brokersCount;

    private String ts;

    public String getZookeeperAddress() {
        return zookeeperAddress;
    }

    public void setZookeeperAddress(String zookeeperAddress) {
        this.zookeeperAddress = zookeeperAddress;
    }

    public Integer getBrokersCount() {
        return brokersCount;
    }

    public void setBrokersCount(Integer brokersCount) {
        this.brokersCount = brokersCount;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "KafkaMonitorDto{" +
                "zookeeperAddress='" + zookeeperAddress + '\'' +
                ", brokersCount=" + brokersCount +
                ", ts='" + ts + '\'' +
                '}';
    }
}
