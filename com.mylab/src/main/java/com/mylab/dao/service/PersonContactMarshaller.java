package com.mylab.dao.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import com.mylab.json.data.PersonContact;

public class PersonContactMarshaller implements DynamoDBMarshaller<PersonContact> {

	@Override
	public String marshall(PersonContact getterReturnResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonContact unmarshall(Class<PersonContact> clazz, String obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
