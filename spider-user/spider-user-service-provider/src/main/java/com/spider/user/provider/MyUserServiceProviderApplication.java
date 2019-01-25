package com.spider.user.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;


@SpringBootApplication
@ComponentScan(basePackages={"com.spider.user.service.api","com.spider.user.provider.*"})
@ImportResource(locations = {"classpath:dubbo/*.xml"})
public class MyUserServiceProviderApplication {

	public static void main(String[] args) {

		SpringApplication.run(MyUserServiceProviderApplication.class, args);


	}


}

