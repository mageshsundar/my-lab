package com.mylab.s3.service;

import org.apache.log4j.Logger;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

public class CreateS3ObjectService {
	
	private final Logger logger = Logger.getLogger(CreateS3ObjectService.class);
	
	public static void main(String[] a) throws Exception {
		CreateS3ObjectService s3client = new CreateS3ObjectService();
		s3client.createBucket();
	}
	
	public void createBucket() throws Exception {
			final AmazonS3 s3 = new AmazonS3Client();
			try{
				Bucket bucket = s3.createBucket("personbucket");
			}catch (Exception e){
				logger.error("Error in creating S3 Object", e);
				throw e;
			}
			debug("Bucket created successfully");
	}
	
	private void debug(String message){
		if ( logger.isDebugEnabled())
			logger.debug(message);
	}
	
}
