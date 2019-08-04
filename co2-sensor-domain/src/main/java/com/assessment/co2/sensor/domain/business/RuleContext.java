package com.assessment.co2.sensor.domain.business;

import com.assessment.co2.sensor.domain.model.Sensor;
import com.assessment.co2.sensor.domain.model.SensorStatus;
import com.assessment.co2.sensor.domain.model.Status;
import com.assessment.co2.sensor.domain.repository.SensorStatusRepository;

/**
 * This is the helper class for Rule. This class is responsible for all the
 * collecting all the data required for rule execution
 * 
 * @author ghosh
 *
 */
public class RuleContext {
	private SensorStatusRepository sensorStatusRepository;
	private Sensor newSensordata;
	private SensorStatus sensorStatus;
	private final int CONSECUTIVE_RECORD_TO_CHECK = 2;

	public RuleContext(Sensor sensorData, SensorStatusRepository sensorStatusRepository) {
		this.sensorStatusRepository = sensorStatusRepository;
		newSensordata = sensorData;
	}
	/**
	 * Number of previous records required for rule execution
	 * @return
	 */
	public int noOfPreviousRecordToCheck() {
		return CONSECUTIVE_RECORD_TO_CHECK;
	}
	/**
	 * Sets the new calculated status 
	 * @param status
	 */
	public void setStatus(Status status) {
		newSensordata.setStatus(status);
	}
	/**
	 * Get the new sensor object
	 * @return
	 */
	public Sensor getNewSensor() {
		return newSensordata;
	}
	/** 
	 * Set the alert status after the rule execution
	 * @param alertStatus
	 */
	public void setNewAlertStatus(boolean alertStatus) {
		newSensordata.setAlert(alertStatus);
	}
	/**
	 * Current sensor status
	 * @return
	 */
	public Status getCurrentSensorStatus() {
		return getSensorStatus().getStatus();
	}
	/**
	 * Co2 level of the new sensor data
	 * @return
	 */
	public int getNewCo2level() {
		return newSensordata.getLevel();
	}
	/**
	 * Gets the X number of previous records
	 * @return
	 */
	public int[] getPreviousXreadings() {
		int[] sensorlevels = new int[CONSECUTIVE_RECORD_TO_CHECK];
		sensorlevels[0] = getSensorStatus().getLastValue();
		sensorlevels[1] = getSensorStatus().getLastMinusOneValue();
		return sensorlevels;
	}
	/**
	 * Gets the sensor status object after rule execution
	 * @return
	 */
	public SensorStatus getNewSensorStatus() {
		SensorStatus newSensorStatus = new SensorStatus();
		newSensorStatus.setSensorId(newSensordata.getSensorId());
		newSensorStatus.setStatus(newSensordata.getStatus());
		newSensorStatus.setSensorId(newSensordata.getSensorId());
		newSensorStatus.setLastValue(newSensordata.getLevel());
		newSensorStatus.setLastMinusOneValue(getSensorStatus().getLastValue());
		return newSensorStatus;
	}

	private SensorStatus getSensorStatus() {
		if (sensorStatus == null) {
			sensorStatus = sensorStatusRepository.getSensorStatus(newSensordata.getSensorId());
			if (sensorStatus == null) {
				sensorStatus = new SensorStatus();
			}
		}
		return sensorStatus;
	}

}
