/*
 * JSON Parser to parse the JSON Object 
 */
package com.mylab.json;

import org.json.simple.JSONObject;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mylab.json.data.Person;
import com.mylab.json.data.PersonAddress;
import com.mylab.json.data.PersonCommunication;
import com.mylab.json.data.PersonContact;
import com.mylab.json.jsoninf.ParserConstant;

import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class PrsJSONParser implements ParserConstant{
	
	
	final static Logger logger = Logger.getLogger(PrsJSONParser.class);
	
	Person person = new Person();
	
	PersonCommunication prsCommunication = new PersonCommunication();
	
	public static void main(String a[]) throws Exception {
		
		PrsJSONParser prsJsonParser = new PrsJSONParser();
		
		prsJsonParser.parseFile("PersonInfo.json");
		
	}
	
	 public Person parseFile(String fileName)throws Exception {
		 
		ClassLoader classLoader = getClass().getClassLoader();
			
		File jsonfile = new File(classLoader.getResource(fileName).getFile());
	
		ArrayList<Object> mailingAddressList = new ArrayList<>();
		ArrayList<Object> contactList = new ArrayList<>();
		FileReader jsf = null;
		
		try{
			jsf = new FileReader(jsonfile);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsf);
			
			person.setPersonName((String)jsonObject.get(PERSON_NAME));
			person.setPersonID((String)jsonObject.get(PERSON_ID));
			person.setPersonAge((Long)jsonObject.get(PERSON_AGE));
			person.setPersonDOB(new Date((Long)jsonObject.get(PERSON_DOB)));
			person.setPersonGender((String)jsonObject.get(PERSON_GENDER));
			
			JSONObject communication = (JSONObject)jsonObject.get(PERSON_COMMUNICATION);
			
			JSONArray mailAddress = (JSONArray)communication.get(PERSON_M_ADDRESS);
			
			Iterator<JSONObject> itr = null; 
			
			itr = mailAddress.iterator();
			JSONObject mobj= null;
			
			PersonAddress personAddress = null;
			
			while(itr.hasNext()){
				
				personAddress = new PersonAddress();
				mobj = itr.next();
				
				personAddress.setAddress1((String)mobj.get(PERSON_ADDRESS1));
				personAddress.setAddress2((String)mobj.get(PERSON_ADDRESS2));
				personAddress.setCity((String)mobj.get(PERSON_CITY));
				personAddress.setState((String)mobj.get(PERSON_STATE));
				personAddress.setCtry((String)mobj.get(PERSON_CTRY));
				personAddress.setZip((String)mobj.get(PERSON_ZIP));
				personAddress.setCurrentAddress((String)mobj.get(PERSON_CADDRESS));
				mailingAddressList.add(personAddress);
			}
			PersonAddress[] pAdd= new PersonAddress[mailingAddressList.size()];
			mailingAddressList.toArray(pAdd);
			prsCommunication.setPersonAddress(pAdd);
			
			JSONArray contact = (JSONArray)communication.get(PERSON_CONTACT);
			PersonContact prsContact = null;
			
			itr = contact.iterator();
			
			while(itr.hasNext()){
				
				prsContact = new PersonContact();
				
				mobj = itr.next();
				prsContact.setPhoneNumer((String)mobj.get(PERSON_PHONE_NUMBER));
				prsContact.setEmailID((String)mobj.get(PERSON_EMAIL_ID));
				contactList.add(prsContact);
			}
			PersonContact[] pC = new PersonContact[contactList.size()];
			contactList.toArray(pC);
			prsCommunication.setPersonContact(pC);
			person.setPersonCommunication(prsCommunication);
			
			//ToDo
			/*
			  1. Store the POJO in the DynamoDB table (variation: since we have a req to read and parse the string delimited values, I can store in a String Object instead of POJO
			  2. Create a Table in DynamoDb to store person data (personID, personData)
			  3. Write another program to read the DynamoDB table record and tokenize it based on the delimiter
			  4. Store the JSON file S3 and retrieve the file from S3
			*/
			
		}catch (FileNotFoundException fe){
				fe.printStackTrace();
				return null;
		} catch (IOException io) {
			io.printStackTrace();
			return null;
		} catch (ParseException pe){
			pe.printStackTrace();
			return null;
		} finally{
			mailingAddressList = null;
			contactList = null;
			
			if(jsf != null)
					jsf.close();
			jsf=null;
			
		}
		return person;	
	}
	 
	void debug(Person person){
		if(logger.isDebugEnabled()){
			logger.debug("PersonName : "+ person.getPersonName());
			logger.debug("PersonID : "+ person.getPersonID());
			logger.debug("PersonAge : "+ person.getPersonAge());
			logger.debug("PersonDOB : "+ person.getPersonDOB());
			logger.debug("PersonGender : "+ person.getPersonGender());
		}
	}
	
	void persistPersonData(Person person){
		
	}
	
}
	

