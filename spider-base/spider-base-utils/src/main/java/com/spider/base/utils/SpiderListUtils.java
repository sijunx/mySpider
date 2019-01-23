package com.spider.base.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 集合工具类
 * @author xusijun
 * @date 2019.1.23
 */
public class SpiderListUtils {

	//	List转为Map
	public static <K, T> Map<K, T> listToMap(List<T> beanList, String keyProp)  {
		Map<K, T> result = new LinkedHashMap<K, T>();
		if (!CollectionUtils.isEmpty(beanList) && !StringUtils.isBlank(keyProp)) {
			for (T bean : beanList) {
				try {
					@SuppressWarnings("unchecked")
					K val = (K) PropertyUtils.getProperty(bean, keyProp);
					if (val != null) {
						result.put(val, bean);
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return result;
	}
}