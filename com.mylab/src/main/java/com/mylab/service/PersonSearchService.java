package com.mylab.service;


import org.apache.log4j.Logger;

import com.mylab.dao.service.GetPersonData;
import com.mylab.json.data.Person;

public class PersonSearchService {

final static Logger logger = Logger.getLogger(PersonSearchService.class);
	
	public static void main(String a[]) throws Exception {
		
		
		PersonSearchService personSearchService = new PersonSearchService();
		Person person = new Person();
		
		person.setPersonID(a[0]);
		person.setPersonName(a[1]);
		
		if(a[2] != null && a[2].equals("UseMapper"))
			personSearchService.searchPersonWithMapper(person);
		else	
			new GetPersonData().getPerson(person);

	}
	
	
	private Person searchPersonWithMapper (Person person){
		try {

			String statusCode = new GetPersonData().searchPersonWithMapper(person);
			
			logger.debug("Person Creation status "+ statusCode);
			
		} catch (Exception e) {
			
			logger.error("Error in persistData", e);
		}
	

		
		return person;
	}
}
