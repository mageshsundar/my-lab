package com.mylab.dao.service;


import org.apache.log4j.Logger;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import com.mylab.dao.PersonDAOConstant;
import com.mylab.json.data.PersonAddress;
import com.mylab.json.data.PersonCommunication;
import com.mylab.json.data.PersonContact;

public class PersonCommunicationMarshaller implements DynamoDBMarshaller<PersonCommunication>, PersonDAOConstant {

	static final Logger logger = Logger.getLogger(PersonCommunicationMarshaller.class);

	@Override
	public String marshall(PersonCommunication pC) {
		// TODO Auto-generated method stub
		String personAddressArr = marshallPersonAddress(pC);
		debug("personAddressArr "+ personAddressArr );
		
		String personContactArr = marshallPersonContact(pC, personAddressArr);
		debug("personContactArr "+ personContactArr );
		
		String sb = new StringBuilder().append(personAddressArr).append(PERSON_COMM_DELIM).append(personContactArr)
				.toString();
		debug("Final String "+ sb );
		return sb;
	}

	@Override
	public PersonCommunication unmarshall(Class<PersonCommunication> personCommunication, String ms) {
		PersonCommunication pcm = null;

		if (ms == null)
			return pcm;
		pcm = new PersonCommunication();

		//Split the string into an array of PersonAddress and PersonContact
		String[] msArray = ms.split(PERSON_COMM_DELIM);

		if (msArray != null)
			debug("Totol PC length " + msArray.length);
		
		int size = msArray.length;
		

		
		String[] sAA = null, sCA = null;
		// Split the Address array string and Contact array string in separate ArrayLists
		for (int i = 0; i < size; i++) {
			if (msArray[i].contains(ADDRESS_SEPARATOR)) {
				sAA = msArray[i].split(ADDRESS_RECORD_SEPERATOR);
			} else if(msArray[i].contains(CONTACT_SEPARATOR)) {
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
		if(sCA != null) {
			debug("Size of Contact array " + sCA.length);
			PersonContact[] pc = new PersonContact[sCA.length];
			
			for (int i=0; i< sCA.length; i++){
				s1 = sCA[i].split(CONTACT_SEPARATOR);
				pc[i] = new PersonContact();
				pc[i].setEmailID(s1[0]);
				pc[i].setPhoneNumer(s1[1]);
			}
			pcm.setPersonContact(pc);
		}
		
		return pcm;
	}

	private String marshallPersonContact(PersonCommunication pC, String personAddressArr) {
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


	private String marshallPersonAddress(PersonCommunication pC) {
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

	public void debug(String debugMsg){
		if(logger.isDebugEnabled())
			logger.debug(debugMsg);
	}
	
}
