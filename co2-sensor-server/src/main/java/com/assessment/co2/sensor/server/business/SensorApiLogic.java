package com.assessment.co2.sensor.server.business;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.assessment.co2.sensor.domain.business.SensorManager;
import com.assessment.co2.sensor.domain.model.Metrics;
import com.assessment.co2.sensor.domain.model.Sensor;
import com.assessment.co2.sensor.domain.model.Status;
import com.assessment.co2.sensor.server.contract.GetAlerts;
import com.assessment.co2.sensor.server.contract.GetSensorMetrics;
import com.assessment.co2.sensor.server.contract.GetStatus;
import com.assessment.co2.sensor.server.contract.Mesurment;

/**
 * Logic for Sensor Api
 * 
 * @author ghosh
 *
 */
@Component(value = "SensorApiLogic")
public class SensorApiLogic {
	SensorManager sensorManager;
	AsynchoronousMessage rabbitMQManager;

	@Autowired
	public SensorApiLogic(SensorManager sensorManager, RabbitMQManager rabbitMQManager) {
		this.sensorManager = sensorManager;
		this.rabbitMQManager = rabbitMQManager;
	}

	/**
	 * Saves sensor level
	 * 
	 * @param sensorId
	 * @param measurement
	 * @return
	 */
	public ResponseEntity<Void> saveSensorLevel(String sensorId, Mesurment measurement) {
		try {
			Sensor sensor = ContractMapper.convertMeasurementToSensorStatus(measurement);
			UUID uuid = UUID.fromString(sensorId);
			sensor.setSensorId(uuid);
			rabbitMQManager.sendMsg(sensor);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// Log Exception
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Get current sensor status from sensor manager maps to contract status and
	 * sends the response to controller
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<GetStatus> getSensorStatus(String id) {
		try {
			UUID sensorId = UUID.fromString(id);
			Status status = sensorManager.getSensorStatus(sensorId);
			return new ResponseEntity<GetStatus>(ContractMapper.convertStatusToGetStatus(status), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new GetStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Get sensorMetrics from sensormanager and maps model object to contract object
	 * 
	 * @param sensorId
	 * @return
	 */
	public ResponseEntity<GetSensorMetrics> getSensorMetrics(String sensorId) {
		try {
			UUID uuid = UUID.fromString(sensorId);
			Metrics metrics = sensorManager.getSensorMetrics(uuid);
			return new ResponseEntity<GetSensorMetrics>(ContractMapper.convertMerticsToGetSensonMetrics(metrics),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new GetSensorMetrics(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Gets alerts from sensor manager and maps model object to contract
	 * 
	 * @param sensorId
	 * @return
	 */
	public ResponseEntity<GetAlerts> getAlerts(String sensorId) {
		try {
			UUID uuid = UUID.fromString(sensorId);
			return new ResponseEntity<GetAlerts>(
					ContractMapper.convertAlertToGetAlert(sensorManager.getAllAlerts(uuid)), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
