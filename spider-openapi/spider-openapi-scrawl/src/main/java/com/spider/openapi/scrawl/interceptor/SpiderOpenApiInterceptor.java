package com.spider.openapi.scrawl.interceptor;

import com.spider.base.rsa.RSACodeUtil;
import com.spider.openapi.scrawl.util.SpiderIpUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class SpiderOpenApiInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(SpiderOpenApiInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入拦截器---------------------------------------------------");
        logger.info("ip:{} ", SpiderIpUtil.getIp());
        String signature = request.getHeader("signature");
        String dataStr = request.getHeader("data");
        String[] dataArray = new String[]{dataStr};
        request.setAttribute("data", dataStr);

        String result = null;
        try {
            result = RSACodeUtil.decode(signature);
        }catch (Exception e){
            ExceptionUtils.getStackTrace(e);
        }
        if(!Objects.equals(result, "whoAmI")){
            logger.info("------------------签名验证不通过----------------------------------------------------");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
