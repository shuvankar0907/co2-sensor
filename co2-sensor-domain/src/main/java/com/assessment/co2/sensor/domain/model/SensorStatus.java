/**
 * 
 */
package com.assessment.co2.sensor.domain.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

/**
 * Sensor status sollection
 * @author ghosh
 *
 */
public class SensorStatus {
	@Id
	private UUID sensorId;
	private Status status=Status.OK;
	private int lastValue=-1;
	private int lastMinusOneValue=-1;
	/**
	 * @return the sensorId
	 */
	public UUID getSensorId() {
		return sensorId;
	}
	/**
	 * @param sensorId the sensorId to set
	 */
	public void setSensorId(UUID sensorId) {
		this.sensorId = sensorId;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * @return the lastValue
	 */
	public int getLastValue() {
		return lastValue;
	}
	/**
	 * @param lastValue the lastValue to set
	 */
	public void setLastValue(int lastValue) {
		this.lastValue = lastValue;
	}
	/**
	 * @return the lastMinusOneValue
	 */
	public int getLastMinusOneValue() {
		return lastMinusOneValue;
	}
	/**
	 * @param lastMinusOneValue the lastMinusOneValue to set
	 */
	public void setLastMinusOneValue(int lastMinusOneValue) {
		this.lastMinusOneValue = lastMinusOneValue;
	}
	

}
