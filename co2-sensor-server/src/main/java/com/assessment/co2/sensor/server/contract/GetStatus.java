package com.assessment.co2.sensor.server.contract;

import com.assessment.co2.sensor.domain.model.Status;
/**
 * Contract status
 * @author ghosh
 *
 */
public class GetStatus {
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status.toString();
	}
}
