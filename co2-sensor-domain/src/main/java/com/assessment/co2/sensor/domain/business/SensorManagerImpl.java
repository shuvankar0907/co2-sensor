package com.assessment.co2.sensor.domain.business;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assessment.co2.sensor.domain.model.Alert;
import com.assessment.co2.sensor.domain.model.Metrics;
import com.assessment.co2.sensor.domain.model.PostProcessSensorData;
import com.assessment.co2.sensor.domain.model.Sensor;
import com.assessment.co2.sensor.domain.model.SensorStatus;
import com.assessment.co2.sensor.domain.model.Status;
import com.assessment.co2.sensor.domain.model.SummarizedSensorDay;
import com.assessment.co2.sensor.domain.repository.AlertRepository;
import com.assessment.co2.sensor.domain.repository.SensorRepository;
import com.assessment.co2.sensor.domain.repository.SensorStatusRepository;
import com.assessment.co2.sensor.domain.repository.SummarizedSensorDayReposirory;

/**
 * This is the manager class for all sensor data.
 * 
 * @author ghosh
 *
 */
@Component("SensorManagerImpl")
public class SensorManagerImpl implements SensorManager {
	private AlertRepository alertRepository;
	private SensorRepository sensorRepository;
	private SummarizedSensorDayReposirory summarizedSensorDayRepository;
	private SensorStatusRepository sensorStatusRepository;
	/**
	 * Constructor for injecting the dependencies
	 * @param alertRepository
	 * @param sensorRepository
	 * @param summarizedSensorDayRepository
	 * @param sensorStatusRepository
	 */
	@Autowired
	public SensorManagerImpl(AlertRepository alertRepository, SensorRepository sensorRepository,
			SummarizedSensorDayReposirory summarizedSensorDayRepository,
			SensorStatusRepository sensorStatusRepository) {
		this.alertRepository = alertRepository;
		this.sensorRepository = sensorRepository;
		this.summarizedSensorDayRepository = summarizedSensorDayRepository;
		this.sensorStatusRepository = sensorStatusRepository;
	}
	/**
	 * Method to save all sensor data, alert data, sensorStatus data
	 */
	@Override
	public void saveAllSensorsData(List<Sensor> sensors, List<Alert> alerts, List<SensorStatus> sensorStatus) {
		if (sensors != null && sensors.size() > 0) {
			saveAllSensors(sensors);
		}
		if (alerts != null && alerts.size() > 0) {
			saveAllAlerts(alerts);
		}
		if (sensorStatus != null && sensorStatus.size() > 0) {
			SaveAllSensorStatus(sensorStatus);
		}

	}
	/**
	 * Executes rule and returns the objects required to be stored after rule execution 
	 */
	public PostProcessSensorData executeRule(Sensor sensorloc) {
		PostProcessSensorData postProcessSensorData = new PostProcessSensorData();
		sensorloc.setId(UUID.randomUUID());
		RuleContext ruleContext = new RuleContext(sensorloc, sensorStatusRepository);
		Rule.executeRule(ruleContext);
		postProcessSensorData.setSensor(ruleContext.getNewSensor());
		postProcessSensorData.setAlert(getAlert(ruleContext.getNewSensor()));
		postProcessSensorData.setSensorStatus(ruleContext.getNewSensorStatus());

		return postProcessSensorData;
	}

	/**
	 * This method returns current status of the sensor
	 */
	public Status getSensorStatus(UUID sensorUUId) throws Exception {
		return sensorStatusRepository.getSensorStatus(sensorUUId).getStatus();
	}

	/**
	 * This method gets 30 days metrics for a sensor. It calculates 30days by
	 * today's date+1 (start of the day)minus 30
	 */
	public Metrics getSensorMetrics(UUID sensorId) throws Exception {
		DateTime endDate = LocalDate.now().plusDays(1).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
		DateTime startDate = LocalDate.now().minusDays(30).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
		SummarizedSensorDay summarizedDays = summarizedSensorDayRepository.getRangeOfdaysSummarizedMetrix(sensorId,
				startDate, endDate);
		Metrics metrics = new Metrics();
		metrics.setAvg(summarizedDays.getAverage());
		metrics.setMax(summarizedDays.getMax());
		return metrics;
	}
	/**
	 * Returns all the alerts
	 */
	public List<Alert> getAllAlerts(UUID sensorId) throws Exception {
		return alertRepository.getAllAlertsForSensor(sensorId);

	}
	/**
	 * Gets yesterday's summarised data from sensor and saves it in SummarizedSensorDay collection.
	 * It also deletes the same range of data from sensor collection
	 */
	public void getDailySummarizedDaySensorData() {
		DateTime todaysStartDate = LocalDate.now().minusDays(1).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
		DateTime todaysEndDate = LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault());

		List<SummarizedSensorDay> summarizedDaysDatas = sensorRepository.getAggregatedData(todaysStartDate,
				todaysEndDate);
		if (summarizedDaysDatas != null && summarizedDaysDatas.size() > 0) {
			summarizedSensorDayRepository.saveAllSummarizedSensorDayRepository(summarizedDaysDatas);
			sensorRepository.delete(todaysStartDate, todaysEndDate);
		}
	}

	private Alert getAlert(Sensor sensor) {
		if (!sensor.isAlert())
			return null;

		Alert alert = new Alert();
		alert.setSensorId(sensor.getSensorId());
		alert.setLevel(sensor.getLevel());
		alert.setRecoredDate(sensor.getRecordingDateTime());
		alert.setStatus(Status.ALERT);
		alert.setId(sensor.getId());
		return alert;
	}

	private void saveAllSensors(List<Sensor> sensors) {
		sensorRepository.saveAll(sensors);
		// System.out.print("inserted count-" + count);
	}

	private void saveAllAlerts(List<Alert> alerts) {
		alertRepository.saveAll(alerts);
	}

	private void SaveAllSensorStatus(List<SensorStatus> sensorStatus) {
		sensorStatusRepository.saveAll(sensorStatus);
	}

}
