package self.aub.study.java.third.quartz;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobRunner {

	public static void main(String[] args) throws Exception {
		// 调度器实例
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		// 任务实例
		JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();

		// 触发器实例
		CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?")).build();

		// 注册并进行调度
		Date ft = sched.scheduleJob(job, trigger);

		System.out.println("==" + job.getKey() + " has been scheduled to run at: " + ft
				+ " and repeat based on expression: " + trigger.getCronExpression());

		// 启动调度
		sched.start();
		try {
			Thread.sleep(300000L);
		} catch (Exception e) {
		}
		// 关闭调度
		sched.shutdown(true);

	}

}
