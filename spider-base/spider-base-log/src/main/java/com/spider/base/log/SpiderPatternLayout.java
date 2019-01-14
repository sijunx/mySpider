package com.spider.base.log;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author xusijun
 */
public class SpiderPatternLayout extends PatternLayout {

    public SpiderPatternLayout() {
        super();
    }

    public SpiderPatternLayout(String pattern) {
        super(pattern);
    }

    /** 日志存放目录 */
    private static final String LOG_FILE_PATH_KEY = "spider.logger.file.path";

    /** 统一日志控制，可以在此处增加日志的截取 */
    @Override
    public String format(LoggingEvent event) {
        return super.format(event);
    }

}