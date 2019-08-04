package com.assessment.co2.sensor.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.assessment.co2.sensor.domain","com.assessment.co2.sensor.server"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
	

}
