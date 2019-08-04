package com.assessment.co2.sensor.server.business;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan({"com.assessment.co2.sensor.domain" })
public class TestConfig {

}
