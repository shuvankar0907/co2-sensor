///**
// * 
// */
//package com.assessment.co2.sensor.domain.business;
//
//import static org.junit.Assert.*;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//
//import static org.mockito.Mockito.*;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.assessment.co2.sensor.domain.business.Rule;
//import com.assessment.co2.sensor.domain.business.RuleContext;
//import com.assessment.co2.sensor.domain.model.Status;
//import com.assessment.co2.sensor.domain.repository.TestConfig;
//
///**
// * @author ghosh
// *
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
//public class RuleTest {
//	
//	private final int BELOW_RANGE=1800;
//	private final int ABOVE_RANGE=2200;
//	private final int PERMITTED_RANGE=2000;
//
//	@Mock
//    RuleContext ruleContext;
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
//	 * Test method for {@link com.assessment.co2.sensor.domain.domain.business.Rule#executeRule(com.assessment.co2.sensor.domain.domain.business.RuleContext)}.
//	 */
//	@Test
//	public void testExecuteRuleCurrentStatusOKAndNewLevelisBelowRange() {
//		//RuleContext ruleContext
//		when(ruleContext.getCurrentSensorStatus()).thenReturn(Status.OK);
//		when(ruleContext.getNewCo2level()).thenReturn(BELOW_RANGE);
//		RuleContext ruleContext1=Rule.executeRule(ruleContext);
//		assertEquals(Status.OK, ruleContext1.getNewSensorStatus());
//		
//	}
//	
//	/**
//	 * Test method for {@link com.assessment.co2.sensor.domain.domain.business.Rule#executeRule(com.assessment.co2.sensor.domain.domain.business.RuleContext)}.
//	 */
//	@Test
//	public void testExecuteRuleCurrentStatusOKAndNewLevelisAboveRange() {
//		when(ruleContext.getCurrentSensorStatus(2)).thenReturn(Status.OK);
//		when(ruleContext.getNewCo2level()).thenReturn(ABOVE_RANGE);
//		Status status=Rule.executeRule(ruleContext);
//		assertEquals(Status.WARN, status);
//		verify(this.ruleContext, times(0)).saveAlert(Status.ALERT);
//		verify(this.ruleContext, times(1)).saveNewLevel(Status.WARN);
//	}
//	
//	@Test
//	public void testExecuteRuleCurrentStatusOKAndNewLevelisEqualToPermittedRange() {
//		when(ruleContext.getCurrentSensorStatus(2)).thenReturn(Status.OK);
//		when(ruleContext.getNewCo2level()).thenReturn(PERMITTED_RANGE);
//		Status status=Rule.executeRule(ruleContext);
//		assertEquals(Status.OK, status);
//		verify(this.ruleContext, times(0)).saveAlert(Status.ALERT);
//		verify(this.ruleContext, times(1)).saveNewLevel(Status.OK);
//	}
//	
//	@Test
//	public void testExecuteRuleCurrentStatusWARNAndNewLevelisBELOWRange() {
//		when(ruleContext.getCurrentSensorStatus(2)).thenReturn(Status.WARN);
//		when(ruleContext.getNewCo2level()).thenReturn(BELOW_RANGE);
//		Status status=Rule.executeRule(ruleContext);
//		assertEquals(Status.OK, status);
//		verify(this.ruleContext, times(0)).saveAlert(Status.ALERT);
//		verify(this.ruleContext, times(1)).saveNewLevel(Status.OK);
//	}
//	
//	@Test
//	public void testExecuteRuleCurrentStatusWARNAndNewLevelisEqualToPermittedLevel() {
//		when(ruleContext.getCurrentSensorStatus(2)).thenReturn(Status.WARN);
//		when(ruleContext.getNewCo2level()).thenReturn(PERMITTED_RANGE);
//		Status status=Rule.executeRule(ruleContext);
//		assertEquals(Status.OK, status);
//		verify(this.ruleContext, times(0)).saveAlert(Status.ALERT);
//		verify(this.ruleContext, times(1)).saveNewLevel(Status.OK);
//	}
//	
//	@Test
//	public void testExecuteRuleCurrentStatusWARNAndNewLevelisAboveRangeButNotConsecutive() {
//		int[] previousReadings= {BELOW_RANGE,ABOVE_RANGE};
//		when(ruleContext.getPreviousXreadings(2)).thenReturn(previousReadings);
//		when(ruleContext.getCurrentSensorStatus(2)).thenReturn(Status.WARN);
//		when(ruleContext.getNewCo2level()).thenReturn(ABOVE_RANGE);
//		Status status=Rule.executeRule(ruleContext);
//		assertEquals(Status.WARN, status);
//		verify(this.ruleContext, times(0)).saveAlert(Status.ALERT);
//		verify(this.ruleContext, times(1)).saveNewLevel(Status.WARN);
//		
//	}
//	
//	@Test
//	public void testExecuteRuleCurrentStatusWARNAndNewLevelisAboveRangeAndConsecutive() {
//		int[] previousReadings= {ABOVE_RANGE,ABOVE_RANGE};
//		when(ruleContext.getPreviousXreadings(2)).thenReturn(previousReadings);
//		when(ruleContext.getCurrentSensorStatus(2)).thenReturn(Status.WARN);
//		when(ruleContext.getNewCo2level()).thenReturn(ABOVE_RANGE);
//		Status status=Rule.executeRule(ruleContext);
//		assertEquals(Status.ALERT, status);
//		verify(this.ruleContext, times(1)).saveAlert(Status.ALERT);
//		verify(this.ruleContext, times(1)).saveNewLevel(Status.ALERT);
//	}
//	//  Alert
//	@Test
//	public void testExecuteRuleCurrentStatusALERTAndNewLevelisAboveRange() {
//		when(ruleContext.getCurrentSensorStatus(2)).thenReturn(Status.ALERT);
//		when(ruleContext.getNewCo2level()).thenReturn(ABOVE_RANGE);
//		Status status=Rule.executeRule(ruleContext);
//		assertEquals(Status.ALERT, status);
//		verify(this.ruleContext, times(1)).saveAlert(Status.ALERT);
//		verify(this.ruleContext, times(1)).saveNewLevel(Status.ALERT);
//	}
//	
//	@Test
//	public void testExecuteRuleCurrentStatusALERTAndNewLevelisEqualToPermittedLevel() {
//		when(ruleContext.getCurrentSensorStatus(2)).thenReturn(Status.ALERT);
//		when(ruleContext.getNewCo2level()).thenReturn(PERMITTED_RANGE);
//		Status status=Rule.executeRule(ruleContext);
//		assertEquals(Status.ALERT, status);
//		verify(this.ruleContext, times(1)).saveAlert(Status.ALERT);
//		verify(this.ruleContext, times(1)).saveNewLevel(Status.ALERT);
//	}
//	
//	@Test
//	public void testExecuteRuleCurrentStatusALERTAndNewLevelisBelowRangeANDConsecutiveValueAboveRange() {
//		int[] previousReadings= {ABOVE_RANGE,ABOVE_RANGE};
//		when(ruleContext.getPreviousXreadings(2)).thenReturn(previousReadings);
//		when(ruleContext.getCurrentSensorStatus(2)).thenReturn(Status.ALERT);
//		when(ruleContext.getNewCo2level()).thenReturn(BELOW_RANGE);
//		Status status=Rule.executeRule(ruleContext);
//		assertEquals(Status.ALERT, status);
//		verify(this.ruleContext, times(1)).saveAlert(Status.ALERT);
//		verify(this.ruleContext, times(1)).saveNewLevel(Status.ALERT);
//	}
//	
//	@Test
//	public void testExecuteRuleCurrentStatusALERTAndNewLevelisBelowRangeANDConsecutiveValueBelowRange() {
//		int[] previousReadings= {BELOW_RANGE,BELOW_RANGE};
//		when(ruleContext.getPreviousXreadings(2)).thenReturn(previousReadings);
//		when(ruleContext.getCurrentSensorStatus(2)).thenReturn(Status.ALERT);
//		when(ruleContext.getNewCo2level()).thenReturn(BELOW_RANGE);
//		Status status=Rule.executeRule(ruleContext);
//		assertEquals(Status.OK, status);
//		verify(this.ruleContext, times(1)).saveNewLevel(Status.OK);
//	}
//
//}
