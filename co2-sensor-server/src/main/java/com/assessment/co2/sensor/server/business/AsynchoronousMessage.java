package com.assessment.co2.sensor.server.business;

import com.assessment.co2.sensor.domain.model.Sensor;
/**
 * Interface for sensing Asyn message
 * @author ghosh
 *
 */
public interface AsynchoronousMessage {
	void sendMsg(Sensor sensor);

}
