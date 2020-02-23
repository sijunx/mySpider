package com.spider.scrawl.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.scrawl.provider.dao.entity.ItemInfo;
import com.spider.scrawl.provider.dao.mapper.ItemInfoMapper;
import com.spider.search.service.enums.ItemTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyMessageProcessorDataItemTopic implements ISpiderMessageProcessor {

    private final static Logger logger = LoggerFactory.getLogger(MyMessageProcessorDataItemTopic.class);

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    public boolean messageProcess(String message){
        logger.info("--------------------消息处理，收到的消息内容:{}", message);
        System.out.println("消息处理，收到的消息内容："+message);
        JSONObject jsonObject = JSONObject.parseObject(message);

        String itemCode = jsonObject.getString("itemCode");
        String itemCname = jsonObject.getString("itemCname");
        String itemEname = jsonObject.getString("itemEname");
        String itemDesc = jsonObject.getString("itemDesc");
        String itemType = jsonObject.getString("itemType");
        String itemLen = jsonObject.getString("itemLen");

        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setItemCode(itemCode);
        itemInfo.setItemCname(itemCname);
        itemInfo.setItemEname(itemEname);
        itemInfo.setItemDesc(itemDesc);
        itemInfo.setItemType(ItemTypeEnum.getByDesc(itemType) != null ? ItemTypeEnum.getByDesc(itemType).getCode() : 0);
        itemInfo.setItemLen(itemLen);
        itemInfo.setItemRemark("");
        itemInfoMapper.insertSelective(itemInfo);

        logger.info("--------------------消息处理结束------------------------------");
        return true;
    }

}
