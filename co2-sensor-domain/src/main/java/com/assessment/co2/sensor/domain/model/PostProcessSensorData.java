/**
 * 
 */
package com.assessment.co2.sensor.domain.model;

/**
 * This class stores the composite object of sensor,sensorStatus,alert
 * @author ghosh
 *
 */
public class PostProcessSensorData {
	Sensor sensor;
	SensorStatus sensorStatus;
	Alert alert;
	/**
	 * @return the sensor
	 */
	public Sensor getSensor() {
		return sensor;
	}
	/**
	 * @param sensor the sensor to set
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	/**
	 * @return the sensorStatus
	 */
	public SensorStatus getSensorStatus() {
		return sensorStatus;
	}
	/**
	 * @param sensorStatus the sensorStatus to set
	 */
	public void setSensorStatus(SensorStatus sensorStatus) {
		this.sensorStatus = sensorStatus;
	}
	/**
	 * @return the alert
	 */
	public Alert getAlert() {
		return alert;
	}
	/**
	 * @param alert the alert to set
	 */
	public void setAlert(Alert alert) {
		this.alert = alert;
	}

}
