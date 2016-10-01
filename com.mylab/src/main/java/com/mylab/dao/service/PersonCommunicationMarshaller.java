package com.mylab.dao.service;

import org.apache.log4j.Logger;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import com.mylab.dao.PersonDAOConstant;
import com.mylab.json.data.PersonAddress;
import com.mylab.json.data.PersonCommunication;
import com.mylab.json.data.PersonContact;
import com.mylab.json.util.JSONUtil;


//TODO: Replace with DynamoDBTypeConverter<S,T>
public class PersonCommunicationMarshaller implements DynamoDBMarshaller<PersonCommunication>, PersonDAOConstant {
	static final Logger logger = Logger.getLogger(PersonCommunicationMarshaller.class);

	@Override
	public String marshall(PersonCommunication pC) {
		
		String personAddressArr = JSONUtil.marshallPersonAddress(pC);

		String personContactArr = JSONUtil.marshallPersonContact(pC, personAddressArr);

		String sb = new StringBuilder().append(personAddressArr).append(PERSON_COMM_DELIM).append(personContactArr)
				.toString();

		debug("Final String " + sb);

		return sb;
	}

	@Override
	public PersonCommunication unmarshall(Class<PersonCommunication> personCommunication, String ms) {
		PersonCommunication pcm = null;

		if (ms == null)
			return pcm;
		
		
		pcm = new PersonCommunication();

		// Split the string into an array of PersonAddress and PersonContact
		String[] msArray = ms.split(PERSON_COMM_DELIM);

		if (msArray != null)
			debug("Totol PC length " + msArray.length);

		int size = msArray.length;

		String[] sAA = null, sCA = null;
		// Split the Address array string and Contact array string in separate
		// ArrayLists
		for (int i = 0; i < size; i++) {
			if (msArray[i].contains(ADDRESS_SEPARATOR)) {
				sAA = msArray[i].split(ADDRESS_RECORD_SEPERATOR);
			} else if (msArray[i].contains(CONTACT_SEPARATOR)) {
				sCA = msArray[i].split(CONTACT_RECORD_SEPARATOR);
			}
		}

		String[] s1 = null;
		/* Parse the String and create Person Address object */
		if (sAA != null) {
			debug("Size of Address array " + sAA.length);
			PersonAddress[] pa = new PersonAddress[sAA.length];
			for (int i = 0; i < sAA.length; i++) {
				s1 = sAA[i].split(ADDRESS_SEPARATOR);
				pa[i] = new PersonAddress();
				pa[i].setAddress1(s1[0]);
				pa[i].setAddress2(s1[1]);
				pa[i].setCity(s1[2]);
				pa[i].setState(s1[3]);
				pa[i].setCtry(s1[4]);
				pa[i].setZip(s1[5]);
				pa[i].setCurrentAddress(s1[6]);
			}
			pcm.setPersonAddress(pa);
		}

		/* Parse the String and create Person Contact object */
		if (sCA != null) {
			debug("Size of Contact array " + sCA.length);
			PersonContact[] pc = new PersonContact[sCA.length];

			for (int i = 0; i < sCA.length; i++) {
				s1 = sCA[i].split(CONTACT_SEPARATOR);
				pc[i] = new PersonContact();
				pc[i].setEmailID(s1[0]);
				pc[i].setPhoneNumer(s1[1]);
			}
			pcm.setPersonContact(pc);
		}

		return pcm;
	}
	
	public void debug(String debugMsg) {
		if (logger.isDebugEnabled())
			logger.debug(debugMsg);
	}

}
