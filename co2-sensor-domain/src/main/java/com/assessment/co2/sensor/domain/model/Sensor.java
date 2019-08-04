package com.assessment.co2.sensor.domain.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Sensor Collection object
 * @author ghosh
 *
 */
@Document(collection = "sensor")
public class Sensor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private UUID id;
	private UUID sensorId;
	private int level;
	//private Date recordingTime;
	private DateTime recordingDateTime;
	private Status status;
	private boolean isAlert;
	@Transient
	private String time;
	
	
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the recordingDateTime
	 */
	public DateTime getRecordingDateTime() {
		return recordingDateTime;
	}
	/**
	 * @param recordingDateTime the recordingDateTime to set
	 */
	public void setRecordingDateTime(DateTime recordingDateTime) {
		this.recordingDateTime = recordingDateTime;
	}
	public boolean isAlert() {
		return isAlert;
	}
	public void setAlert(boolean isAlert) {
		this.isAlert = isAlert;
	}
	public UUID getSensorId() {
		return sensorId;
	}
	public void setSensorId(UUID sensorId) {
		this.sensorId = sensorId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
//	public Date getRecordingTime() {
//		return recordingTime;
//	}
//	public void setRecordingTime(Date recordingTime) {
//		this.recordingTime = recordingTime;
//	}
	
	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream(bos);
	      oos.writeObject(this);
	      oos.flush();
	      return bos.toByteArray();
	}
	

}
