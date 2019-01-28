package com.spider.base.dbcp;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SpiderGetDataSource {

    String dataSource();

    String desc() default "";
}