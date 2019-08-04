package com.assessment.co2.sensor.server.contract;


import java.util.List;
/**
 * Alerts model
 * @author ghosh
 *
 */
public class GetAlerts {
	private String startDate;
	private String endDate;
	private List<Integer> mesurement;
	public List<Integer> getMesurement() {
		return mesurement;
	}
	public void setMesurement(List<Integer> mesurement) {
		this.mesurement = mesurement;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	

}
