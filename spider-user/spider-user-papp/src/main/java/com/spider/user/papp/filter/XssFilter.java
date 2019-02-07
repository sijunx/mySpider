package com.spider.user.papp.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(XssFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException{

        logger.info("XssFilter 过滤器 init ----------------------------");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {
        logger.info("XssFilter 过滤器do----------------------------");
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        filterChain.doFilter(xssRequest, response);
        logger.info("XssFilter 过滤器 ");
    }

    public void destroy(){
        logger.info("XssFilter 过滤器 destroy ----------------------------");
    }
}
