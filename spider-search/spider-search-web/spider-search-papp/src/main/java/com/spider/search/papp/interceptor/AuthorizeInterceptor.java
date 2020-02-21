package com.spider.search.papp.interceptor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizeInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(AuthorizeInterceptor.class);

    private final static String UNKNOW = "unknown";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle 进入 时间:{}", System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n");
        String contextPath = request.getRequestURI();
        String method = request.getMethod();
        sb.append("clientIp##" + getIpAddress(request) + " \r\n");
        sb.append("method##" + request.getMethod() + " \r\n");
        sb.append("url##" + contextPath + " \r\n");
        sb.append("session##" + JSON.toJSONString(request.getSession()) + " \r\n");
        sb.append("session false ##" + JSON.toJSONString(request.getSession(false)));
        sb.append("sessionId##" + request.getSession().getId() + " \r\n");
        sb.append("session.token##" + request.getSession().getAttribute("token") + "\r\n");
        sb.append("session.userId##" + request.getSession().getAttribute("userId") + "\r\n");
        logger.info("--------请求参数:{}",sb.toString());
        System.out.println("----request param:"+sb.toString());

        String tokenStr=request.getParameter("token");

        System.out.println("token "+tokenStr);
        if(tokenStr!=null && tokenStr.contains("pzx")){
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle--------------------进入--------------------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion--------------------进入--------------------------");
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
