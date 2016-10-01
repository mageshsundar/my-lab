package com.mylab.dao.service;

import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.GetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.mylab.dao.PersonDAO;
import com.mylab.json.data.Person;

public class GetPersonData {

	final static Logger logger = Logger.getLogger(SavePersonData.class);

	public String getPerson(Person person) throws Exception {
		String statusCode = null;

		PersonDAO personDAO = new PersonDAO();
		DynamoDB dynamoDB = personDAO.initConnection();
		Table table = dynamoDB.getTable("Person");
		
		String hashKeyValue = person.getPersonID();
		String rangeKeyValue = person.getPersonName();
		
		GetItemOutcome outcome = table.getItemOutcome("PersonID", hashKeyValue, "PersonName", rangeKeyValue);
		
		if (outcome != null) {
			GetItemResult result = outcome.getGetItemResult();
			Map<String, AttributeValue> item = result.getItem();

			if (logger.isDebugEnabled())
				logger.debug("Retrieved data for " + item.get("PersonName"));

		}

		statusCode = "SUCCESS";
		
		return statusCode;
	}

	public String searchPersonWithMapper(Person person) throws Exception {
		try {
			PersonDAO personDAO = new PersonDAO();
			AmazonDynamoDBClient dbClient = personDAO.getDBClient();
			
			DynamoDBMapper mapper = new DynamoDBMapper(dbClient);
			mapper.load(person);
			
		} catch(Exception e) {
			logger.error ("Error in Saving", e);
		}
		return "SUCCESS";
	
	}
	
}
