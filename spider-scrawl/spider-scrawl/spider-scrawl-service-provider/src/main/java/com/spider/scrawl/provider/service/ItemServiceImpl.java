package com.spider.scrawl.provider.service;

import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.java.spi.common.MyCallBackService;
import com.java.spi.common.Search;
import com.java.spi.demo.a.impl.DemoASearch;
import com.spider.base.excel.ExcelExportUtil;
import com.spider.base.http.DingDingUtil;
import com.spider.base.kafka.consumer.MyKafkaConsumerClient;
import com.spider.base.kafka.producer.MyKafkaProducerClient;
import com.spider.base.utils.MyHttpUtil;
import com.spider.scrawl.provider.dao.entity.ItemInfo;
import com.spider.scrawl.provider.dao.entity.MonitorInfo;
import com.spider.scrawl.provider.dao.mapper.ItemInfoMapper;
import com.spider.scrawl.provider.dao.mapper.MonitorInfoMapper;
import com.spider.scrawl.provider.dto.KafkaMonitorDto;
import com.spider.scrawl.provider.transfer.ItemInfoTransfer;
import com.spider.search.service.api.ItemService;
import com.spider.search.service.dto.ItemDto;
import com.spider.search.service.enums.ItemTypeEnum;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ItemServiceImpl implements ItemService {

    private final static Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemInfoMapper itemInfoMapper;
    @Autowired
    private MonitorInfoMapper monitorInfoMapper;
    @Autowired
    private MyMessageProcessorDataItemTopic myMessageProcessor60;
    @Autowired
    private MyMessageProcessorDataSynSaveDataBaseTopic dataSynSaveDataBaseTopic;
    @Autowired
    private MyMessageProcessorCanalBinlogTopic canalBinlogTopic;
    @Autowired
    private MyCallBackService myCallBackService;
    @Autowired
    private Search search;

    Lock lock = new ReentrantLock();

    private Map<Long, Long> sortMap = new HashMap<Long, Long>();

    @Override
    public List<ItemDto> getList(String keyWord){
        List<ItemInfo> itemInfos = itemInfoMapper.getListByKeyWord(keyWord);
        if(CollectionUtils.isEmpty(itemInfos)){
            return Collections.emptyList();
        }
        List<ItemDto> itemDtos = new ArrayList<>();
        for(ItemInfo itemInfo:itemInfos){
            ItemDto itemDto = ItemInfoTransfer.getItemDtoByConvert(itemInfo);
            itemDtos.add(itemDto);
            Long long01 = sortMap.get(Long.parseLong(itemDto.getId()));
            sortMap.put(Long.parseLong(itemDto.getId()), long01!=null?long01+1L:1L);
        }
        return itemDtos;
    }

    @Override
    public List<ItemDto> getTop10(){
        if(sortMap==null || sortMap.size()<=0){
            return Collections.emptyList();
        }
        List<Map.Entry<Long,Long>> list = new ArrayList<>(sortMap.entrySet()); //将map的entryset放入list集合
        //对list进行排序，并通过Comparator传入自定义的排序规则
        Collections.sort(list,new Comparator<Map.Entry<Long, Long>>() {
            @Override
            public int compare(Map.Entry<Long, Long> o1, Map.Entry<Long, Long> o2) {
                return o2.getValue().compareTo(o1.getValue()); //重写排序规则，小于0表示升序，大于0表示降序
            }
        });
        //用迭代器对list中的键值对元素进行遍历
        Iterator<Map.Entry<Long, Long>> iter = list.iterator();
        int icount = 0;
        List<Long> idList = new ArrayList<>();
        while(iter.hasNext()){
            Map.Entry<Long, Long> item = iter.next();
            Long key = item.getKey();
            Long value = item.getValue();
            idList.add(key);
            if(icount++ >= 10){
                break;
            }
        }

        List<ItemInfo> itemInfos = itemInfoMapper.getByIdList(idList);
        return ItemInfoTransfer.getByConvertList(itemInfos);
    }

    @Override
    public void exportDataFromExcel(){
        Config appConfig = ConfigService.getAppConfig();
        String prop = appConfig.getProperty("excel_dic_data_path", "");
        System.out.println("-------------------excel_dic_data_path:"+prop);
        lock.lock();
        try {
            String excelPath = prop;
            ExcelExportUtil excelExportUtil = new ExcelExportUtil();
            Sheet sheet = excelExportUtil.getSheetByExcel(excelPath);
            System.out.println("row:" + sheet.getLastRowNum());
            for (int icount = 0; icount <= sheet.getLastRowNum(); icount++) {
                Row row = sheet.getRow(icount);
                String itemCode = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "";
                String itemCname = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : "";
                String itemEname = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "";
                String itemType = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "";
                String itemLen = row.getCell(4) != null ? row.getCell(4).getStringCellValue() : "";
                String itemDesc = row.getCell(5) != null ? row.getCell(5).getStringCellValue() : "";

                System.out.println("itemCname:" + itemCname);
                List<ItemInfo> itemInfos = itemInfoMapper.getListByItemCodeAndCname(itemCode, itemCname);
                if (CollectionUtils.isNotEmpty(itemInfos)) {
                    continue;
                }

                System.out.println("itemCode:" + itemCode);
                System.out.println("itemCname:" + itemCname);
                System.out.println("itemEname:" + itemEname);
                System.out.println("itemType:" + itemType);
                System.out.println("itemLen:" + itemLen);
                System.out.println("itemDesc:" + itemDesc);

                ItemInfo itemInfo = new ItemInfo();
                itemInfo.setItemCode(itemCode);
                itemInfo.setItemCname(itemCname);
                itemInfo.setItemEname(itemEname);
                itemInfo.setItemDesc(itemDesc);
                itemInfo.setItemType(ItemTypeEnum.getByDesc(itemType) != null ? ItemTypeEnum.getByDesc(itemType).getCode() : 0);
                itemInfo.setItemLen(itemLen);
                itemInfo.setItemRemark("");
                itemInfoMapper.insertSelective(itemInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String send(String message){
//        SpiderKafkaProducerClient.sendMessage("item_add_topic", message);
        MyKafkaProducerClient.sendMessage("item_add_topic", message);
        return null;
    }

    @Override
    public String consumeDataItemTopicMsg(){
        //字根、词组接受消息
//        SpiderKafkaConsumerClient.getInstance().receiveMessages("item_add_topic", "item_add_topic_group", myMessageProcessor60);
        MyKafkaConsumerClient.receiveMessage("item_add_topic", myMessageProcessor60);
        return null;
    }

    @Override
    public String consumeCanalBinlogDataTopic(String topic){
        //字根、词组接受消息
        //SpiderKafkaConsumerClient.getInstance().receiveMessages("canal_binlog_data_topic", "canal_binlog_data_topic_group", canalBinlogTopic);
        if(StringUtils.isBlank(topic)){
            topic = "canal_binlog_data_topic";
        }
        MyKafkaConsumerClient.receiveMessage(topic, canalBinlogTopic);
        return null;
    }

    @Override
    public String recvHttpMessage(String message){
        //SpiderKafkaProducerClient.sendMessage("data_syn_topic", message);
        MyKafkaProducerClient.sendMessage("data_syn_topic", message);
        return null;
    }

    @Override
    public String consumeDataSynTopicMsg(){
        //SpiderKafkaConsumerClient.getInstance().receiveMessages("data_syn_topic", "data_syn_topic_group", dataSynSaveDataBaseTopic);
        MyKafkaConsumerClient.receiveMessage("data_syn_topic", dataSynSaveDataBaseTopic);
        return null;
    }

    @Override
    public void kafkaMonitor(){
        while(true){
            try {
                Config appConfig = ConfigService.getAppConfig();
                String zookeeperAddress = appConfig.getProperty("kafka.zookeeper", "");
                //ms
                int sessionOutTime = 10000;
                ZkClient zkc = new ZkClient(new ZkConnection(zookeeperAddress), sessionOutTime);
                List<String> ids = zkc.getChildren("/brokers/ids");
                Integer idsSize = 0;
                if (ids != null) {
                    idsSize = ids.size();
                }
                KafkaMonitorDto kafkaMonitorDto = new KafkaMonitorDto();
                kafkaMonitorDto.setZookeeperAddress(zookeeperAddress);
                kafkaMonitorDto.setBrokersCount(idsSize);
                String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
                String frontStr = dateStr.substring(0, 10);
                String backStr = dateStr.substring(11, 19);
                String dateStr02 = new StringBuilder().append(frontStr).append("T").append(backStr).append("Z").toString();
                kafkaMonitorDto.setTs(dateStr02);
                MyKafkaProducerClient.sendMessage("kafka_brokers_monitor_topic", JSON.toJSONString(kafkaMonitorDto));
            }catch (Exception e){
                logger.error("kafka监控线程异常:{}", ExceptionUtils.getStackTrace(e));
            }
            try{

                Config appConfig = ConfigService.getAppConfig();
                String loopTimeStr = appConfig.getProperty("kafka.brokers.monitor.zookeeper.loop.time", "");
                Integer loopTime = 30;
                if(NumberUtils.isNumber(loopTimeStr)){
                    loopTime = new Integer(loopTimeStr);
                }
                Thread.sleep(loopTime * 1000);
            }catch (Exception e){
                logger.error("kafka监控线程休眠异常:{}", ExceptionUtils.getStackTrace(e));
            }
        }
    }

    @Override
    public void loopQueryKafkaMonitorData(){
        while(true) {
            try {
                Config appConfig = ConfigService.getAppConfig();
                String monitorCountStr = appConfig.getProperty("kafka.brokers.monitor.count", "");
                String loopTimeStr = appConfig.getProperty("kafka.brokers.monitor.loop.time", "");
                Integer monitorCount = 0;
                if(StringUtils.isNotBlank(monitorCountStr) && NumberUtils.isNumber(monitorCountStr)){
                    monitorCount = new Integer(monitorCountStr);
                }
                List<MonitorInfo> monitorInfos = monitorInfoMapper.getListByCode("KAFKA");
                if (!CollectionUtils.isEmpty(monitorInfos)) {
                    for (MonitorInfo monitorInfo : monitorInfos) {
                        if(     StringUtils.isBlank(monitorInfo.getItemValue())
                            ||  !NumberUtils.isNumber(monitorInfo.getItemValue())){
                            continue;
                        }
                        Integer itemValue = new Integer(monitorInfo.getItemValue());
                        if (itemValue != null && itemValue<= monitorCount) {
                            String url = "https://oapi.dingtalk.com/robot/send?access_token=11681ef2bf9db061c0b3d582c2a1d4e4f7c2f2b51a13ebc2a8153074b39430ac";
                            DingDingUtil.sendDingMessage(url, "测试 预警【" + monitorInfo.getItemName() + "】 brokers 数量:" + monitorInfo.getItemValue());
                        }
                    }
                }
            }catch (Exception e){
                logger.error("kafka监控数据轮询线程异常:{}", ExceptionUtils.getStackTrace(e));
            }
            try{
                Config appConfig = ConfigService.getAppConfig();
                String loopTimeStr = appConfig.getProperty("kafka.brokers.monitor.loop.time", "");
                Integer loopTime = 30;
                if(NumberUtils.isNumber(loopTimeStr)){
                    loopTime = new Integer(loopTimeStr);
                }
                Thread.sleep(loopTime * 1000);
            }catch (Exception e){
                logger.error("kafka监控数据轮询线程休眠异常:{}", ExceptionUtils.getStackTrace(e));
            }
        }
    }

    @Override
    public void testSpi(){
//        DemoASearch demoASearch = new DemoASearch();
//        List ls = demoASearch.search("订单", myCallBackService);
        search.search("订单", myCallBackService);

//        ServiceLoader<Search> serviceLoaders = ServiceLoader.load(Search.class);
//        Iterator<Search> iterators = serviceLoaders.iterator();
//        Search myTestYouService = null;
//        if(iterators.hasNext()){
//            System.out.println("xxxxxxxx");
//            myTestYouService = iterators.next();
//        }
//        myTestYouService.search("xxxxxxxxxxxxxxxxxxx00000000000000000000000000000");
    }
}

