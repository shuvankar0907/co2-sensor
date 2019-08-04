///**
// * 
// */
//package com.assessment.co2.sensor.worker.handler;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
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
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.assessment.co2.sensor.domain.business.SensorManager;
//import com.assessment.co2.sensor.domain.model.PostProcessSensorData;
//import com.assessment.co2.sensor.domain.model.Sensor;
//import com.assessment.co2.sensor.domain.model.Status;
//
//
///**
// * @author ghosh
// *
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
////@SpringBootTest(classes = { TestConfig.class })
//public class SensorMessageHandlerTest {
//
//	@InjectMocks
//	SensorMessageHandler sensorMessageHandle;
//	@Mock
//	SensorManager sensorManager;
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
//	 * Test method for {@link com.assessment.co2.sensor.worker.handler.SensorMessageHandler#handleMessage(com.assessment.co2.sensor.domain.model.Sensor)}.
//	 * @throws Exception 
//	 */
//	@Test
//	public void testHandleMessageUniquesensorDataArrived() throws Exception {
//		Sensor sensor = getSensorObkj(UUID.randomUUID());
//		PostProcessSensorData postprocessSensorData=new PostProcessSensorData();
//		when(sensorManager.executeRule(sensor)).thenReturn(postprocessSensorData);
//		boolean res=sensorMessageHandle.handleMessage(sensor);
//		assertFalse(res);
//		//fail("Not yet implemented");
//	}
//	
//	@Test
//	public void testHandleMessageDuplicatesensorDataArrived() throws Exception {
//		UUID sensorId=UUID.randomUUID();
//		Sensor sensor = getSensorObkj(sensorId);
//		Sensor sensor1 = getSensorObkj(sensorId);
//		PostProcessSensorData postprocessSensorData=new PostProcessSensorData();
//		when(sensorManager.executeRule(sensor)).thenReturn(postprocessSensorData);
//		when(sensorManager.executeRule(sensor1)).thenReturn(postprocessSensorData);
//		
//		sensorMessageHandle.handleMessage(sensor);
//		boolean res=sensorMessageHandle.handleMessage(sensor1);
//		assertTrue(res);
//	}
//	/**
//	 * @return
//	 */
//	private Sensor getSensorObkj(UUID uuid) {
//		DateTime date=LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		Sensor sensor=new Sensor();
//		sensor.setSensorId(uuid);
//		sensor.setId(UUID.randomUUID());
//		sensor.setLevel(230);
//		sensor.setStatus(Status.WARN);
//		sensor.setRecordingDateTime(date);
//		return sensor;
//	}
//	
//	
//
//}
