package com.spider.job;

import com.spider.base.kafka.api.ISpiderMessageProcessor;
import com.spider.base.kafka.consumer.SpiderKafkaConsumerClient;
import com.spider.job.constant.SpiderTopicGroupContant;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyJob implements Job {
	
	private static Logger logger = LoggerFactory.getLogger(MyJob.class);

//	@Autowired
//	private ISpiderMessageProcessor spiderMessageProcessor;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		MyMessageProcessor myMessageProcessor = new MyMessageProcessor();

		Long startTime = System.currentTimeMillis();
		logger.info("---------我的job运行开始了---------");
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            logger.info("异常 e:{}", ExceptionUtils.getStackTrace(e));
        }
        SpiderKafkaConsumerClient.getInstance().receiveMessages(SpiderTopicGroupContant.SPIDER_DIST_TOPIC, SpiderTopicGroupContant.SPIDER_DIST_GROUP, myMessageProcessor);
		logger.info("---------我的job运行结束了 运行时间：" + (System.currentTimeMillis()-startTime));
	}
}
