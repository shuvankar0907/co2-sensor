///**
// * 
// */
//package com.assessment.co2.sensor.domain.repository;
//
//import static org.junit.Assert.*;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeZone;
//import org.joda.time.LocalDate;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.internal.matchers.GreaterThan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.assessment.co2.sensor.domain.model.Sensor;
//import com.assessment.co2.sensor.domain.model.Status;
//import com.assessment.co2.sensor.domain.model.SummarizedSensorDay;
//import com.assessment.co2.sensor.domain.repository.SensorRepositoryImpl;
//
///**
// * @author ghosh
// *
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
//public class SensorRepositoryImplTest {
//
//	@Autowired
//	@InjectMocks
//	SensorRepositoryImpl sensorRepository;
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.assessment.co2.sensor.domain.repository.SensorRepositoryImpl#saveLevel(com.assessment.co2.sensor.domain.domain.model.Sensor)}.
//	 */
//	@Test
//	public void testSaveLevel() {
//		UUID sensorId = UUID.randomUUID();
//		DateTime date = LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		Sensor sensor = getSensorObj(sensorId, 1500, Status.OK, date);
//
//		sensorRepository.saveLevel(sensor);
//		List<Sensor> sensors = sensorRepository.getPreviousXLevels(sensorId, 1);
//		assertEquals(1, sensors.size());
//		assertEquals(sensorId, sensors.get(0).getSensorId());
//		assertEquals(1500, sensors.get(0).getLevel());
//		assertEquals(Status.OK, sensors.get(0).getStatus());
//		assertEquals(date, sensors.get(0).getRecordingDateTime());
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.assessment.co2.sensor.domain.repository.SensorRepositoryImpl#getStatus(java.util.UUID)}.
//	 */
//	@Test
//	public void testGetStatusOK() {
//		UUID sensorId = UUID.randomUUID();
//		Sensor sensor = getSensorObj(sensorId, 1500, Status.OK,
//				LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault()));
//		sensorRepository.saveLevel(sensor);
//
//		try {
//			assertEquals(Status.OK, sensorRepository.getStatus(sensorId));
//		} catch (Exception e) {
//			fail("Exception thrown");
//		}
//	}
//
//	@Test
//	public void testGetStatusWARN() {
//		UUID sensorId = UUID.randomUUID();
//		Sensor sensor = getSensorObj(sensorId, 2100, Status.WARN,
//				LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault()));
//		sensorRepository.saveLevel(sensor);
//
//		try {
//			assertEquals(Status.WARN, sensorRepository.getStatus(sensorId));
//		} catch (Exception e) {
//			fail("Exception thrown");
//		}
//	}
//
//	@Test
//	public void testGetStatusALERT() {
//		UUID sensorId = UUID.randomUUID();
//		Sensor sensor = getSensorObj(sensorId, 2200, Status.ALERT,
//				LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault()));
//		sensorRepository.saveLevel(sensor);
//		try {
//			assertEquals(Status.ALERT, sensorRepository.getStatus(sensorId));
//		} catch (Exception e) {
//			fail("Exception thrown");
//		}
//	}
//
//	@Test
//	public void testGetStatusInvalidSensorID() {
//		UUID sensorId = UUID.randomUUID();
//		try {
//			sensorRepository.getStatus(sensorId);
//			fail("Expected exception but no exception is thrown ");
//		} catch (Exception e) {
//			assertEquals("No data found", e.getMessage());
//		}
//	}
//
//
//	/**
//	 * Test method for
//	 * {@link com.assessment.co2.sensor.domain.repository.SensorRepositoryImpl#getPreviousXLevels(java.util.UUID, int)}.
//	 */
//	@Test
//	public void testGetPreviousXLevels() {
//		UUID sensorId = UUID.randomUUID();
//		DateTime date=LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		Sensor sensor1 = getSensorObj(sensorId, 1800, Status.OK,
//				date);
//		Sensor sensor2 = getSensorObj(sensorId, 1700, Status.OK,
//				date);
//		Sensor sensor3 = getSensorObj(sensorId, 1200, Status.OK,
//				date);
//		Sensor sensor4 = getSensorObj(sensorId, 1100, Status.OK,
//				date);
//		Sensor sensor5 = getSensorObj(sensorId, 1900, Status.OK,
//				date);
//
//		sensorRepository.saveLevel(sensor1);
//		sensorRepository.saveLevel(sensor2);
//		sensorRepository.saveLevel(sensor3);
//		sensorRepository.saveLevel(sensor4);
//		sensorRepository.saveLevel(sensor5);
//
//		List<Sensor> sensors = sensorRepository.getPreviousXLevels(sensorId, 3);
//
//		assertEquals(3, sensors.size());
//		assertEquals(1800, sensors.get(0).getLevel());
//		assertEquals(1700, sensors.get(1).getLevel());
//		assertEquals(1200, sensors.get(2).getLevel());
//
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.assessment.co2.sensor.domain.repository.SensorRepositoryImpl#getPreviousXLevels(java.util.UUID, int)}.
//	 */
//	@Test
//	public void testGetPreviousXLevelsWhenNoDataPresent() {
//		UUID sensorId = UUID.randomUUID();
//		List<Sensor> sensors = sensorRepository.getPreviousXLevels(sensorId, 3);
//		assertEquals(0, sensors.size());
//	}
//
//	@Test
//	public void testSaveAll() {
//		List<Sensor> sensors = new ArrayList<Sensor>();
//		DateTime date=LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		sensors.add(getSensorObj(UUID.randomUUID(), 150, Status.OK,date));
//		sensors.add(getSensorObj(UUID.randomUUID(), 160, Status.OK,date));
//		sensors.add(getSensorObj(UUID.randomUUID(), 170, Status.OK,date));
//		sensorRepository.saveAll(sensors);
//	}
//
//
//	/**
//	 * Test method for
//	 * {@link com.assessment.co2.sensor.domain.repository.SensorRepositoryImpl#getPreviousXLevels(java.util.UUID, int)}.
//	 */
//	@Test
//	public void testGetPreviousXLevelsWithInvalidNoOFDays() {
//		UUID sensorId = UUID.randomUUID();
//		List<Sensor> sensors = sensorRepository.getPreviousXLevels(sensorId, -3);
//		assertEquals(0, sensors.size());
//	}
//	
//	@Test
//	public void testgetAggregatedData() throws ParseException {
//		List<Sensor> sensors = new ArrayList<Sensor>();
//		DateTime date=LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		sensors.add(getSensorObj(UUID.randomUUID(), 150, Status.OK,date));
//		sensors.add(getSensorObj(UUID.randomUUID(), 160, Status.OK,date));
//		sensors.add(getSensorObj(UUID.randomUUID(), 170, Status.OK,date));
//		sensorRepository.saveAll(sensors);
//		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//		 DateTime startdate=date;
//		
//		 DateTime enddate=LocalDate.now().plusDays(1).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		 
//		 List<SummarizedSensorDay> list=sensorRepository.getAggregatedData(startdate, enddate);
//		 
//		 assertTrue(list.size()>0);
//		 
//		
//	}
//
//	private Sensor getSensorObj(UUID sensorId, int level, Status status, DateTime recordedDate) {
//		Sensor sensor = new Sensor();
//		sensor.setId(UUID.randomUUID());
//		sensor.setSensorId(sensorId);
//		sensor.setLevel(level);
//		sensor.setRecordingDateTime(recordedDate);
//		sensor.setStatus(status);
//		return sensor;
//	}
//
//	
//
//}
