package com.assessment.co2.sensor.server.contract;
/**
 * Contract measurements
 * @author ghosh
 *
 */
public class Mesurment {
	private int co2;
	private String time;
	public int getCo2() {
		return co2;
	}
	public void setCo2(int co2) {
		this.co2 = co2;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
