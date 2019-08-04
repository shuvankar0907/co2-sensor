///**
// * 
// */
//package com.assessment.co2.sensor.server.business;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.mock;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.assessment.co2.sensor.domain.business.SensorManager;
//import com.assessment.co2.sensor.domain.model.Alert;
//import com.assessment.co2.sensor.domain.model.Metrics;
//import com.assessment.co2.sensor.domain.model.Sensor;
//import com.assessment.co2.sensor.domain.model.Status;
//import com.assessment.co2.sensor.server.contract.GetAlerts;
//import com.assessment.co2.sensor.server.contract.GetSensorMetrics;
//import com.assessment.co2.sensor.server.contract.GetStatus;
//import com.assessment.co2.sensor.server.contract.Mesurment;
//
///**
// * @author ghosh
// *
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
//public class SensorApiLogicTest {
//
//	@InjectMocks
//	SensorApiLogic sensorApiLogic;
//	@Mock
//	SensorManager sensorManager;
//	@Mock
//	RabbitMQManager rabbitMQManager;
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
//	
//	/**
//	 * Test method for {@link com.assessment.co2.sensor.server.business.SensorApiLogic#saveSensorLevel(java.lang.String, com.assessment.co2.sensor.server.contract.Mesurment)}.
//	 */
//	@Test
//	public void testSaveSensorLevel() {
//		String sensorid=UUID.randomUUID().toString();
//		Mesurment measurement=new Mesurment();
//		Sensor sensorMock=mock(Sensor.class);
//		ResponseEntity<Void> res= sensorApiLogic.saveSensorLevel(sensorid, measurement);
//		doNothing().when(rabbitMQManager).sendMsg(sensorMock);
//		assertEquals(HttpStatus.OK,res.getStatusCode());
//		
//	}
//
//	/**
//	 * Test method for {@link com.assessment.co2.sensor.server.business.SensorApiLogic#getSensorStatus(java.lang.String)}.
//	 * @throws Exception 
//	 */
//	@Test
//	public void testGetSensorStatus() throws Exception {
//		UUID sensorId=UUID.randomUUID();
//		Status status=Status.OK;
//		when(sensorManager.getSensorStatus(sensorId)).thenReturn(status);
//		ResponseEntity<GetStatus> res= sensorApiLogic.getSensorStatus(sensorId.toString());
//		assertEquals(HttpStatus.OK,res.getStatusCode());
//		assertEquals("OK",((GetStatus)res.getBody()).getStatus());
//	}
//
//	/**
//	 * Test method for {@link com.assessment.co2.sensor.server.business.SensorApiLogic#getSensorMetrics(java.lang.String)}.
//	 * @throws Exception 
//	 */
//	@Test
//	public void testGetSensorMetrics() throws Exception {
//		UUID sensorId=UUID.randomUUID();
//		Metrics metric=new Metrics();
//		metric.setAvg(2000);
//		when(sensorManager.getSensorMetrics(sensorId)).thenReturn(metric);
//		ResponseEntity<GetSensorMetrics> res= sensorApiLogic.getSensorMetrics(sensorId.toString());
//		assertEquals(HttpStatus.OK,res.getStatusCode());
//		assertEquals(2000,((GetSensorMetrics)res.getBody()).getAvgLast30Days());
//	}
//
//	/**
//	 * Test method for {@link com.assessment.co2.sensor.server.business.SensorApiLogic#getAlerts(java.lang.String)}.
//	 * @throws Exception 
//	 */
//	@Test
//	public void testGetAlerts() throws Exception {
//		UUID sensorId=UUID.randomUUID();
//		List<Alert> alerts=new ArrayList<Alert>();
//		Alert alert=new Alert();
//		alert.setId(sensorId);
//		alert.setLevel(2400);
//		alerts.add(alert);
//		
//		when(sensorManager.getAllAlerts(sensorId)).thenReturn(alerts);
//		ResponseEntity<GetAlerts> res=sensorApiLogic.getAlerts(sensorId.toString());
//		assertEquals(HttpStatus.OK,res.getStatusCode());
//	
//	}
//
//}
