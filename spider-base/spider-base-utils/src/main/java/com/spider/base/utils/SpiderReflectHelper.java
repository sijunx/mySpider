package com.spider.base.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class SpiderReflectHelper {

	private static final Logger logger = LoggerFactory.getLogger(SpiderReflectHelper.class);

	private SpiderReflectHelper(){

	}

	/**
	 * 根据field名称获取field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		Class<?> superClass = obj.getClass();
		//	若当前类没有Field，则要向前查找父类的Field
		while(superClass != Object.class){
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				logger.warn("反射获取域信息异常 异常信息:{}", ExceptionUtils.getStackTrace(e));
			}
			superClass = superClass.getSuperclass();
		}
		return null;
	}

	/**
	 * 根据field名称获取field变量值
	 * @param object
	 * @param fieldName
	 * @return
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object object, String fieldName) throws IllegalAccessException{
		Field field = getFieldByFieldName(object, fieldName);
		Object value = null;
		//	修改field的安全访问权限
		if(field != null){
			if (field.isAccessible()) {
				value = field.get(object);
			} else {
				field.setAccessible(true);
				value = field.get(object);
				field.setAccessible(false);
			}
		}
		return value;
	}
}
