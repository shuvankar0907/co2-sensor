//package com.assessment.co2.sensor.domain.repository;
//
//import static org.junit.Assert.*;
//
//import java.time.ZoneId;
//import java.util.ArrayList;
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
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.assessment.co2.sensor.domain.model.SummarizedSensorDay;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
//public class SummarizedSensorDayRepositoryImplTest {
//	@Autowired
//	@InjectMocks
//	SummarizedSensorDayRepositoryImpl summarizedSensorDayRepository;
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
///**
// * Test case to test bulk save of summarised senror date and retrieve of metric
// */
//	@Test
//	public void testSaveAllSummarizedSensorDayRepository() {
//
//		UUID sensorId = UUID.randomUUID();
//		int avg1 = 170;
//		int avg2 = 180;
//		int avg3 = 190;
//		int avg4 = 160;
//		int avg5 = 150;
//		int avg6 = 140;
//		int max = 400;
//		DateTime date=LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		SummarizedSensorDay summarizedData1 = getSummarizedSensorDay(avg1, 228,
//				date, sensorId);
//		SummarizedSensorDay summarizedData2 = getSummarizedSensorDay(avg2, 250,
//				date, sensorId);
//		SummarizedSensorDay summarizedData3 = getSummarizedSensorDay(avg3, 270,
//				date, sensorId);
//		SummarizedSensorDay summarizedData4 = getSummarizedSensorDay(avg4, 230,
//				date, sensorId);
//		SummarizedSensorDay summarizedData5 = getSummarizedSensorDay(avg5, max,
//				date, sensorId);
//		SummarizedSensorDay summarizedData6 = getSummarizedSensorDay(avg6, 228,
//				date, sensorId);
//		int sum=avg1+avg2+avg3+avg4+avg5+avg6;
//		int avg=sum/6;
//		List<SummarizedSensorDay> summarizedSensorDays=new ArrayList<SummarizedSensorDay>();
//		summarizedSensorDays.add(summarizedData1);
//		summarizedSensorDays.add(summarizedData2);
//		summarizedSensorDays.add(summarizedData3);
//		summarizedSensorDays.add(summarizedData4);
//		summarizedSensorDays.add(summarizedData5);
//		summarizedSensorDays.add(summarizedData6);
//	
//		summarizedSensorDayRepository.saveAllSummarizedSensorDayRepository(summarizedSensorDays);
//		DateTime start=LocalDate.now().minusDays(2).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		DateTime end=LocalDate.now().plusDays(2).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		
//		SummarizedSensorDay summarizeSensorDay= summarizedSensorDayRepository.getRangeOfdaysSummarizedMetrix(sensorId,start,end );
//	
//		assertEquals(avg, summarizeSensorDay.getAverage());
//		assertEquals(max, summarizeSensorDay.getMax());
//	}
///**
// * Test case for the scenario when there is no data present for the range
// */
//	@Test
//	public void testSaveAllSummarizedSensorDayRepositoryDifferentRange() {
//		UUID sensorId = UUID.randomUUID();
//		int avg1 = 170;
//		int avg2 = 180;
//		int avg3 = 190;
//		int avg4 = 160;
//		int avg5 = 150;
//		int avg6 = 140;
//		int max = 400;
//		DateTime date=LocalDate.now().toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		SummarizedSensorDay summarizedData1 = getSummarizedSensorDay(avg1, 228,
//				date, sensorId);
//		SummarizedSensorDay summarizedData2 = getSummarizedSensorDay(avg2, 250,
//				date, sensorId);
//		SummarizedSensorDay summarizedData3 = getSummarizedSensorDay(avg3, 270,
//				date, sensorId);
//		SummarizedSensorDay summarizedData4 = getSummarizedSensorDay(avg4, 230,
//				date, sensorId);
//		SummarizedSensorDay summarizedData5 = getSummarizedSensorDay(avg5, max,
//				date, sensorId);
//		SummarizedSensorDay summarizedData6 = getSummarizedSensorDay(avg6, 228,
//				date, sensorId);
//		int sum=avg1+avg2+avg3+avg4+avg5+avg6;
//		int avg=sum/6;
//		List<SummarizedSensorDay> summarizedSensorDays=new ArrayList<SummarizedSensorDay>();
//		summarizedSensorDays.add(summarizedData1);
//		summarizedSensorDays.add(summarizedData2);
//		summarizedSensorDays.add(summarizedData3);
//		summarizedSensorDays.add(summarizedData4);
//		summarizedSensorDays.add(summarizedData5);
//		summarizedSensorDays.add(summarizedData6);
//		
//		summarizedSensorDayRepository.saveAllSummarizedSensorDayRepository(summarizedSensorDays);
//		
//		DateTime start=LocalDate.now().minusDays(10).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		DateTime end=LocalDate.now().minusDays(2).toDateTimeAtStartOfDay(DateTimeZone.getDefault());
//		
//		SummarizedSensorDay summarizeSensorDay= summarizedSensorDayRepository.getRangeOfdaysSummarizedMetrix(sensorId,start,end );
//		
//		assertEquals(0, summarizeSensorDay.getAverage());
//		assertEquals(0, summarizeSensorDay.getMax());
//	}
//	private SummarizedSensorDay getSummarizedSensorDay(int avg, int max, DateTime date, UUID sensorId) {
//		SummarizedSensorDay summarizedData = new SummarizedSensorDay();
//		summarizedData.setAverage(avg);
//		summarizedData.setMax(max);
//		summarizedData.setDate(date);
//		summarizedData.setSensorId(sensorId);
//		summarizedData.setId(UUID.randomUUID());
//		return summarizedData;
//	}
//
//}
