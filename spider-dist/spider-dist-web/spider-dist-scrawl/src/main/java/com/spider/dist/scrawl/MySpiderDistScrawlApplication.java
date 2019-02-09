package com.spider.dist.scrawl;

import com.spider.dist.facade.api.ISpiderDistScrawlFacade;
import org.springframework.beans.factory.annotation.Autowired;
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

