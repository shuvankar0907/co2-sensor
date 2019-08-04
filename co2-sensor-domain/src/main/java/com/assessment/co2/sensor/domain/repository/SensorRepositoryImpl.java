package com.assessment.co2.sensor.domain.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoOperations;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import com.assessment.co2.sensor.domain.model.Sensor;
import com.assessment.co2.sensor.domain.model.Status;
import com.assessment.co2.sensor.domain.model.SummarizedSensorDay;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DBCollection;
/**
 * This is the sensor repository class. It handles all the mongo queries for sensor data
 * @author ghosh
 *
 */
@Repository
public class SensorRepositoryImpl implements SensorRepository{

	private final MongoOperations mongoTemplate;
	private static final String SENSOR_COLLECTION_NAME = "Sensor";
	
	@Autowired
	public SensorRepositoryImpl(MongoOperations mongoTemplate) {
		this.mongoTemplate=mongoTemplate;
	}
	/**
	 * Saves sensor levels in Sensor collection
	 */
	public void saveLevel(Sensor sensor) {
		mongoTemplate.insert(sensor,SENSOR_COLLECTION_NAME);		
	}
	
	/**
	 * Gets the current status of a sensor 
	 */
	public Status getStatus(UUID sensorId) throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("sensorId").is(sensorId));

		Sensor sensor=mongoTemplate.findOne(query, Sensor.class, SENSOR_COLLECTION_NAME);
		if(sensor==null) {
			throw new Exception("No data found");
		}
		return sensor.getStatus();
	}
	/**
	 * Gets the previous x number of records of the a sensor
	 */
	public List<Sensor> getPreviousXLevels(UUID sensorId,int noOfPreviousRecord) {
		 Query query = new Query(Criteria.where("sensorId").is(sensorId));
		 	query.limit(noOfPreviousRecord);
		 	query.with(new Sort(Sort.Direction.DESC, "recordingDateTime"));

	        return mongoTemplate.find(query, Sensor.class,SENSOR_COLLECTION_NAME);
	}
	/**
	 * Saves all the sensors data. This method is optimized for bulk save
	 */
	public int saveAll(List<Sensor> sensorsData) {
		final DBCollection collection =mongoTemplate.getCollection(SENSOR_COLLECTION_NAME);
		
		
		final BulkWriteOperation bulkWriteOperationInserts = collection.initializeUnorderedBulkOperation();
		
		for (Sensor data : sensorsData) {
            final BasicDBObject dbObject = new BasicDBObject();
            mongoTemplate.getConverter().write(data, dbObject);
            bulkWriteOperationInserts.insert(dbObject);
        }
		
		final com.mongodb.BulkWriteResult bulkWriteResult = bulkWriteOperationInserts.execute();
        return bulkWriteResult.getInsertedCount();
	}
	/**
	 * Gets the aggregated data between start date and end date for all the sensors as summarised days
	 */
	public List<SummarizedSensorDay> getAggregatedData(DateTime startDate, DateTime endDate) {
		Date sDate=startDate.toDate();
		Date eDate=endDate.toDate();
		AggregationOperation filter=match(Criteria.where("recordingDateTime").gte(sDate).lt(eDate));
		AggregationOperation group=groupBySensorId();
		AggregationOperation project=projectSummarizedDaySensorData(sDate);
		
		final Aggregation aggregation = Aggregation.newAggregation(filter,group,project);
		
		AggregationResults<SummarizedSensorDay> results = this.mongoTemplate.aggregate(aggregation,
	            SENSOR_COLLECTION_NAME,
	            SummarizedSensorDay.class);
		
		return results.getMappedResults();
		
		
	}
	/**
	 * Bulk deletes all the documents in the dateTime range
	 */
	public void delete(DateTime startDate, DateTime endDate) {
		Date sDate=startDate.toDate();
		Date eDate=endDate.toDate();
		
		Query remove=  new Query(Criteria.where("recordingDateTime").gte(sDate).lt(eDate));
				
		BulkOperations bulkOperation= mongoTemplate.bulkOps(BulkMode.UNORDERED, SENSOR_COLLECTION_NAME).remove(remove);
		bulkOperation.execute();
		
	}
	
	 private AggregationOperation groupBySensorId() {
	        return new DBObjectAggregationOperation(new BasicDBObject("$group", new BasicDBObject()
	            .append("_id","$" + "sensorId")
	            .append("average", new BasicDBObject("$avg", "$" + "level"))
	            .append("max", new BasicDBObject("$max", "$" + "level")))
	           
	        );
	        
	    }
	 
	    private AggregationOperation projectSummarizedDaySensorData(Date startDate) {
	    	 return new DBObjectAggregationOperation(new BasicDBObject("$project",
	    	            new BasicDBObject().append("average", 1)
	    	                .append("max", 1)
	    	                .append("sensorId", "$_id")
	    	                .append("_id", 0)
	    	                .append("date", startDate)));
	    }
	 
	public static MatchOperation match(Criteria criteria) {
		return new MatchOperation(criteria);
	}
	

	
	
	
	

}
