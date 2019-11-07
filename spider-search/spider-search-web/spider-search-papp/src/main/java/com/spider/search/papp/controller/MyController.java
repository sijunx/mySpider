package com.spider.search.papp.controller;

import com.alibaba.fastjson.JSON;
import com.spider.search.papp.response.ResponseDTO;
import com.spider.search.service.api.ItemService;
import com.spider.search.service.dto.ItemDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mySpider")
public class MyController {

    private final static Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    @ResponseBody
    public  ResponseDTO<List<ItemDto>>  getList(@RequestBody Map map) {

        System.out.println("object:"+ JSON.toJSON(map));
        String keyWord = null;
        if(map.get("keyword") != null) {
            keyWord = (String) map.get("keyword");
        }
        if(StringUtils.isBlank(keyWord)){
            keyWord = "订单ID";
        }
        List<ItemDto> itemDtoList = null;
        try {
            itemDtoList = itemService.getList(keyWord);
            System.out.println("获取到数据啦itemDtoList"+JSON.toJSONString(itemDtoList));
        }catch (Exception e){
            e.printStackTrace();
        }

        List<ItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(getItemDto("1", "orderId", "order identity", "订单ID", "Long", "20"));
        itemDtos.add(getItemDto("1", "shippingOrderId", "shipping order identity", "运单ID", "Long", "20"));
        itemDtos.add(getItemDto("1", "userId", "user identity", "用户ID", "Long", "20"));
        itemDtos.add(getItemDto("1", "userName", "user name", "用户姓名", "String", "20"));
        itemDtos.add(getItemDto("1", "accountId", "account identity", "账单ID", "Long", "20"));
        itemDtos.add(getItemDto("1", "status", "status", "状态", "Integer", "11"));
        itemDtos.add(getItemDto("1", "createUserId", "create user identity", "创建人ID", "Long", "20"));
        itemDtos.add(getItemDto("1", "createUserName", "create user id", "创建人姓名", "String", "40"));
        itemDtos.add(getItemDto("1", "createUserType", "create user type", "创建人类型", "Integer", "11"));
        itemDtos.add(getItemDto("1", "createTime", "create time", "创建时间", "Long", "13"));
        ResponseDTO responseDTO = new ResponseDTO();
        if(CollectionUtils.isNotEmpty(itemDtoList)){
            responseDTO.setData(itemDtoList);
        }else{
            responseDTO.setData(itemDtos);
        }

        return responseDTO;
    }


    private ItemDto getItemDto(String id, String itemCode, String itemEname, String itemCname, String itemType, String itemLen){
        ItemDto itemDto = new ItemDto();
        itemDto.setId(id);
        itemDto.setItemCode(itemCode);
        itemDto.setItemEname(itemEname);
        itemDto.setItemCname(itemCname);
        itemDto.setItemType(itemType);
        itemDto.setItemLen(itemLen);
        return itemDto;
    }
}