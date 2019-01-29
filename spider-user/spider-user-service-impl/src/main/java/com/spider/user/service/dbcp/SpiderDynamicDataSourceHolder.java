package com.spider.user.service.dbcp;

public class SpiderDynamicDataSourceHolder {

	public static final ThreadLocal<String> CURRENT_DATA_SOURCE = new ThreadLocal<>();

	public static final String DEFAULT_DATA_SOURCE = "dataSource";

	public static void setDbType(String dbType) {
		CURRENT_DATA_SOURCE.set(dbType);
	}

	public static String getDbType() {
		if (CURRENT_DATA_SOURCE.get() == null) {
			CURRENT_DATA_SOURCE.set(DEFAULT_DATA_SOURCE);
		}
		return (CURRENT_DATA_SOURCE.get());
	}

	public static void clearDbType(){
		CURRENT_DATA_SOURCE.remove();
	}
}
