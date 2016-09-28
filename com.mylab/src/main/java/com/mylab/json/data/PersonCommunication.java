package com.mylab.json.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class PersonCommunication {
	PersonAddress[] personAddress;
	PersonContact[] personContact;
	
	public PersonAddress[] getPersonAddress() {
		return personAddress;
	}
	public void setPersonAddress(PersonAddress[] personAddress) {
		this.personAddress = personAddress;
	}
	public PersonContact[] getPersonContact() {
		return personContact;
	}
	public void setPersonContact(PersonContact[] personContact) {
		this.personContact = personContact;
	}
	
	
}
