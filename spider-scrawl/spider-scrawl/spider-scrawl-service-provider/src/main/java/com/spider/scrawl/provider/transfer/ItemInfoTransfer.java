package com.spider.scrawl.provider.transfer;

import com.spider.scrawl.provider.dao.entity.ItemInfo;
import com.spider.search.service.dto.ItemDto;
import com.spider.search.service.enums.ItemTypeEnum;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ItemInfoTransfer {

    private ItemInfoTransfer(){

    }

    public static List<ItemDto> getByConvertList(List<ItemInfo> itemInfoFromList){
        List<ItemDto> itemDtoToList = new ArrayList<>();
        if(CollectionUtils.isEmpty(itemInfoFromList)){
            return itemDtoToList;
        }
        if(itemDtoToList == null){
            itemDtoToList = new ArrayList<>();
        }
        for(ItemInfo itemInfo:itemInfoFromList){
            ItemDto itemDto = getItemDtoByConvert(itemInfo);
            itemDtoToList.add(itemDto);
        }
        return itemDtoToList;
    }

    public static ItemDto getItemDtoByConvert(ItemInfo itemInfo){
        ItemDto itemDto = new ItemDto();
        if(itemInfo == null){
            return itemDto;
        }
        String itemType = "";
        ItemTypeEnum itemTypeEnum = ItemTypeEnum.getByCode(itemInfo.getItemType());
        if(itemTypeEnum!=null){
            itemType = itemTypeEnum.getDesc();
        }
        itemDto.setId(itemInfo.getId().toString());
        itemDto.setItemCode(itemInfo.getItemCode());
        itemDto.setItemCname(itemInfo.getItemCname());
        itemDto.setItemEname(itemInfo.getItemEname());
        itemDto.setItemType(itemType);
        itemDto.setItemLen(itemInfo.getItemLen());
        itemDto.setItemDesc(itemInfo.getItemDesc());
        return itemDto;
    }




}
