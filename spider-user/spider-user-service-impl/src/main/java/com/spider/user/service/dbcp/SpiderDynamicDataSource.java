package com.spider.user.service.dbcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Order(2)
public class SpiderDynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger logger = LoggerFactory.getLogger(SpiderDynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("触发动态选择数据源");
        logger.debug("选择好的数据源为：" + SpiderDynamicDataSourceHolder.getDbType());
        return SpiderDynamicDataSourceHolder.getDbType();
    }
}
