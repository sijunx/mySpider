package com.spider.scrawl.provider.service;


import com.alibaba.fastjson.JSON;
import com.spider.scrawl.provider.dao.entity.ItemInfo;
import com.spider.scrawl.provider.dao.mapper.ItemInfoMapper;
import com.spider.search.service.api.ItemService;
import com.spider.search.service.dto.ItemDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    @Override
    public List<ItemDto> getList(String keyWord){
        List<ItemInfo> itemInfos = itemInfoMapper.getListByKeyWord(keyWord);
        if(CollectionUtils.isEmpty(itemInfos)){
            return Collections.emptyList();
        }
        List<ItemDto> itemDtos = new ArrayList<>();
        for(ItemInfo itemInfo:itemInfos){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemCode(itemInfo.getItemCode());
            itemDto.setItemCname(itemInfo.getItemDesc());
            itemDto.setItemType(itemInfo.getItemType()!=null?itemInfo.getItemType().toString():"");
            itemDto.setItemLen(itemInfo.getItemLen()!=null?itemInfo.getItemLen().toString():"");
            itemDtos.add(itemDto);
        }
        System.out.println("itemInfos结果:"+ JSON.toJSON(itemDtos));
        return itemDtos;
    }
}

