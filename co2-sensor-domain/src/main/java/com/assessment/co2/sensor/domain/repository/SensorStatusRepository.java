package com.assessment.co2.sensor.domain.repository;

import java.util.List;
import java.util.UUID;

import com.assessment.co2.sensor.domain.model.SensorStatus;

/**
 * 
 * @author ghosh
 *
 */
public interface SensorStatusRepository {
	int saveAll(List<SensorStatus> sensorStatus) ;
	SensorStatus getSensorStatus(UUID sensorId);
}
