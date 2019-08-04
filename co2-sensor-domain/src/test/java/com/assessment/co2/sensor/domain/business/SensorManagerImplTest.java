package com.assessment.co2.sensor.domain.business;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assessment.co2.sensor.domain.model.Alert;
import com.assessment.co2.sensor.domain.model.Sensor;
import com.assessment.co2.sensor.domain.model.SensorStatus;
import com.assessment.co2.sensor.domain.repository.AlertRepository;
import com.assessment.co2.sensor.domain.repository.SensorRepository;
import com.assessment.co2.sensor.domain.repository.SensorStatusRepository;
import com.assessment.co2.sensor.domain.repository.SummarizedSensorDayReposirory;
import com.assessment.co2.sensor.domain.repository.TestConfig;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class SensorManagerImplTest {

	@Mock
	AlertRepository alertRepository;
	@Mock
	SensorRepository sensorRepository;
	@Mock
	SummarizedSensorDayReposirory summarizedDayRepo;
	
	@Mock
	SensorStatusRepository sensorStatusRepository;
	
	@InjectMocks
	SensorManagerImpl sensorManagerImpl;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveAllSensorsData() {
		List<Sensor> sensors=new ArrayList<Sensor>();
		sensors.add(new Sensor());
		List<Alert> alerts=new ArrayList<Alert>();
		alerts.add(new Alert());
		List<SensorStatus> sensorStatus=new ArrayList<SensorStatus>();
		sensorStatus.add(new SensorStatus());
		
		sensorManagerImpl.saveAllSensorsData(sensors, alerts,sensorStatus);
		
		verify(sensorRepository,times(1)).saveAll(sensors);
		verify(alertRepository,times(1)).saveAll(alerts);
		verify(sensorStatusRepository,times(1)).saveAll(sensorStatus);
	}
	@Test
	public void testSaveAllSensorsDataOnlySensorDataAvailable() {
		List<Sensor> sensors=new ArrayList<Sensor>();
		sensors.add(new Sensor());
		List<Alert> alerts=new ArrayList<Alert>();
		List<SensorStatus> sensorStatus=new ArrayList<SensorStatus>();
		
		sensorManagerImpl.saveAllSensorsData(sensors, alerts,sensorStatus);
		
		verify(sensorRepository,times(1)).saveAll(sensors);
		verify(alertRepository,times(0)).saveAll(alerts);
		verify(sensorStatusRepository,times(0)).saveAll(sensorStatus);
	}
	@Test
	public void testSaveAllSensorsDataOnlyAlertsAvailable() {
		List<Sensor> sensors=new ArrayList<Sensor>();
		List<Alert> alerts=new ArrayList<Alert>();
		alerts.add(new Alert());
		List<SensorStatus> sensorStatus=new ArrayList<SensorStatus>();
		sensorStatus.add(new SensorStatus());
		
		sensorManagerImpl.saveAllSensorsData(sensors, alerts,sensorStatus);
		
		verify(sensorRepository,times(0)).saveAll(sensors);
		verify(alertRepository,times(1)).saveAll(alerts);
		verify(sensorStatusRepository,times(1)).saveAll(sensorStatus);
	}

	
	@Test
	public void testGetSensorStatus() throws Exception {
		UUID sensorUUID=UUID.randomUUID();
		SensorStatus status=new SensorStatus();
		when(sensorStatusRepository.getSensorStatus(sensorUUID)).thenReturn(status);
		sensorManagerImpl.getSensorStatus(sensorUUID);
		verify(sensorStatusRepository,times(1)).getSensorStatus(sensorUUID);
	}

//	@Test
//	public void testGetSensorMetrics() throws Exception {
//		UUID sensorId=UUID.randomUUID();
//		sensorManagerImpl.getSensorMetrics(sensorId);
//	}

//	@Test
//	public void testGetAllAlerts() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetDailySummarizedDaySensorData() {
//		fail("Not yet implemented");
//	}

}
