package com.assessment.co2.sensor.worker.handler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:workerapplication.properties")
@ComponentScan(basePackages={"com.assessment.co2.sensor.domain","com.assessment.co2.sensor.worker"})
public class TestConfig {

}
