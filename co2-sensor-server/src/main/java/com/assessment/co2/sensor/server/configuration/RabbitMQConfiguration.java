package com.assessment.co2.sensor.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * Configuration class for RabbitMQ
 * @author ghosh
 *
 */
@PropertySource(value = "classpath:/application.properties")
@Configuration
public class RabbitMQConfiguration {
	@Autowired
	Environment env;
	
	private static String QUEUENAME;
	/**
	 * Creates the connection factory for the rabbitMQ
	 * @return
	 * @throws Exception
	 */
	@Bean("rabbitconnectionfactory")
	public ConnectionFactory rabbitConnectionFactory() throws Exception {
		try {
			String host = env.getProperty("rabbit.host");
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			return factory;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * Creates the rabbit channel
	 * @return
	 * @throws Exception
	 */
	@Bean("rabbitchannel")
	public Channel rabbitchannel() throws Exception {
		try {
			QUEUENAME = env.getProperty("rabbit.queuename");
			Connection connection = rabbitConnectionFactory().newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUENAME, true, false, false, null);
			return channel;
		} catch (Exception e) {
			throw e;
		}
	}

	public static String queueName() {
		return QUEUENAME;
	}
}
