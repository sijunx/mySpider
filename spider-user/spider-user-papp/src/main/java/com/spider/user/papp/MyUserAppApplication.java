package com.spider.user.papp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(exclude = {
			DataSourceAutoConfiguration.class,
			HibernateJpaAutoConfiguration.class,
			RedisAutoConfiguration.class,
			KafkaAutoConfiguration.class
		})
@ImportResource(locations = {"classpath:context/*.xml"})
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 3600)	//spring在多长时间后强制使redis中的session失效,默认是1800.(单位/秒)
public class MyUserAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyUserAppApplication.class, args);

	}
}

