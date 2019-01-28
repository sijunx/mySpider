package com.spider.base.dbcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class SpiderDynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger logger = LoggerFactory.getLogger(SpiderDynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        logger.debug("当前使用的数据源为：" + SpiderDynamicDataSourceHolder.getDbType());
        return SpiderDynamicDataSourceHolder.getDbType();
    }
}
