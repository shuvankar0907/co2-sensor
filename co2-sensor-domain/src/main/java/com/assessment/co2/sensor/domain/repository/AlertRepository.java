package com.assessment.co2.sensor.domain.repository;

import java.util.List;
import java.util.UUID;

import com.assessment.co2.sensor.domain.model.Alert;
/**
 * Alert repository
 * @author ghosh
 *
 */
public interface AlertRepository {
	void save(Alert alert)throws Exception;
	List<Alert> getAllAlertsForSensor(UUID id) throws Exception;
	int saveAll(List<Alert> alerts);

}
