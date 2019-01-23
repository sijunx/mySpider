package com.spider.dist.service.api;

import java.util.List;

public interface ISpiderKeyWordExtractService {

    List<String> getKeyWordList(String summary);
}
