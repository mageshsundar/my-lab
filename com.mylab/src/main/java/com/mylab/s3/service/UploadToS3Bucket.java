package com.mylab.s3.service;

import java.io.File;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class UploadToS3Bucket {

	Logger logger = Logger.getLogger(UploadToS3Bucket.class);
	
	public void uploadFile(){
		final AmazonS3 s3 = new AmazonS3Client();
		try{
			ClassLoader classLoader = getClass().getClassLoader();
			
			String fileName = classLoader.getResource("PersonInfo.json").getPath();
			
			s3.putObject("PersonBucket", "Person",fileName);
		}catch(AmazonServiceException e){
			logger.error("Error in Uploading file to s3", e);
		}
		
	}
	
	
	private void debug(String message){
		if ( logger.isDebugEnabled())
			logger.debug(message);
	}
	
}
