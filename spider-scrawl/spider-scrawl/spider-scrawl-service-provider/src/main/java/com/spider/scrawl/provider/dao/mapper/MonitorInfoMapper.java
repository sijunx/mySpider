package com.spider.scrawl.provider.dao.mapper;

import com.spider.scrawl.provider.dao.entity.MonitorInfo;
import com.spider.scrawl.provider.dao.entity.MonitorInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MonitorInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(MonitorInfo record);

    int insertSelective(MonitorInfo record);

    MonitorInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MonitorInfo record);

    int updateByPrimaryKey(MonitorInfo record);

    List<MonitorInfo> getListByCode(@Param("itemCode") String itemCode);

}