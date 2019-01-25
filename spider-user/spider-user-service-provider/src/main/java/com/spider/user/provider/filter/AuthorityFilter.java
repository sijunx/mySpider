package com.spider.user.provider.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(group = Constants.PROVIDER, order = -999)
public class AuthorityFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorityFilter.class);
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {


        logger.info("接口名：{}",invocation.getInvoker().getInterface().getName());
        logger.info("方法名：{}",invocation.getMethodName());
        String clientIp = RpcContext.getContext().getRemoteHost();
        logger.info("访问ip为{}", clientIp);

        return invoker.invoke(invocation);

    }
}