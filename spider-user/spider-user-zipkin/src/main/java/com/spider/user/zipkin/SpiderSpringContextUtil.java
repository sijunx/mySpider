package com.spider.user.zipkin;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpiderSpringContextUtil implements ApplicationContextAware {
	public static ApplicationContext applicationContext;
	
	@Autowired
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		setContext(applicationContext);
	}

	private static void setContext(ApplicationContext applicationContext) {
		SpiderSpringContextUtil.applicationContext = applicationContext;
	}
	public static ApplicationContext getApplicationContext ()
	{
		return applicationContext;
	}
	
    public static Object getBean(String name) throws BeansException {
            return applicationContext.getBean(name); 
    } 
    public static Object getBean(Class requiredType)
	            throws BeansException {
	    return applicationContext.getBean(requiredType); 
	}
    public static Object getBean(String name, Class requiredType)
                    throws BeansException {
            return applicationContext.getBean(name, requiredType); 
    } 
    public static boolean containsBean(String name) {
            return applicationContext.containsBean(name); 
    } 
    public static boolean isSingleton(String name)
                    throws NoSuchBeanDefinitionException {
            return applicationContext.isSingleton(name); 
    } 
    public static Class getType(String name)
                    throws NoSuchBeanDefinitionException {
            return applicationContext.getType(name); 
    } 
    public static String[] getAliases(String name)
                    throws NoSuchBeanDefinitionException {
            return applicationContext.getAliases(name); 
    } 
}
