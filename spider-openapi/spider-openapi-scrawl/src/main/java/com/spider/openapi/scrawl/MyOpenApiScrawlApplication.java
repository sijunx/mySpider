package com.spider.openapi.scrawl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.spider.*")
public class MyOpenApiScrawlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyOpenApiScrawlApplication.class, args);
	}
}

