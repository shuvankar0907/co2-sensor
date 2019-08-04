package com.assessment.co2.sensor.server.business;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assessment.co2.sensor.domain.model.Sensor;
import com.assessment.co2.sensor.server.configuration.RabbitMQConfiguration;
import com.rabbitmq.client.Channel;
/**
 * This class publishes messages to rabbit MQ channel
 * @author ghosh
 *
 */
@Component
public class RabbitMQManager implements AsynchoronousMessage{
	
	Channel channel;
	
	@Autowired
	public RabbitMQManager(Channel channel) {
		this.channel=channel;
	}
	/**
	 * Sends message to rabbit channel
	 */
	@Override
	public void sendMsg(Sensor sensor) {
		try {
			channel.basicPublish("", RabbitMQConfiguration.queueName(), null, sensor.getBytes());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
