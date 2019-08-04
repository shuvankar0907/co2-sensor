/**
 * 
 */
package com.assessment.co2.sensor.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.assessment.co2.sensor.domain.model.SensorStatus;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import com.mongodb.*;

/**
 * @author ghosh
 *
 */
@Repository
public class SensorStatusRepositoryImpl implements SensorStatusRepository {

	private final MongoOperations mongoTemplate;
	private static final String SENSOR_STATUS_COLLECTION_NAME = "SensorStatus";

	@Autowired
	public SensorStatusRepositoryImpl(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/*
	 * Get current sensorStatus
	 * 
	 * @see com.assessment.co2.sensor.domain.repository.SensorStatusRepository#
	 * getSensorStatus(java.util.UUID)
	 */
	@Override
	public SensorStatus getSensorStatus(UUID sensorId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sensorId").is(sensorId));
		
		return mongoTemplate.findOne(query, SensorStatus.class, SENSOR_STATUS_COLLECTION_NAME);
	}
	/**
	 * Bulk update of SensorStatus
	 */
	public int saveAll(List<SensorStatus> sensorStatus) {
		final DBCollection collection = mongoTemplate.getCollection(SENSOR_STATUS_COLLECTION_NAME);
		BulkWriteOperation bulkWriteOperation = collection.initializeUnorderedBulkOperation();
		
		for (SensorStatus data : sensorStatus) {
			final BasicDBObject dbObject = new BasicDBObject();
			mongoTemplate.getConverter().write(data, dbObject);
			
			DBObject query = new BasicDBObject("_id", data.getSensorId());
			
			bulkWriteOperation.find(query).upsert().replaceOne(dbObject);
		}
		
		BulkWriteResult result = bulkWriteOperation.execute();
        return result.getUpserts().size();
       
	}
	

	

}
