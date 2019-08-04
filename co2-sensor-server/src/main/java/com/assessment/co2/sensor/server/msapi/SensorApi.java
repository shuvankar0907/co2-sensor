package com.assessment.co2.sensor.server.msapi;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.co2.sensor.server.business.SensorApiLogic;
import com.assessment.co2.sensor.server.contract.GetAlerts;
import com.assessment.co2.sensor.server.contract.GetSensorMetrics;
import com.assessment.co2.sensor.server.contract.GetStatus;
import com.assessment.co2.sensor.server.contract.Mesurment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

/**
 * Controller class for sensor api
 * @author ghosh
 *
 */
@RestController
@RequestMapping(path = "/api/v1")
public class SensorApi {
	@Autowired
    @Qualifier("SensorApiLogic")
    SensorApiLogic sensorLogic;
	/**
	 * Save all sensor measurement 
	 * @param uuid
	 * @param measurement
	 * @return
	 */
	@RequestMapping(value = "/sensors/{uuid}/mesurements",
            consumes = {"application/json"},
            method = RequestMethod.POST)
	public ResponseEntity<Void> saveMeasurement(@PathVariable("uuid") String uuid, @RequestBody Mesurment measurement) {
        return sensorLogic.saveSensorLevel(uuid, measurement);
    }
	
	/**
	 * Gets the current status of the sensor
	 * @param uuid
	 * @return
	 */
	@RequestMapping("/sensors/{uuid}")
	public ResponseEntity<GetStatus> getStatus(@PathVariable("uuid") String uuid){
		//String uuid=null;
		System.out.print("Api called");
		return sensorLogic.getSensorStatus(uuid);
	}
	/**
	 * Get the metrics of a sensor
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/sensors/{uuid}/metrics",
            produces = {"application/json"},
            method = RequestMethod.GET)
	public ResponseEntity<GetSensorMetrics> getMetrics(@PathVariable String uuid){
		return sensorLogic.getSensorMetrics(uuid);
	}
	/**
	 * Get all alerts of the sensor
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/sensors/{uuid}/alerts",
            produces = {"application/json"},
            method = RequestMethod.GET)
	public ResponseEntity<GetAlerts>  getAlerts(@PathVariable String uuid){
		return sensorLogic.getAlerts(uuid);
	}

}
