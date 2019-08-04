//package com.assessment.co2.sensor.domain.repository;
//
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import org.hamcrest.core.IsNull;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.assessment.co2.sensor.domain.model.SensorStatus;
//import com.assessment.co2.sensor.domain.model.Status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
//public class SensorStatusRepositoryImplTest {
//	
//	@Autowired
//	@InjectMocks
//	SensorStatusRepository sensorStatusRepository;
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testSaveAll() {
//		
//		UUID sensorId=UUID.randomUUID();
//		int lastValue=120;
//		int lastMinusOneValue=110;
//		Status status=Status.OK;
//		List<SensorStatus> sensorStatuses=new ArrayList<SensorStatus>();
//		sensorStatuses.add(getSensorStatus(UUID.randomUUID(),180,200,Status.ALERT));
//		sensorStatuses.add(getSensorStatus(UUID.randomUUID(),160,170,Status.OK));
//		sensorStatuses.add(getSensorStatus(UUID.randomUUID(),200,220,Status.ALERT));
//		sensorStatuses.add(getSensorStatus(UUID.randomUUID(),500,180,Status.OK));
//		sensorStatuses.add(getSensorStatus(UUID.randomUUID(),230,220,Status.WARN));
//		sensorStatuses.add(getSensorStatus(sensorId,lastValue,lastMinusOneValue,status));
//		sensorStatusRepository.saveAll(sensorStatuses);
//		
//		SensorStatus recSensorStatus=sensorStatusRepository.getSensorStatus(sensorId);
//		assertEquals(sensorId,recSensorStatus.getSensorId());
//		assertEquals(lastValue,recSensorStatus.getLastValue());
//		assertEquals(lastMinusOneValue,recSensorStatus.getLastMinusOneValue());
//		assertEquals(status,recSensorStatus.getStatus());
//		
//		
//		
//	}
//	
//	@Test
//	public void testSaveAllUpsert() {
//		
//		UUID sensorId=UUID.randomUUID();
//		int lastValue=120;
//		int lastMinusOneValue=110;
//		Status status=Status.OK;
//		List<SensorStatus> sensorStatuses=new ArrayList<SensorStatus>();
//		sensorStatuses.add(getSensorStatus(UUID.randomUUID(),180,200,Status.ALERT));
//		sensorStatuses.add(getSensorStatus(UUID.randomUUID(),160,170,Status.OK));
//		sensorStatuses.add(getSensorStatus(UUID.randomUUID(),200,220,Status.ALERT));
//		sensorStatuses.add(getSensorStatus(UUID.randomUUID(),500,180,Status.OK));
//		sensorStatuses.add(getSensorStatus(UUID.randomUUID(),230,220,Status.WARN));
//		sensorStatuses.add(getSensorStatus(sensorId,150,210,Status.WARN));
//		sensorStatuses.add(getSensorStatus(sensorId,lastValue,lastMinusOneValue,status));
//		sensorStatusRepository.saveAll(sensorStatuses);
//		
//		SensorStatus recSensorStatus=sensorStatusRepository.getSensorStatus(sensorId);
//		assertEquals(sensorId,recSensorStatus.getSensorId());
//		assertEquals(lastValue,recSensorStatus.getLastValue());
//		assertEquals(lastMinusOneValue,recSensorStatus.getLastMinusOneValue());
//		assertEquals(status,recSensorStatus.getStatus());
//		
//	}
//	@Test
//	public void testgetSensorNotAvailable() {
//		SensorStatus recSensorStatus=sensorStatusRepository.getSensorStatus(UUID.randomUUID());
//		
//		assertNull(recSensorStatus);
//	}
//	
//	private SensorStatus getSensorStatus(UUID sensorID,int lastValue,int lastMinusOneValue,Status status) {
//		SensorStatus sensorStatus=new SensorStatus();
//		sensorStatus.setSensorId(sensorID);
//		sensorStatus.setLastValue(lastValue);
//		sensorStatus.setLastMinusOneValue(lastMinusOneValue);
//		sensorStatus.setStatus(status);
//		return sensorStatus;
//	}
//
//}
