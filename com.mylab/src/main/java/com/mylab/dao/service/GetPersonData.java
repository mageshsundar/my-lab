package com.mylab.dao.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.GetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
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
				
		/* PrimaryKey primaryKey = new PrimaryKey();
		 primaryKey.addComponent("PersonName", person.getPersonID());
		
		item.withPrimaryKey("PersonID", person.getPersonID());
		item.withString("PersonName", person.getPersonName());
		item.withLong("PersonAge", person.getPersonAge());
		item.withString("PersonDOB", String.valueOf(person.getPersonDOB()));
		item.withString("PersonGender", person.getPersonGender());
		item.withList("PersonCommunication", person.getPersonCommunication());
		Item outcome = table.getItem(primaryKey);
*/
		
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

}
