package com.spider.dist.scrawl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.spider.*")
public class MySpiderDistScrawlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpiderDistScrawlApplication.class, args);
	}
}

