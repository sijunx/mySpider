package com.spider.base.mybatis.dao.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

@Intercepts({
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
		@Signature(type = Executor.class, method = "update", args = {MappedStatement.class,Object.class})})
public class SpiderSqlExplainInterceptor implements Interceptor {

	private final static Logger logger = LoggerFactory.getLogger(SpiderSqlExplainInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement mappedStatement = (MappedStatement) args[0];
		//	mapperInfo 值例如: com.spider.user.service.dao.impl.com.spider.scrawl.provider.dao.mapper.SpiderUserInfoMapper.findListByPhone
		String mapperInfo = mappedStatement.getId();
		logger.info("mapperInfo:{}", mapperInfo);
		String[] mapperClassInfos = mapperInfo.split("\\.");
		//	mapperInfoSimple 值例如:SpiderUserInfoMapper#findListByPhone
		String mapperInfoSimple = mapperClassInfos[mapperClassInfos.length - 2] + "#" + mapperClassInfos[mapperClassInfos.length - 1];
		//	add by xusijun:调试阶段，此处还需要做进一步调试改进
		BoundSql boundSql = (BoundSql) args[5];
		if(boundSql != null){
			List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
			for(ParameterMapping parameterMapping:parameterMappings){
				logger.info("参数:{} ", parameterMapping.getProperty());
			}
			logger.info("sql:{} ", boundSql.getSql());
			logger.info("参数值：{}", String.valueOf(boundSql.getParameterObject()));
		}
		//	开始时间
		long start = System.currentTimeMillis();
		//	执行处理操作
		Object returnObj = invocation.proceed();
		//	用时计算
		long useTime = System.currentTimeMillis() - start;
		logger.info("mapperId:{},用时：{} ms", mapperInfoSimple, useTime);
		return returnObj;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		logger.info("properties : {}", properties);
		String slowSqlMills = properties.getProperty("slowSqlMills");
		logger.info("slowSqlMills : {}", slowSqlMills);
	}
}