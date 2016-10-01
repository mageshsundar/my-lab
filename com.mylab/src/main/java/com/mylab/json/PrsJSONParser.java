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
import com.mylab.json.util.JSONUtil;

import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

public class PrsJSONParser implements ParserConstant{
	
	
	final static Logger logger = Logger.getLogger(PrsJSONParser.class);
	
	Person person = new Person();
	
	
	
	
	public static void main(String a[]) throws Exception {
		
		PrsJSONParser prsJsonParser = new PrsJSONParser();
		
		prsJsonParser.parseFile("PersonInfo.json");
		
	}
	
	public Person parseFile(String fileName) throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		FileReader jsf = null;
		Person person = null;
		
		File jsonfile = new File(classLoader.getResource(fileName).getFile());

		try {
			jsf = new FileReader(jsonfile);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsf);
			
			String jsonStr = JSONUtil.jsonToString(jsonObject);
			
			
			String[] items = jsonStr.split("\\^K");
			
			for(int i=0; i< items.length; i++)
				logger.debug(items[i]);
			
			// person = convertToPerson(jsonObject);
		
		} catch (IOException io) {
			logger.error("IOException while Parsing", io);
		} catch (ParseException pe) {
			logger.error("ParserException while parsing the file ", pe);
		} catch (Exception e) {
			logger.error("Exception while parsing the file", e);
		} finally {
			if (jsf != null)
				jsf.close();
			jsf = null;

		}
		
		return person;
	}
	
	public Person convertToPerson(JSONObject jsonObject) {

		ArrayList<Object> mailingAddressList = new ArrayList<>();
		ArrayList<Object> contactList = new ArrayList<>();

		PersonCommunication[] prsCommunication = null;

		try {
			person.setPersonName((String) jsonObject.get(PERSON_NAME));
			person.setPersonID((String) jsonObject.get(PERSON_ID));
			person.setPersonAge((Long) jsonObject.get(PERSON_AGE));
			person.setPersonDOB(new Date((Long) jsonObject.get(PERSON_DOB)));
			person.setPersonGender((String) jsonObject.get(PERSON_GENDER));

			Iterator<JSONObject> itr, itr2 = null;
			JSONObject mobj, comJSONObj = null;

			JSONArray communication = (JSONArray) jsonObject.get(PERSON_COMMUNICATION);

			itr2 = communication.iterator();

			int communicationInc = 0;

			prsCommunication = new PersonCommunication[communication.size()];

			while (itr2.hasNext()) {
				comJSONObj = itr2.next();

				JSONArray mailAddress = (JSONArray) comJSONObj.get(PERSON_M_ADDRESS);

				itr = mailAddress.iterator();

				PersonAddress personAddress = null;

				while (itr.hasNext()) {

					personAddress = new PersonAddress();
					mobj = itr.next();

					personAddress.setAddress1((String) mobj.get(PERSON_ADDRESS1));
					personAddress.setAddress2((String) mobj.get(PERSON_ADDRESS2));
					personAddress.setCity((String) mobj.get(PERSON_CITY));
					personAddress.setState((String) mobj.get(PERSON_STATE));
					personAddress.setCtry((String) mobj.get(PERSON_CTRY));
					personAddress.setZip((String) mobj.get(PERSON_ZIP));
					personAddress.setCurrentAddress((String) mobj.get(PERSON_CADDRESS));
					mailingAddressList.add(personAddress);
				}
				PersonAddress[] pAdd = new PersonAddress[mailingAddressList.size()];
				mailingAddressList.toArray(pAdd);

				prsCommunication[communicationInc] = new PersonCommunication();

				prsCommunication[communicationInc].setPersonAddress(pAdd);

				JSONArray contact = (JSONArray) comJSONObj.get(PERSON_CONTACT);
				PersonContact prsContact = null;

				itr = contact.iterator();

				while (itr.hasNext()) {

					prsContact = new PersonContact();

					mobj = itr.next();
					prsContact.setPhoneNumer((String) mobj.get(PERSON_PHONE_NUMBER));
					prsContact.setEmailID((String) mobj.get(PERSON_EMAIL_ID));
					contactList.add(prsContact);
				}
				PersonContact[] pC = new PersonContact[contactList.size()];
				contactList.toArray(pC);

				prsCommunication[communicationInc].setPersonContact(pC);

				communicationInc++;

			}
			person.setPersonCommunication(prsCommunication);

		}catch(Exception e){
			logger.error("convertToPerson", e);
		}
		finally {
			mailingAddressList = null;
			contactList = null;

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
	
	
}
	

