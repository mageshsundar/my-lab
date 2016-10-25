package com.mylab.json;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylab.json.data.DataInstanceElements;
import com.mylab.json.data.FeedData;

public class URIParser {

	ClassLoader classLoader = getClass().getClassLoader();

	void parseJSON() throws IOException {
		byte[] jsonData = readFile("/Users/mageshs/Learn/my-lab/com.mylab/src/main/resources/DataPipeFeed.json");
		ObjectMapper objectMapper = new ObjectMapper();
		FeedData[] feedData = objectMapper.readValue(jsonData, FeedData[].class);

		System.out.println("feedData count : "+feedData.length);

		readURI(feedData);

	}

	void readURI(FeedData[] feedData) {
		
		if(feedData == null) return;
		
		for(FeedData fd: feedData){
			if(fd.getDataSetId().equals("8") || fd.getDataSetId().equals("9")){
				DataInstanceElements[] dse = fd.getDatasetinstanceelements();
				System.out.println("DataInstanceElements count : "+  dse.length);
				
				for(DataInstanceElements dseChild: dse){
					String uri = dseChild.getUri();
					try {
						byte[] data = readFile(uri);
						compareData(data);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public byte[] readFile(String fileName) throws IOException {
		return Files
				.readAllBytes(Paths.get(fileName));
	}

	//TODO
	@Test
	void compareData(byte[] data) {
		byte[] gmData = new byte[1];
		/** Compare the data with the Golden Master data
		    Need to know the contents of the uri and GM data
		**/
		assertArrayEquals(gmData, data);
	}

	public static void main(String[] a) throws Exception {

		URIParser uriParser = new URIParser();
		uriParser.parseJSON();
		
		// Test
		Result result = JUnitCore.runClasses(URIParser.class);
		for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());

	}

}
