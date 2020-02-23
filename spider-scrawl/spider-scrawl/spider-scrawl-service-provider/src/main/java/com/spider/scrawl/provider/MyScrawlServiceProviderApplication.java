package com.spider.scrawl.provider;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude =
		{
				DataSourceAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class,
				RedisAutoConfiguration.class,
				KafkaAutoConfiguration.class
		})
@ImportResource(locations = {"classpath:context/*.xml"})
public class MyScrawlServiceProviderApplication {

	public static void main(String[] args) {
//		System.setProperty(ConfigConsts.APOLLO_META_KEY, "http://127.0.0.1:8080");
//		System.setProperty(ConfigConsts.APOLLO_META_KEY, "http://192.168.1.100:8080");

//		-DENV=PRO -DAPOLLO_META=http://106.12.161.30:8080
//		String envStr = System.getProperty("env");
//		String apolloMeta = System.getProperty("apollo.meta");
//		if(StringUtils.isBlank(envStr)){
//			System.setProperty("env", "PRO");
//		}
//		if(StringUtils.isBlank(apolloMeta)){
////			System.setProperty("apollo.meta", "http://106.12.161.30:8080");
//			System.setProperty("apollo.meta", "http://127.0.0.1:8080");
//		}
		SpringApplication.run(MyScrawlServiceProviderApplication.class, args);
	}
}

