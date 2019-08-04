package com.assessment.co2.sensor.domain.business;

import java.util.List;
import java.util.UUID;

import com.assessment.co2.sensor.domain.model.Alert;
import com.assessment.co2.sensor.domain.model.Metrics;
import com.assessment.co2.sensor.domain.model.PostProcessSensorData;
import com.assessment.co2.sensor.domain.model.Sensor;
import com.assessment.co2.sensor.domain.model.SensorStatus;
import com.assessment.co2.sensor.domain.model.Status;
/**
 * Interface for Sensor manager
 * @author ghosh
 *
 */
public interface SensorManager {
	void saveAllSensorsData(List<Sensor> sensors,List<Alert> alerts,List<SensorStatus> sensorStatus);
	PostProcessSensorData executeRule(Sensor sensor) throws Exception;
	Status getSensorStatus(UUID uuid) throws Exception;
	Metrics getSensorMetrics(UUID uuid) throws Exception;
	List<Alert> getAllAlerts(UUID uuid) throws Exception;
	void getDailySummarizedDaySensorData();
}
