package com.assessment.co2.sensor.server.contract;
/**
 * Sensor metrics 
 * @author ghosh
 *
 */
public class GetSensorMetrics {
	private int maxLast30Days;
	private int avgLast30Days;
	public int getMaxLast30Days() {
		return maxLast30Days;
	}
	public void setMaxLast30Days(int maxLast30Days) {
		this.maxLast30Days = maxLast30Days;
	}
	public int getAvgLast30Days() {
		return avgLast30Days;
	}
	public void setAvgLast30Days(int avgLast30Days) {
		this.avgLast30Days = avgLast30Days;
	}

}
