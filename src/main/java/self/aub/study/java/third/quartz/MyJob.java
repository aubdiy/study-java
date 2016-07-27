package self.aub.study.java.third.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("===========job begin=========");
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("MyJob:" + jobKey + " executing at " + new Date());
		System.out.println("===========job   end=========");
	}

}
