package com.spider.user.papp;

import com.spider.user.papp.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class SpiderFilterConfig {

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(getXssFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("exclude", "/swagger*");
        registration.setName("XssFilter");
        return registration;
    }

    @Bean
    public Filter getXssFilter() {
        return new XssFilter();
    }
}







