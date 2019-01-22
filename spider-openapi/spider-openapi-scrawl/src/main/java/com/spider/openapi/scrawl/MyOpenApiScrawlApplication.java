package com.spider.search.papp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.spider.*")
public class MySearchAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySearchAppApplication.class, args);
	}
}

