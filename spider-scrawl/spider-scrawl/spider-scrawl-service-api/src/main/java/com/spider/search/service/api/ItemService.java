package com.spider.search.service.api;

import com.spider.search.service.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getList(String keyWord);

    List<ItemDto> getTop10();

    void exportDataFromExcel();

    String consumeDataItemTopicMsg();

    String send(String message);

    String consumeCanalBinlogDataTopic(String topic);

    String recvHttpMessage(String message);

    String consumeDataSynTopicMsg();

    void kafkaMonitor();

    void loopQueryKafkaMonitorData();

    void testSpi();
}