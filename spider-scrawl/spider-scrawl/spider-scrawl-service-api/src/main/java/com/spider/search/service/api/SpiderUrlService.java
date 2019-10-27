package com.spider.search.service.api;

import com.spider.search.service.dto.SpiderUrlDto;

import java.util.List;

public interface SpiderUrlService {

    void insertList();

    List<SpiderUrlDto> getUseAbleSpiderUrl();
}
