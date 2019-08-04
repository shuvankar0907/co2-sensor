package com.assessment.co2.sensor.domain.repository;

import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

import com.mongodb.DBObject;

public class DBObjectAggregationOperation implements AggregationOperation{

	 private DBObject operation;

	    public DBObjectAggregationOperation(DBObject operation) {
	        this.operation = operation;
	    }

	    @Override
	    public DBObject toDBObject(AggregationOperationContext context) {
	        return context.getMappedObject(operation);
	    }

}
