package com.assessment.co2.sensor.domain.model;

import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * SummarizedSensorDay collection
 * @author ghosh
 *
 */
@Document
public class SummarizedSensorDay {
	@Id
	UUID id=UUID.randomUUID();
	UUID sensorId;
	DateTime date;
	int max;
	int average;
	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}
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
	 * @return the date
	 */
	public DateTime getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(DateTime date) {
		this.date = date;
	}
	/**
	 * @return the max
	 */
	public int getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(int max) {
		this.max = max;
	}
	/**
	 * @return the average
	 */
	public int getAverage() {
		return average;
	}
	/**
	 * @param average the average to set
	 */
	public void setAverage(int average) {
		this.average = average;
	}
	
}
