package com.mylab.service;

import org.apache.log4j.Logger;


import com.mylab.dao.service.SavePersonData;
import com.mylab.json.PrsJSONParser;
import com.mylab.json.data.Person;

public class PersonCreationService {

final static Logger logger = Logger.getLogger(PersonCreationService.class);
	
	
	public static void main(String a[]) throws Exception {
		
		PrsJSONParser prsJsonParser = new PrsJSONParser();
		
		Person person = prsJsonParser.parseFile("PersonInfo.json");
		
		if(person != null)
			if(a[0] != null && a[0].equals("UseMapper"))
				persistDataWithMapper(person);
			else	
				persistData(person);
		else
			logger.debug("Person object is null");
		
	}
	
	public static void persistData(Person person){
		
		try {

			String statusCode = new SavePersonData().savePerson(person);
			
			logger.debug("Person Creation status "+ statusCode);
			
		} catch (Exception e) {
			
			logger.error("Error in persistData", e);
		}
		
	}
	
public static void persistDataWithMapper(Person person){
		
		try {

			String statusCode = new SavePersonData().saveWihMapper(person);
			
			logger.debug("Person Creation status "+ statusCode);
			
		} catch (Exception e) {
			
			logger.error("Error in persistData", e);
		}
	
	}

	
}
