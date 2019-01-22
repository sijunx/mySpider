package com.spider.dist.service.util;

import com.spider.dist.service.dto.SpiderUrlDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpiderDistScrawlContext {

    private final static Logger logger = LoggerFactory.getLogger(SpiderDistScrawlContext.class);

    public static ConcurrentHashMap<String, SpiderUrlDTO> URL_MAP = new ConcurrentHashMap();

    public static ConcurrentHashMap<String, SpiderUrlDTO> URL_PASSED_MAP = new ConcurrentHashMap<>();





}
