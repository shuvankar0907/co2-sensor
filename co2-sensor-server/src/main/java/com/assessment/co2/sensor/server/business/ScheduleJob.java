package com.assessment.co2.sensor.server.business;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.assessment.co2.sensor.domain.business.SensorManager;
/**
 * Executes scheduleJob to getGetdailySummarizedDaySensorData
 * @author ghosh
 *
 */
@Component
public class ScheduleJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			SensorManager sensorManage = (SensorManager) context.getJobDetail().getJobDataMap().get("job");
			sensorManage.getDailySummarizedDaySensorData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
