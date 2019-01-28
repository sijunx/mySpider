package com.spider.base.dbcp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
public class SpiderDynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger("SpiderDynamicDataSourceAspect");

    @Pointcut("@annotation(com.spider.base.dbcp.SpiderGetDataSource) && ( execution( public *  com.spider..*FacadeImpl.*(..)) " +
            "||   execution( public *  com.spider..*ServiceImpl.*(..)) " +
            "))")
    public void dsPintCut() {
        System.out.println("切点");
        logger.debug("进入切点-------------------------------------------");
    }

    @Before("dsPintCut()")
    public void beforeChangeDataSource(JoinPoint point)  {
        System.out.println("切点");
        logger.debug("进入切点之前before------------------------------");
        //  获取注解的dataSource
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Annotation[] annotations = targetMethod.getAnnotations();
        SpiderGetDataSource spiderChooseDataSource = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof SpiderGetDataSource) {
                spiderChooseDataSource = (SpiderGetDataSource) annotation;
            }
        }
        //  设置数据源的的本地线程变量
        if(spiderChooseDataSource != null) {
            String dataSource = spiderChooseDataSource.dataSource();
            SpiderDynamicDataSourceHolder.setDbType(dataSource);
        }
    }

    @After("dsPintCut()")
    public void afterChooseDataSource(JoinPoint point) {
        System.out.println("切点");
        logger.debug("切点之后after----------------------------");
    }
}