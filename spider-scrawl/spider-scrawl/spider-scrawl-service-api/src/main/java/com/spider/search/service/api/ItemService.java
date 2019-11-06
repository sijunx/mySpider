package com.spider.search.service.api;

import com.spider.search.service.dto.ItemDto;

import java.util.List;

public interface ItemService {

    public List<ItemDto> getList(String keyWord);
}
