package com.spider.scrawl.provider.service;

import com.alibaba.fastjson.JSON;
import com.java.spi.common.MyCallBackService;
import com.spider.search.service.api.ItemService;
import com.spider.search.service.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCallBackServiceImpl implements MyCallBackService {

    @Autowired
    @Lazy
    private ItemService itemService;

    public void test(){

        List<ItemDto> itemDtos = itemService.getList("订单");

        System.out.println("回调成功 message:xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+ JSON.toJSONString(itemDtos));

    }
}

