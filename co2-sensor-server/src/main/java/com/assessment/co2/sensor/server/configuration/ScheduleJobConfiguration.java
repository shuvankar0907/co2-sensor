/**
 * 
 */
package com.assessment.co2.sensor.server.configuration;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.assessment.co2.sensor.domain.business.SensorManager;
import com.assessment.co2.sensor.server.business.ScheduleJob;

/**
 * Configures Schedule job
 * @author ghosh
 *
 */
@PropertySource(value = "classpath:application.properties")
@Component
public class ScheduleJobConfiguration implements InitializingBean {
	
	@Value("${cron.expression}")
	private String cronExpression;
	
	private SensorManager sensorManager;
	
	@Autowired
	public ScheduleJobConfiguration(SensorManager sensorManager) {
		this.sensorManager=sensorManager;
		
	}
	
	private void configureJob() {
		JobDataMap jobdataMap=new JobDataMap();
		jobdataMap.put("job", sensorManager);
		JobDetail job1 = JobBuilder.newJob(ScheduleJob.class)
				.withIdentity("job", "group").setJobData(jobdataMap).build();

		Trigger trigger1 = TriggerBuilder.newTrigger()
				.withIdentity("cronTrigger1", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
				.build();
		
		Scheduler scheduler1=null;
		try {
			scheduler1 = new StdSchedulerFactory().getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			scheduler1.start();
			scheduler1.scheduleJob(job1, trigger1);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		configureJob();
		
	}

}
