package com.spider.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MyQuartz {

	private static Logger logger = LoggerFactory.getLogger(MyQuartz.class);
	
	public static void main(String[] args) throws SchedulerException {
		//	创建Scheduler的工厂
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		//	从工厂中获取调度器实例
		Scheduler scheduler = schedulerFactory.getScheduler();
		//	创建JobDetail
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withDescription("我的job描述") //job的描述
				.withIdentity("job01", "group01") //job 的name和group
				.build();
		//	任务运行的时间
		long time=  System.currentTimeMillis() + 3*1000L; //3秒后启动任务
		Date statTime = new Date(time);
		//	创建Trigger CronScheduleBuilder指定job触发时间
		Trigger trigger = TriggerBuilder.newTrigger()
					.withDescription("")
					.withIdentity("triggerName01", "triggerGroup01")
					//.withSchedule(SimpleScheduleBuilder.simpleSchedule())
					.startAt(statTime)  //默认当前时间启动
					.withSchedule(CronScheduleBuilder.cronSchedule("10 * * * * ? ")) //每5分钟执行一次
					.build();
		//	注册任务和定时器
		scheduler.scheduleJob(jobDetail, trigger);
		//	启动调度器
		scheduler.start();
		logger.info("启动时间：" + new Date());
	}
}
