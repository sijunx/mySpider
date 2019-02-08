package com.spider.job;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.base.kafka.consumer.SpiderKafkaConsumerClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MyJob implements Job {
	
	private static Logger logger = LoggerFactory.getLogger(MyJob.class);

	@Autowired
	private ISpiderMessageProcessor spiderMessageProcessor;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Long startTime = System.currentTimeMillis();
		logger.info("---------我的job运行开始了---------");
		SpiderKafkaConsumerClient.getInstance().receiveMessages("myTopic", "myGroup", spiderMessageProcessor);
		logger.info("---------我的job运行结束了 运行时间：" + (System.currentTimeMillis()-startTime));
	}
}
