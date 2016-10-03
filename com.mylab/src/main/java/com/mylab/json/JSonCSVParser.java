package com.mylab.json;

import org.json.simple.JSONObject;
import org.apache.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mylab.json.data.Person;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

public class JSonCSVParser {

	final static String ROW_SEPERATOR = "\n";
	final static String COL_SEPERATOR = "|";
	final static String COL_SEPERATOR_PATTERN = "[|]";

	final static Logger logger = Logger.getLogger(PrsJSONParser.class);

	Person person = new Person();

	public static void main(String a[]) throws Exception {

		JSonCSVParser jsonCSVParser = new JSonCSVParser();

		jsonCSVParser.parseFile("PersonInfo2.json");

	}

	public void parseFile(String fileName) throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		FileReader jsf = null;

		File jsonfile = new File(classLoader.getResource(fileName).getFile());

		try {
			jsf = new FileReader(jsonfile);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsf);

			String jsonStr = (String) jsonObject.get("Communication");
			parseString(jsonStr);
		} catch (ParseException pe) {
			logger.error("Problem in Parsing the file", pe);
		} catch (Exception e) {
			logger.error("Non Parser issue in Parsing the file", e);
		}
	}

	/*
	 * Parse the jsonString to split the header and rows based on the delimiter
	 * and store each record as key value pair in HashMap and store it in an
	 * ArrayList and return.
	 */
	private Queue<String[]> parseString(String jsonStr) throws Exception {
		CharSequence fromCS = "||";
		CharSequence toCS = "|''|";
		
		jsonStr = jsonStr.replace(fromCS, toCS); //To handle empty values passed
		
		String[] strArray = jsonStr.split(ROW_SEPERATOR);

		logger.debug("Total no of rows" + strArray.length);

		String header = strArray[0];

		String[] headerColumns = header.split(COL_SEPERATOR_PATTERN);
		
		logger.debug("Total no of headers" + headerColumns.length);
		
		Queue<String[]> queue = new LinkedList<String[]>();
		
		queue.add(headerColumns); //add the header 
		
		for (int i = 1; i < strArray.length; ++i) {
			String[] sArray = strArray[i].split(COL_SEPERATOR_PATTERN);
			queue.add(sArray); //add each record
		}
		
		return queue;
	}

}
