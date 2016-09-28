package com.mylab.dao;

import org.apache.log4j.Logger;

//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;



public class PersonDAO {
	
	final static Logger logger = Logger.getLogger(PersonDAO.class);
	
	/* 
	 *private String profileConfigPath = null;
	  private String profileName = null;
	
	 * DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
											new ProfileCredentialsProvider(profileConfigPath, profileName)));
	*/
	
	public DynamoDB initConnection() {
		DynamoDB dynamoDB = null;
		
		try {

			AmazonDynamoDBClient dbClient = new AmazonDynamoDBClient();
			dbClient.setEndpoint("http://localhost:8000");
			dynamoDB = new DynamoDB(dbClient);
			
		} catch (Exception e) {
			logger.error("Error in getting a connection", e);
		}
		return dynamoDB;
	}
	
	public AmazonDynamoDBClient getDBClient() {
		try {
			AmazonDynamoDBClient dbClient = new AmazonDynamoDBClient();
			dbClient.setEndpoint("http://localhost:8000");
			return dbClient;
		} catch (Exception e) {
			logger.error("Error in getting DBClient", e);
			return null;
		}
	}

}
