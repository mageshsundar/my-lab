package com.mylab.json.util;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
import org.apache.log4j.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mylab.dao.PersonDAOConstant;
import com.mylab.json.data.Person;
import com.mylab.json.data.PersonAddress;
import com.mylab.json.data.PersonCommunication;
import com.mylab.json.data.PersonContact;

public class JSONUtil implements PersonDAOConstant {
	
	final static Logger logger = Logger.getLogger(JSONUtil.class);
	

	public static String jsonToString(JSONObject jsonObject) {
		StringBuilder sb = new StringBuilder();
		parsejObject(sb, null, jsonObject);
		return sb.toString();
	}
	
	public static void parsejObject(StringBuilder sb, String key, Object object) {

		if (object instanceof JSONObject) {

			JSONObject tempObj = (JSONObject) object;
			
			Set<String> set = tempObj.keySet();

			if (set != null) {
				logger.debug("Size of keyset " + set.size());

				Iterator<String> itr = set.iterator();
				
				 while(itr.hasNext()){
					 key = itr.next();
					 Object object_1 = tempObj.get(key);
					 parsejObject(sb, key, object_1);
					 
				 }
				
			}

		} else if (object instanceof JSONArray) {

			JSONArray jsonArray = (JSONArray) object;

			logger.debug("jsonToString is Array " + key);

			parseArray(sb, key, jsonArray);

		} else {

			parseKey(sb, key, object);
		}
		
	}
	
	public static void parseKey(StringBuilder sb, String key, Object object) {
		String value = String.valueOf(object);
		sb.append("^K").append(key).append("^V").append(value);

		logger.debug("parseKey " + sb.toString());
	}
	
	public static void parseArray(StringBuilder sb, String key, JSONArray jsonArray) {

		ListIterator<Object> ltr = jsonArray.listIterator();

		while (ltr.hasNext()) {
			Object _object = ltr.next();

			if (_object instanceof JSONObject) {
				parsejObject(sb, key, _object);
			} else if (_object instanceof JSONArray) {
				parseArray(sb, key, (JSONArray) _object);
			} else {
				parseKey(sb, key, _object);
			}
		}

	}
	
	//TODO:
	public static JSONObject stringToJSON() {
		JSONObject jsonObject = new JSONObject();
		
	
		return jsonObject;
	}
	
	public static String convertJSONPersonObj(JSONObject jsonObject){
		String jsonString = null;
		
		
		return jsonString;
	}
	
	
	public static String marshallPerson(Person person) {
		StringBuilder pSB = new StringBuilder();
		
		if (person == null)
			return null;

		/*for (PersonAddress pA : pAddress) {
			pAddrSB.append(pA.getAddress1()).append(ADDRESS_SEPARATOR).append(pA.getAddress2())
					.append(ADDRESS_SEPARATOR).append(pA.getCity()).append(ADDRESS_SEPARATOR).append(pA.getState())
					.append(ADDRESS_SEPARATOR).append(pA.getCtry()).append(ADDRESS_SEPARATOR).append(pA.getZip())
					.append(ADDRESS_SEPARATOR).append(pA.getCurrentAddress());
			pAddrSB.append(ADDRESS_RECORD_SEPERATOR);
		}
		 */
		return pSB.toString();
	}
	
	public static String marshallPersonAddress(PersonCommunication pC) {
		StringBuilder pAddrSB = new StringBuilder();
		PersonAddress[] pAddress = pC.getPersonAddress();

		if (pAddress == null)
			return null;

		for (PersonAddress pA : pAddress) {
			pAddrSB.append(pA.getAddress1()).append(ADDRESS_SEPARATOR).append(pA.getAddress2())
					.append(ADDRESS_SEPARATOR).append(pA.getCity()).append(ADDRESS_SEPARATOR).append(pA.getState())
					.append(ADDRESS_SEPARATOR).append(pA.getCtry()).append(ADDRESS_SEPARATOR).append(pA.getZip())
					.append(ADDRESS_SEPARATOR).append(pA.getCurrentAddress());
			pAddrSB.append(ADDRESS_RECORD_SEPERATOR);
		}

		return pAddrSB.toString();
	}
	
	
	public static String marshallPersonContact(PersonCommunication pC, String personAddressArr) {
		StringBuilder pContactSB = new StringBuilder();
		PersonContact[] pContact = pC.getPersonContact();

		if (pContact == null)
			return null;

		for (PersonContact pCI : pContact) {
			pContactSB.append(pCI.getEmailID()).append(CONTACT_SEPARATOR).append(pCI.getPhoneNumer());
			pContactSB.append(CONTACT_RECORD_SEPARATOR);

		}
		return pContactSB.toString();
	}
	
}
