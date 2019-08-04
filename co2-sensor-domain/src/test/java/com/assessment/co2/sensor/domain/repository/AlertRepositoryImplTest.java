///**
// * 
// */
//package com.assessment.co2.sensor.domain.repository;
//
//import static org.junit.Assert.*;
//
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeZone;
//import org.joda.time.LocalDate;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.assessment.co2.sensor.domain.Application;
//import com.assessment.co2.sensor.domain.model.Alert;
//import com.assessment.co2.sensor.domain.model.Status;
//import com.assessment.co2.sensor.domain.repository.AlertRepositoryImpl;
//
//
///**
// * @author ghosh
// *
// */
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(classes = TestConfig.class)
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
//public class AlertRepositoryImplTest{
//	@Autowired
//	@InjectMocks
//	AlertRepositoryImpl alertRepository;
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.assessment.co2.sensor.domain.repository.AlertRepositoryImpl#save(com.assessment.co2.sensor.domain.domain.model.Alert)}.
//	 */
//	@Test
//	public void testSave() {
//		
//		final UUID sensorId=UUID.randomUUID();
//		
//		DateTime recordedDate=LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		
//		Alert alert = getAlertObj(sensorId,3000,recordedDate,Status.ALERT);
//		try {
//			alertRepository.save(alert);
//			List<Alert> alerts= alertRepository.getAllAlertsForSensor(sensorId);
//			Optional<Alert> dbAlert= alerts.stream().filter(a->a.getSensorId().equals(sensorId)).findAny();
//			
//			assertEquals(true,dbAlert.isPresent());
//			assertEquals(dbAlert.get().getSensorId(), sensorId);
//			assertEquals(dbAlert.get().getLevel(), 3000);
//			assertEquals(dbAlert.get().getRecoredDate(), recordedDate);
//			assertEquals(dbAlert.get().getStatus(), Status.ALERT);
//			
//		} catch (Exception e) {
//			fail("Exception thrown");
//		}
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.assessment.co2.sensor.domain.repository.AlertRepositoryImpl#getAllAlertsForSensor(java.util.UUID)}.
//	 */
//	@Test
//	public void testGetAllAlertsForSensor() {
//		final UUID sensorId=UUID.randomUUID();
//		DateTime date1 = LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		DateTime date2 = LocalDate.now().minusDays(1).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		DateTime date3 = LocalDate.now().minusDays(2).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		DateTime date4 = LocalDate.now().minusDays(3).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		
//		try {
//			alertRepository.save(getAlertObj(sensorId, 2500, date1, Status.ALERT));
//			alertRepository.save(getAlertObj(sensorId, 2200, date2, Status.ALERT));
//			alertRepository.save(getAlertObj(sensorId, 2700, date3, Status.ALERT));
//			alertRepository.save(getAlertObj(sensorId, 3000, date4, Status.ALERT));
//			
//			final UUID sensorIdNext=UUID.randomUUID();
//			
//			alertRepository.save(getAlertObj(sensorIdNext, 2500, LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault()), Status.ALERT));
//			alertRepository.save(getAlertObj(sensorIdNext, 2800, LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault()), Status.ALERT));
//			
//			List<Alert> alerts=alertRepository.getAllAlertsForSensor(sensorId);
//			
//			assertEquals(4, alerts.size());
//			assertEquals(alerts.get(0).getSensorId(), sensorId);
//			assertEquals(alerts.get(0).getLevel(), 3000);
//			
//		} catch (Exception e) {
//			fail("Exception thrown");
//		}
//		
//	}
//	
//	private Alert getAlertObj(UUID id,int level,DateTime date, Status status) {
//		Alert alert=new Alert();
//		alert.setId(UUID.randomUUID());
//		alert.setSensorId(id);
//		alert.setLevel(level);
//		alert.setRecoredDate(date);
//		alert.setStatus(status);
//		return alert;
//	}
//
//}
