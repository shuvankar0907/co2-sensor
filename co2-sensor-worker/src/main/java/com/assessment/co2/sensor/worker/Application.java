/**
 * 
 */
package com.assessment.co2.sensor.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ghosh
 *
 */
@SpringBootApplication(scanBasePackages = {"com.assessment.co2.sensor.domain","com.assessment.co2.sensor.worker"})
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
