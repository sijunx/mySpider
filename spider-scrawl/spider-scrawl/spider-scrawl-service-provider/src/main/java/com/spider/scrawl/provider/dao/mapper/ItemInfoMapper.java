package com.spider.scrawl.provider.dao.mapper;

import com.spider.scrawl.provider.dao.entity.ItemInfo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface ItemInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ItemInfo record);

    int insertSelective(ItemInfo record);

    ItemInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemInfo record);

    int updateByPrimaryKey(ItemInfo record);

    List<ItemInfo> getListByKeyWord(@Param("keyWord") String keyWord);


    List<ItemInfo> getListByItemCodeAndCname(@Param("itemCode") String itemCode, @Param("itemCname") String itemCname);

    List<ItemInfo> getByIdList(@Param("idList") List<Long> idList);
}