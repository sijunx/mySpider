package com.spider.base.dubbo.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(group = com.alibaba.dubbo.common.Constants.PROVIDER, order = -2147483647)
public class SpiderProviderFilter implements Filter{

    private final static Logger logger = LoggerFactory.getLogger(SpiderProviderFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) {
        logger.info("接口名：{}",invocation.getInvoker().getInterface().getName());
        logger.info("方法名：{}",invocation.getMethodName());
        String clientIp = RpcContext.getContext().getRemoteHost();
        logger.info("访问ip为{}", clientIp);
        logger.info("------------------------提供方过滤器---------------");
        return invoker.invoke(invocation);
    }
}
