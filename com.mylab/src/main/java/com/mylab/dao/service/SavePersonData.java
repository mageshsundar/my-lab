package com.mylab.dao.service;

import org.apache.log4j.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.mylab.dao.PersonDAO;
import com.mylab.json.data.Person;

public class SavePersonData {
	
	final static Logger logger = Logger.getLogger(SavePersonData.class);
	
	
	public String savePerson(Person person) throws Exception {
		String statusCode = null;
		
		PersonDAO personDAO = new PersonDAO();
		DynamoDB dynamoDB = personDAO.initConnection();
		
		Table table = dynamoDB.getTable("Person");
		Item item = new Item();
		item.withPrimaryKey("PersonID", person.getPersonID());
		item.withString("PersonName", person.getPersonName());
		item.withLong("PersonAge", person.getPersonAge());
		item.withString("PersonDOB", String.valueOf(person.getPersonDOB()));
		item.withString("PersonGender", person.getPersonGender());
		//item.withList("PersonCommunication", person.getPersonCommunication());
		
		PutItemOutcome outcome = table.putItem(item);
		
		if(logger.isDebugEnabled()) {
			if(outcome != null)
				logger.debug(outcome.getItem().get("PersonName"));
		}
		return statusCode;
	}
	
	public String saveWihMapper(Person person) {
		try {
			PersonDAO personDAO = new PersonDAO();
			AmazonDynamoDBClient dbClient = personDAO.getDBClient();
			
			DynamoDBMapper mapper = new DynamoDBMapper(dbClient);
			mapper.save(person);
		} catch(Exception e) {
			logger.error ("Error in Saving", e);
		}
		return "SUCCESS";
	}
	

}
