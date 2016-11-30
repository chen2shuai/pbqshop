package com.Quartz.test;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
	/**
	 * Scheduler：调度器。所有的调度都是由它控制。 Trigger：
	 * 定义触发的条件。例子中，它的类型是SimpleTrigger，每隔1秒中执行一次（什么是SimpleTrigger下面会有详述）。
	 * JobDetail & Job： JobDetail 定义的是任务数据，而真正的执行逻辑是在Job中，例子中是HelloQuartz。
	 * 为什么设计成JobDetail +
	 * Job，不直接使用Job？这是因为任务是有可能并发执行，如果Scheduler直接使用Job，就会存在对同一个Job实例并发访问的问题
	 * 。而JobDetail & Job
	 * 方式，sheduler每次执行，都会根据JobDetail创建一个新的Job实例，这样就可以规避并发访问的问题。
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			// 创建Scheduler(调度器)
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			// 定义一个Trigger（定义触发的条件）
			Trigger trigger = newTrigger().withIdentity("trigger1", "group1") // 定义name/group
					.startNow()// 一旦加入scheduler，立即生效
					.withSchedule(simpleSchedule() // 使用SimpleTrigger
							.withIntervalInSeconds(1) // 每隔一秒执行一次
							.repeatForever()) // 一直执行，奔腾到老不停歇
					.build();

			// 定义一个JobDetail
			JobDetail job = newJob(HelloQuartz.class) // 定义Job类为HelloQuartz类，这是真正的执行逻辑所在
					.withIdentity("job1", "group1") // 定义name/group
					.usingJobData("name", "quartz") // 定义属性
					.build();

			// 加入这个调度
			scheduler.scheduleJob(job, trigger);

			// 启动之
			scheduler.start();

			// 运行一段时间后关闭
			Thread.sleep(10000);
			scheduler.shutdown(true);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
