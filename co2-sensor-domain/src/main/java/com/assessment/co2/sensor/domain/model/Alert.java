package com.assessment.co2.sensor.domain.model;
/**
 * Alert collection object
 */
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "status")
public class Alert {
	@Id
	private UUID id;
	@Indexed
	private UUID sensorId;
	private Status status;
	private int level;
	private DateTime recoredDate;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getSensorId() {
		return sensorId;
	}
	public void setSensorId(UUID id) {
		this.sensorId = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public DateTime getRecoredDate() {
		return recoredDate;
	}
	public void setRecoredDate(DateTime recoredDate) {
		this.recoredDate = recoredDate;
	}

}
