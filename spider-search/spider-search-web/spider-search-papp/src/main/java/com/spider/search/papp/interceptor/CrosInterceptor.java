package com.spider.search.papp.interceptor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class CrosInterceptor implements HandlerInterceptor {

    // 允许跨域访问的域名
//    private static final List<String> ALLOWED_ORIGINS = Arrays.asList(ElessConfigUtil.getValueByKey("cors.allowed.origins").split(","));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 返回数据跨域支持
        cors(request, response);
        // 拦截options请求
        //return !"options".equals(request.getMethod().toLowerCase());
        return true;
    }

    // 跨域支持
    private void cors(HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        if (isOriginAllow(origin)) {
            if (null == request.getAttribute("addOrigin")) {
                request.setAttribute("addOrigin", true);
                response.addHeader("Access-Control-Allow-Origin", origin);
                response.addHeader("Access-Control-Allow-Methods", "*");
                response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-Shard");
                response.addHeader("Access-Control-Allow-Credentials", "true");
            }
        }
    }

    /**
     * url是否允许跨域
     *
     * @param origin
     * @return
     */
    private boolean isOriginAllow(String origin) {
        return true;
//
//        if (StringUtils.isBlank(origin)) {
//            return false;
//        }
//        for (String allowOrigin : ALLOWED_ORIGINS) {
//            if (origin.endsWith(allowOrigin)) {
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws IOException {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}