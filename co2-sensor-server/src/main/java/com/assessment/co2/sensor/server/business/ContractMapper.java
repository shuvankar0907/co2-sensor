package com.assessment.co2.sensor.server.business;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.assessment.co2.sensor.domain.model.Alert;
import com.assessment.co2.sensor.domain.model.Metrics;
import com.assessment.co2.sensor.domain.model.Sensor;
import com.assessment.co2.sensor.domain.model.Status;
import com.assessment.co2.sensor.server.contract.GetAlerts;
import com.assessment.co2.sensor.server.contract.GetSensorMetrics;
import com.assessment.co2.sensor.server.contract.GetStatus;
import com.assessment.co2.sensor.server.contract.Mesurment;
/**
 * This Class maps the domain model to contract model
 * @author ghosh
 *
 */
public class ContractMapper {
	/**
	 * Maps Domain Metrics to getSensorMetrics
	 * @param metrics
	 * @return
	 */
	public static GetSensorMetrics convertMerticsToGetSensonMetrics(Metrics metrics) {
		GetSensorMetrics getSensorMetrics=new GetSensorMetrics();
		getSensorMetrics.setAvgLast30Days(metrics.getAvg());
		getSensorMetrics.setMaxLast30Days(metrics.getMax());
		return getSensorMetrics;
	}
	/**
	 * Maps domain alerts to GetAlerts
	 * @param alerts
	 * @return
	 */
	public static GetAlerts convertAlertToGetAlert(List<Alert> alerts) {
		GetAlerts getAlerts=new GetAlerts();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
		
		getAlerts.setStartDate(fmt.print(alerts.get(0).getRecoredDate()));
		getAlerts.setEndDate(fmt.print(alerts.get(alerts.size()-1).getRecoredDate()));
		
		List<Integer> measurements=new LinkedList<Integer>();
		for(Alert alert: alerts) {
			measurements.add(alert.getLevel());
		}
		getAlerts.setMesurement(measurements);
		return getAlerts;
	}
	/**
	 * Convert domain status to getStatus
	 * @param status
	 * @return
	 */
	public static GetStatus convertStatusToGetStatus(Status status) {
		GetStatus getStatus=new GetStatus();
		getStatus.setStatus(status);
		return getStatus;
	}
	/**
	 * Convert measurement to Sensor
	 * @param measurement
	 * @return
	 */
	public static Sensor convertMeasurementToSensorStatus(Mesurment measurement) {
		 Sensor sensor=new Sensor();
		 sensor.setTime(measurement.getTime());
		 sensor.setLevel(measurement.getCo2());
		 return sensor;
	}
}
