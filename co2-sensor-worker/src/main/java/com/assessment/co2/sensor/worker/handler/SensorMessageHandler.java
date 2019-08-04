package com.assessment.co2.sensor.worker.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


import com.assessment.co2.sensor.domain.business.SensorManager;
import com.assessment.co2.sensor.domain.model.Alert;
import com.assessment.co2.sensor.domain.model.PostProcessSensorData;
import com.assessment.co2.sensor.domain.model.Sensor;
import com.assessment.co2.sensor.domain.model.SensorStatus;
/**
 * Handles messages from rabbit. Stores the messages in memory till more then one data arrives from the queue.
 * Otherwise it doesn't send ack to rabbit mq
 * It sends the data to manager and sends the ack to rabbit mq
 * @author ghosh
 *
 */
@Component
public class SensorMessageHandler {
	SensorManager sensorManager;
	
	@Autowired
	public SensorMessageHandler(SensorManager sensorManager) {
		this.sensorManager=sensorManager;
	}
	private Set<UUID> sensorIds=new HashSet<UUID>();
	private List<Sensor> sensors=new LinkedList<Sensor>();
	private List<Alert> alerts=new LinkedList<Alert>();
	private List<SensorStatus> sensorStatus=new LinkedList<SensorStatus>();
	/**
	 * Handle msg from rabbitMQ
	 * @param sensor
	 * @return
	 */
	public boolean handleMessage(Sensor sensor) {
		if(sensorIds.add(sensor.getSensorId())) {
			storeInMemeorySensorData(sensor);
			return false;
		}
		else {
			sensorManager.saveAllSensorsData(sensors, alerts,sensorStatus);
			sensors.clear();
			alerts.clear();
			sensorStatus.clear();
			sensorIds.clear();
			sensorIds.add(sensor.getSensorId());
			storeInMemeorySensorData(sensor);
			return true;
		}
	}
	/** 
	 * Execute rule and store the received object in sensor, sensorStatus and alert collection
	 * @param sensor
	 */
	private void storeInMemeorySensorData(Sensor sensor) {
		try {
			PostProcessSensorData postprocessSensorData= sensorManager.executeRule(sensor);
			sensors.add(postprocessSensorData.getSensor());
			sensorStatus.add(postprocessSensorData.getSensorStatus());
			if(postprocessSensorData.getAlert()!=null) {
				alerts.add(postprocessSensorData.getAlert());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
