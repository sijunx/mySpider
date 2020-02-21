package com.spider.scrawl.provider.service;

import com.alibaba.fastjson.JSON;
import com.spider.scrawl.provider.dao.entity.ItemInfo;
import com.spider.search.service.enums.ItemTypeEnum;

import javax.xml.bind.SchemaOutputResolver;

public class Test004 {

    public static void main(String[] arg){
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setItemCode("color");
        itemInfo.setItemCname("颜色");
        itemInfo.setItemEname("color");
        itemInfo.setItemDesc("颜色");
        itemInfo.setItemType(4);
        itemInfo.setItemLen("20");
        itemInfo.setItemRemark("");

        System.out.println("sss:"+ JSON.toJSONString(itemInfo));
        String xx = "{\"itemCname\":\"颜色\",\"itemCode\":\"color\",\"itemDesc\":\"颜色\",\"itemEname\":\"color\",\"itemLen\":\"20\",\"itemRemark\":\"\",\"itemType\":4}";
    }


}
