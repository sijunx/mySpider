package com.spider.scrawl.provider.service;


import com.alibaba.fastjson.JSON;
import com.spider.base.excel.ExcelExportUtil;
import com.spider.base.utils.MapCacheObj;
import com.spider.base.utils.MapCacheUtils;
import com.spider.scrawl.provider.dao.entity.ItemInfo;
import com.spider.scrawl.provider.dao.mapper.ItemInfoMapper;
import com.spider.scrawl.provider.transfer.ItemInfoTransfer;
import com.spider.search.service.api.ItemService;
import com.spider.search.service.dto.ItemDto;
import com.spider.search.service.enums.ItemTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemInfoMapper itemInfoMapper;

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
        lock.lock();
        String excelPath = "/Users/xusijun/Documents/字根集合.xls";
        ExcelExportUtil excelExportUtil = new ExcelExportUtil();
        Sheet sheet = excelExportUtil.getSheetByExcel(excelPath);
        for(int icount=0; icount<=sheet.getLastRowNum(); icount++){
            Row row = sheet.getRow(icount);
            String itemCode = row.getCell(0)!=null?row.getCell(0).getStringCellValue():"";
            String itemCname = row.getCell(1)!=null?row.getCell(1).getStringCellValue():"";
            String itemEname = row.getCell(2)!=null?row.getCell(2).getStringCellValue():"";
            String itemType = row.getCell(3)!=null?row.getCell(3).getStringCellValue():"";
            String itemLen = row.getCell(4)!=null?row.getCell(4).getStringCellValue():"";
            String itemDesc = row.getCell(5)!=null?row.getCell(5).getStringCellValue():"";

            List<ItemInfo>  itemInfos = itemInfoMapper.getListByItemCodeAndCname(itemCode, itemCname);
            if(CollectionUtils.isNotEmpty(itemInfos)){
                continue;
            }

            System.out.println("itemCode:"+itemCode);
            System.out.println("itemCname:"+itemCname);
            System.out.println("itemEname:"+itemEname);
            System.out.println("itemType:"+itemType);
            System.out.println("itemLen:"+itemLen);
            System.out.println("itemDesc:"+itemDesc);

            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setItemCode(itemCode);
            itemInfo.setItemCname(itemCname);
            itemInfo.setItemEname(itemEname);
            itemInfo.setItemDesc(itemDesc);
            itemInfo.setItemType(ItemTypeEnum.getByDesc(itemType)!=null?ItemTypeEnum.getByDesc(itemType).getCode():0);
            itemInfo.setItemLen(itemLen);
            itemInfo.setItemRemark("");
            itemInfoMapper.insertSelective(itemInfo);
        }
        lock.unlock();

    }
}

