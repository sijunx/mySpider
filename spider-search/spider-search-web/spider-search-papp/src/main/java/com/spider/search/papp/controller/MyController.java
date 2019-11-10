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
        String keyWord = null;
        if(map.get("keyword") != null) {
            keyWord = (String) map.get("keyword");
        }
        ResponseDTO responseDTO = new ResponseDTO();
        List<ItemDto> itemDtoList = null;
        if(StringUtils.isBlank(keyWord)){
            itemDtoList = itemService.getTop10();
            responseDTO.setData(itemDtoList);
            return responseDTO;
        }
        try {
            itemDtoList = itemService.getList(keyWord);
            System.out.println("获取到数据啦itemDtoList"+JSON.toJSONString(itemDtoList));
        }catch (Exception e){
            e.printStackTrace();
        }
        responseDTO.setData(itemDtoList);
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

    @RequestMapping("/export")
    @ResponseBody
    public  ResponseDTO<List<ItemDto>>  export() {

        itemService.exportDataFromExcel();
        return null;
    }


}