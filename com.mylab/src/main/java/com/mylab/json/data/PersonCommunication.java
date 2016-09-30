package com.mylab.json.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.mylab.dao.service.PersonAddressMarshaller;
import com.mylab.dao.service.PersonContactMarshaller;

@DynamoDBTable(tableName = "Person")
public class PersonCommunication {
	PersonAddress[] personAddress;
	PersonContact[] personContact;
	
	@DynamoDBMarshalling (marshallerClass = PersonAddressMarshaller.class)
	public PersonAddress[] getPersonAddress() {
		return personAddress;
	}
	public void setPersonAddress(PersonAddress[] personAddress) {
		this.personAddress = personAddress;
	}
	
	@DynamoDBMarshalling (marshallerClass = PersonContactMarshaller.class)
	public PersonContact[] getPersonContact() {
		return personContact;
	}
	public void setPersonContact(PersonContact[] personContact) {
		this.personContact = personContact;
	}
	
	
}
