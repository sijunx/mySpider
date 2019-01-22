package com.spider.scrawl.job;

import com.spider.scrawl.job.service.MyScrawlJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.spider.*")
public class MyScrawlJob01Application {

	public static void main(String[] args) {
		SpringApplication.run(MyScrawlJob01Application.class, args);
		MyScrawlJob myScrawlJob = new MyScrawlJob();
		myScrawlJob.excuteMessageProcessing();
	}
}

