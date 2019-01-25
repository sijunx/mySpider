package com.spider.user.papp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages={"com.spider.user.papp.*","com.spider.user.service.api"})
@ImportResource(locations = {"classpath:dubbo/*.xml"})
public class MyUserAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyUserAppApplication.class, args);

	}
}

