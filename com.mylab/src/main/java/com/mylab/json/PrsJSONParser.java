/*
 * JSON Parser to parse the JSON Object 
 */
package com.mylab.json;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mylab.json.jsoninf.ParserConstant;

import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class PrsJSONParser implements ParserConstant{
	
	public static void main(String a[]) throws Exception {
		
		PrsJSONParser prsJsonParser = new PrsJSONParser();
		
		
		prsJsonParser.parseFile("PersonInfo.json");
		
	}
	
	 boolean parseFile(String fileName) {
		 
		ClassLoader classLoader = getClass().getClassLoader();
			
		File jsonfile = new File(classLoader.getResource(fileName).getFile());

		
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		
		HashMap<String,String> mailingAddressMap = new HashMap<String, String>();
		HashMap<String,String> contactMap = new HashMap<String, String>();
		
		ArrayList<Object> mailingAddressList = new ArrayList<>();
		ArrayList<Object> contactList = new ArrayList<>();
		
		try{
			FileReader jsf = new FileReader(jsonfile);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsf);
			
			String personName = (String)jsonObject.get(PERSON_NAME);
			System.out.println(personName);
			jsonMap.put(PERSON_NAME, personName);
			
			String personID = (String)jsonObject.get(PERSON_ID);
			System.out.println(personID);
			jsonMap.put(PERSON_ID, personID);
			
			Long personAge = (Long)jsonObject.get(PERSON_AGE);
			System.out.println(personAge);
			jsonMap.put(PERSON_AGE, personAge);
			
			Date personDOB = new Date((Long)jsonObject.get(PERSON_DOB));
			System.out.println(personDOB);
			jsonMap.put(PERSON_DOB, personDOB);
			
			String personGender = (String)jsonObject.get(PERSON_GENDER);
			System.out.println(personGender);
			jsonMap.put(PERSON_GENDER, personGender);
			
			JSONObject communication = (JSONObject)jsonObject.get(PERSON_COMMUNICATION);
			
			JSONArray mailAddress = (JSONArray)communication.get(PERSON_M_ADDRESS);
			
			Iterator itr = null; 
					itr = mailAddress.iterator();
			JSONObject mobj= null;
			
			while(itr.hasNext()){
			
				mobj = (JSONObject)itr.next();
				mailingAddressMap.put(PERSON_ADDRESS1, (String)mobj.get(PERSON_ADDRESS1));
				mailingAddressMap.put(PERSON_ADDRESS2, (String)mobj.get(PERSON_ADDRESS2));
				mailingAddressMap.put(PERSON_CITY, (String)mobj.get(PERSON_CITY));
				mailingAddressMap.put(PERSON_STATE, (String)mobj.get(PERSON_STATE));
				mailingAddressMap.put(PERSON_CTRY, (String)mobj.get(PERSON_CTRY));
				mailingAddressMap.put(PERSON_ZIP, (String)mobj.get(PERSON_ZIP));
				mailingAddressMap.put(PERSON_CADDRESS, (String)mobj.get(PERSON_CADDRESS));
				mailingAddressList.add(mailingAddressMap);
			}
			
			System.out.println(mailingAddressList.size());
			
			JSONArray contact = (JSONArray)communication.get(PERSON_CONTACT);
			
			itr = contact.iterator();
			
			while(itr.hasNext()){
				
				mobj = (JSONObject)itr.next();
				contactMap.put(PERSON_PHONE_NUMBER, (String)mobj.get(PERSON_PHONE_NUMBER));
				contactMap.put(PERSON_EMAIL_ID, (String)mobj.get(PERSON_EMAIL_ID));
				contactList.add(mailingAddressMap);
			}
			
			System.out.println(contactList.size());
			
			jsf.close();
			jsf=null;
			
			//ToDo
			/*
			  1. Create a String delimited object and store the parsed data in the POJO instead of HashMap
			  2. Store the POJO in the DynamoDB table (variation: since we have a req to read and parse the string delimited values, I can store in a String Object instead of POJO
			  3. Create a Table in DynamoDb to store person data (personID, personData)
			  4. Write another program to read the DynamoDB table record and tokenize it based on the delimiter
			  5. Store the JSON file S3 and retrieve the file from S3
			*/
			
		}catch (FileNotFoundException fe){
				fe.printStackTrace();
				return false;
		} catch (IOException io) {
			io.printStackTrace();
			return false;
		} catch (ParseException pe){
			pe.printStackTrace();
			return false;
		}
		return true;	
	}
	
	
}
