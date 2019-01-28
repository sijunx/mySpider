package com.spider.base.utils;

import org.springframework.beans.BeansException;
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

}
