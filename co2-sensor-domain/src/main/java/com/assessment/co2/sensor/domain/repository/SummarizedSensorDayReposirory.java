package com.assessment.co2.sensor.domain.repository;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;

import com.assessment.co2.sensor.domain.model.SummarizedSensorDay;
/**
 * 
 * @author ghosh
 *
 */
public interface SummarizedSensorDayReposirory {

	int saveAllSummarizedSensorDayRepository(List<SummarizedSensorDay> summarizedDaysDatas);

	SummarizedSensorDay getRangeOfdaysSummarizedMetrix(UUID sensorId, DateTime startDate, DateTime endDate);

}