package com.assessment.co2.sensor.domain.business;

import com.assessment.co2.sensor.domain.model.Status;

/**
 * This class contains the business logic for setting the sensor status
 * 
 * @author ghosh
 *
 */
public class Rule {

	private final static int PERMITED_CO2_LEVEL = 2000;

	/**
	 * Based on the values of the previous reading current status is calculated
	 * 
	 * @param context
	 * @return
	 */
	public static RuleContext executeRule(RuleContext context) {
		switch (context.getCurrentSensorStatus()) {
		case OK:
			handleCurrentStatusAsOK(context);
			break;
		case WARN:
			handleCurrentStatusAsWARN(context);
			break;
		case ALERT:
			handleCurrentStatusAsALERT(context);
			break;
		}
		return context;
	}

	/**
	 * If the current status is OK and new value is less than or equal to the new
	 * value then set the status to OK. If the current status is OK and the new
	 * value is more than the permitted value set the status to WARN
	 * 
	 * @param context
	 */
	private static void handleCurrentStatusAsOK(RuleContext context) {
		if (context.getNewCo2level() <= PERMITED_CO2_LEVEL)
			context.setStatus(Status.OK);
		else {
			context.setStatus(Status.WARN);
		}

	}

	/**
	 * If the current status is WARN and the new value is less than or equal to
	 * permitted value set the status to OK. 
	 * If the current status is WARN and the
	 * new value is greater than permitted value and any of the previous value is
	 * less than or equal to permitted value set the status to WARN. 
	 * If the current
	 * status is WARN and the new value is greater than permitted value and all of
	 * the previous value is greater than permitted value set the status to ALERT.
	 * 
	 * @param context
	 */
	private static void handleCurrentStatusAsWARN(RuleContext context) {
		if (context.getNewCo2level() <= PERMITED_CO2_LEVEL) {
			context.setStatus(Status.OK);
			return;
		} else {
			int[] previousReadings = context.getPreviousXreadings();
			for (int i = 0; i < previousReadings.length; i++) {
				if (previousReadings[i] <= PERMITED_CO2_LEVEL) {
					context.setStatus(Status.WARN);
					return;
				}
			}
			context.setStatus(Status.ALERT);
			context.setNewAlertStatus(true);
		}
	}

	/**
	 * If the current status is ALERT and the new value is greater than or equal to
	 * permitted value then set the status to ALERT.
	 * If the current status is ALERT
	 * and the new value is less than the permitted value and any of the previous
	 * value is greater than or equal to the permitted value then set the status to
	 * ALERT.
	 * If the current
	 * status is ALERT and the new value is less than permitted value and all of
	 * the previous value is less than permitted value set the status to OK. 
	 * 
	 * @param context
	 */
	private static void handleCurrentStatusAsALERT(RuleContext context) {
		if (context.getNewCo2level() >= PERMITED_CO2_LEVEL) {
			context.setStatus(Status.ALERT);
			context.setNewAlertStatus(true);
		} else {
			int[] previousReadings = context.getPreviousXreadings();
			for (int i = 0; i < previousReadings.length; i++) {
				if (previousReadings[i] >= PERMITED_CO2_LEVEL) {
					context.setStatus(Status.ALERT);
					return;
				}
			}
			context.setStatus(Status.OK);
		}
	}

}
