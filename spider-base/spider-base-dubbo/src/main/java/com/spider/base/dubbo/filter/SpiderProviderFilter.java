package com.spider.base.dubbo.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Activate(group = com.alibaba.dubbo.common.Constants.PROVIDER, order = -2147483647)
public class SpiderProviderFilter implements Filter{

    private final static Logger logger = LoggerFactory.getLogger(SpiderProviderFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) {
        System.out.println("过滤器---------------------------------guolvqi---------------");
        logger.info("进入duboo过滤器------------------------SpiderConsumerFilter---------------");
        return new Result() {
            @Override
            public Object getValue() {
                return null;
            }

            @Override
            public Throwable getException() {
                return null;
            }

            @Override
            public boolean hasException() {
                return false;
            }

            @Override
            public Object recreate() throws Throwable {
                return null;
            }

            @Override
            public Object getResult() {
                return null;
            }

            @Override
            public Map<String, String> getAttachments() {
                return null;
            }

            @Override
            public String getAttachment(String s) {
                return null;
            }

            @Override
            public String getAttachment(String s, String s1) {
                return null;
            }
        };
    }
}
