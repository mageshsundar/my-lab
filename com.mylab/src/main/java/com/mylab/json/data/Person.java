/**
 * 
 */
package com.mylab.json.data;

/**
 * @author mageshs
 *
 */

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.mylab.dao.service.PersonCommunicationMarshaller;

@DynamoDBTable(tableName = "Person")
public class Person {
	String personName;
	String personID;
	Long personAge;
	Date personDOB;
	String personGender;
	PersonCommunication personCommunication;
	
	@DynamoDBRangeKey (attributeName = "PersonName")
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	@DynamoDBHashKey (attributeName = "PersonID")
	public String getPersonID() {
		return personID;
	}
	
	
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public Long getPersonAge() {
		return personAge;
	}
	public void setPersonAge(Long personAge) {
		this.personAge = personAge;
	}
	public Date getPersonDOB() {
		return personDOB;
	}
	public void setPersonDOB(Date personDOB) {
		this.personDOB = personDOB;
	}
	public String getPersonGender() {
		return personGender;
	}
	public void setPersonGender(String personGender) {
		this.personGender = personGender;
	}
	
	@DynamoDBMarshalling (marshallerClass = PersonCommunicationMarshaller.class)
	public PersonCommunication getPersonCommunication() {
		return personCommunication;
	}
	public void setPersonCommunication(PersonCommunication personCommunication) {
		this.personCommunication = personCommunication;
	}
	

}
