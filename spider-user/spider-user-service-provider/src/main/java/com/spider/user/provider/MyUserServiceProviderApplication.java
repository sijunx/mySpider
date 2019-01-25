package com.spider.user.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.spider.*")
public class MyUserServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyUserServiceProviderApplication.class, args);
	}
}

