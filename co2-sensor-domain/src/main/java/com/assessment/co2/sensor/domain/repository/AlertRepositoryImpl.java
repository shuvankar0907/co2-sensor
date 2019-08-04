package com.assessment.co2.sensor.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.assessment.co2.sensor.domain.model.Alert;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DBCollection;
/**
 * Repository class for Alert collection
 * @author ghosh
 *
 */
@Repository
public class AlertRepositoryImpl implements AlertRepository{

	private MongoTemplate mongoTemplate;
	private final String ALERRT_COLLECTION_NAME="Alert";
	
	@Autowired
	public AlertRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate=mongoTemplate;
	}
	/**
	 * Inserts new alerts documents
	 */
	public void save(Alert alert) throws Exception{
		mongoTemplate.insert(alert, ALERRT_COLLECTION_NAME);
		
	}
	/**
	 * Returns all alerts for a sensor 
	 */
	public List<Alert> getAllAlertsForSensor(UUID sensorId) throws Exception{
		
		Query query = new Query();
		query.addCriteria(Criteria.where("sensorId").is(sensorId));
		query.with(new Sort(Sort.Direction.ASC, "recoredDate"));
		
		return mongoTemplate.find(query,Alert.class, ALERRT_COLLECTION_NAME);
	}
	/**
	 * Bulk insert for all the alerts
	 */
	public int saveAll(List<Alert> alerts) {
		final DBCollection collection =mongoTemplate.getCollection(ALERRT_COLLECTION_NAME);
		
		final BulkWriteOperation bulkWriteOperationInserts = collection.initializeUnorderedBulkOperation();
		
		for (Alert data : alerts) {
            final BasicDBObject dbObject = new BasicDBObject();
            mongoTemplate.getConverter().write(data, dbObject);
            bulkWriteOperationInserts.insert(dbObject);
        }
		
		final com.mongodb.BulkWriteResult bulkWriteResult = bulkWriteOperationInserts.execute();
        return bulkWriteResult.getInsertedCount();
	}

}
