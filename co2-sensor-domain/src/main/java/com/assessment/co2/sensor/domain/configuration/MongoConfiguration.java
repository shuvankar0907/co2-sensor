package com.assessment.co2.sensor.domain.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;
/**
 * Mongo configuration class 
 * @author ghosh
 *
 */
@PropertySource(value = "classpath:/domainapp.properties")
@Configuration
public class MongoConfiguration {
	@Autowired
	Environment env;

	@Bean("mongoDbFactory")
	public MongoDbFactory ruleMongoDbFactory() throws Exception {
		MongoClientURI mongoClientURI = new MongoClientURI(env.getProperty("mongo.rulesdb.uri"));
		return new SimpleMongoDbFactory(mongoClientURI);
	}

	@Bean("com.assessment.co2.sensor.domain.MongoTemplate")
	public MongoTemplate ruleMongoTemplate() throws Exception {
		return new MongoTemplate(ruleMongoDbFactory());
	}
		
}
