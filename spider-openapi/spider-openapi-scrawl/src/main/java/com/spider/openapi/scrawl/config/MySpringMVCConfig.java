package com.spider.openapi.scrawl.config;

import com.spider.openapi.scrawl.interceptor.SpiderOpenApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class MySpringMVCConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private SpiderOpenApiInterceptor spiderOpenApiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(spiderOpenApiInterceptor).addPathPatterns("/**");
    }


}
