package com.mylab.dao.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import com.mylab.json.data.PersonAddress;

public class PersonAddressMarshaller implements DynamoDBMarshaller<PersonAddress> {

	@Override
	public String marshall(PersonAddress getterReturnResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonAddress unmarshall(Class<PersonAddress> clazz, String obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
