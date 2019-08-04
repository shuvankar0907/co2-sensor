package com.assessment.co2.sensor.domain.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.assessment.co2.sensor.domain.model.SummarizedSensorDay;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DBCollection;
/**
 * This Class stores summarised data for each sensor 
 * @author ghosh
 *
 */
@Repository
public class SummarizedSensorDayRepositoryImpl implements SummarizedSensorDayReposirory {
	private final MongoTemplate mongoTemplate;
	private static final String SUMMARIZED_COLLECTION_NAME = "SummarizedSensorDay";
	
	@Autowired
	public SummarizedSensorDayRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate=mongoTemplate;
	}
	
	/**
	 * This method stores collection of summarized sensor data. It is optimised as it does bulk insert 
	 */
	@Override
	public int saveAllSummarizedSensorDayRepository(List<SummarizedSensorDay> summarizedDaysDatas) {
		final DBCollection collection = mongoTemplate.getCollection(SUMMARIZED_COLLECTION_NAME);

		final BulkWriteOperation bulkWriteOperationInserts = collection.initializeUnorderedBulkOperation();

		for (SummarizedSensorDay data : summarizedDaysDatas) {
			final BasicDBObject dbObject = new BasicDBObject();
			mongoTemplate.getConverter().write(data, dbObject);
			bulkWriteOperationInserts.insert(dbObject);
		}

		final com.mongodb.BulkWriteResult bulkWriteResult = bulkWriteOperationInserts.execute();
		return bulkWriteResult.getInsertedCount();
	}
	
	/**
	 * This method retries sensor summarised data based on sensor Id for range of period
	 */
	@Override
	public SummarizedSensorDay getRangeOfdaysSummarizedMetrix(UUID sensorId,DateTime startDate, DateTime endDate) {
		Date sDate=startDate.toDate();
		Date eDate=endDate.toDate();
		AggregationOperation filter=match(Criteria.where("sensorId").is(sensorId).and("date").gte(sDate).lt(eDate));
		AggregationOperation group=groupBySensorId();
		final Aggregation aggregation = Aggregation.newAggregation(filter,group);
		
		AggregationResults<SummarizedSensorDay> results = this.mongoTemplate.aggregate(aggregation,
				SUMMARIZED_COLLECTION_NAME,
	            SummarizedSensorDay.class);
		if(results.getMappedResults().size()>0) {
			return results.getMappedResults().get(0);
		}
		else {
			SummarizedSensorDay retObj=new SummarizedSensorDay();
			retObj.setSensorId(sensorId);
			return retObj;
		}
	}
	
	 private AggregationOperation groupBySensorId() {
	        return new DBObjectAggregationOperation(new BasicDBObject("$group", new BasicDBObject()
	            .append("_id","$" + "sensorId")
	            .append("average", new BasicDBObject("$avg", "$" + "average"))
	            .append("max", new BasicDBObject("$max", "$" + "max")))
	           
	        );
	        
	    }
	public static MatchOperation match(Criteria criteria) {
		return new MatchOperation(criteria);
	}

}
