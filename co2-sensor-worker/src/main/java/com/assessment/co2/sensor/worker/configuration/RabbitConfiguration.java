/**
 * 
 */
package com.assessment.co2.sensor.worker.configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;


import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.assessment.co2.sensor.domain.model.Sensor;
import com.assessment.co2.sensor.worker.handler.SensorMessageHandler;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * Configuration class for rabbit
 * @author ghosh
 *
 */
@PropertySource(value = "classpath:/workerapplication.properties")
@Component
public class RabbitConfiguration implements InitializingBean {
	private SensorMessageHandler sensorMessageHandler;
	
	@Autowired
	Environment env;

	@Autowired
	public RabbitConfiguration(SensorMessageHandler sensorMessageHandle) {
		this.sensorMessageHandler = sensorMessageHandle;
	}
	/**
	 * Creates the connection with rabbitMQ and receives the call back from rabbit when ever there is message in the queue
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		String rabbitQueue=env.getProperty("rabbit.queuename");
		String rabbitHost=env.getProperty("rabbit.host");
		
		factory.setHost(rabbitHost);
		final Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();

		channel.queueDeclare(rabbitQueue, true, false, false, null);

		channel.basicQos(0);

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {

			// String message = new String(delivery.getBody(), "UTF-8");
			Sensor sensor = getSensorObj(delivery.getBody());
			//System.out.println(" [x] Received '" + sensor.getSensorId() + "'");
			try {
				boolean ack = sensorMessageHandler.handleMessage(sensor);
				if (ack) {
					channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
				}
			} catch(Exception e) {
				e.printStackTrace();
				//channel.basicNack(delivery.getEnvelope().getDeliveryTag(), true, true);
			}
			
			finally {
				

				// channel.
			}
		};
		channel.basicConsume(rabbitQueue, false, deliverCallback, consumerTag -> {
		});

	}
	/**
	 * Creates sensor object from bytes array
	 * @param bytes
	 * @return
	 */
	private Sensor getSensorObj(byte[] bytes) {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		Sensor sensor = null;
		;
		try {
			in = new ObjectInputStream(bis);
			try {
				sensor = (Sensor)in.readObject();
				DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
				// Parsing the date
				sensor.setRecordingDateTime(dtf.parseDateTime(sensor.getTime()));
				sensor.setTime("");
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return sensor;
	}
}
