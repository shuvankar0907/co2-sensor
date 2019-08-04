package com.assessment.co2.sensor.domain.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:domainapp.properties")
@ComponentScan({"com.assessment.co2.sensor.domain" })
public class TestConfig {

}
