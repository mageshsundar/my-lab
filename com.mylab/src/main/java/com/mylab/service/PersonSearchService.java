package com.mylab.service;

import org.apache.log4j.Logger;

import com.mylab.dao.service.GetPersonData;
import com.mylab.json.data.Person;

public class PersonSearchService {

final static Logger logger = Logger.getLogger(PersonSearchService.class);
	
	public static void main(String a[]) throws Exception {

		Person person = new Person();
		person.setPersonID(a[0]);
		person.setPersonName(a[1]);
		new GetPersonData().getPerson(person);

	}
}
