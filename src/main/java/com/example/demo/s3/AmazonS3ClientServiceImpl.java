package com.example.demo.s3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class AmazonS3ClientServiceImpl implements AmazonS3ClientService{
	private String awsS3AudioBucket;
	private AmazonS3 amazonS3;
	private static final Logger logger = LoggerFactory.getLogger(AmazonS3ClientServiceImpl.class);
	
	@Autowired
	public AmazonS3ClientServiceImpl(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider, String awsS3AudioBucket) {
		this.amazonS3 = AmazonS3ClientBuilder.standard()
				.withCredentials(awsCredentialsProvider)
				.withRegion(awsRegion.getName()).build();
		this.awsS3AudioBucket = awsS3AudioBucket;
		
	}
	
	@Async
	public String uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess) {
		String fileName = UUID.randomUUID().toString() + ".jpg";
		String res = "https://elasticbeanstalk-us-east-2-997409331678.s3.us-east-2.amazonaws.com/" + fileName;
		
		try {
			//Temporarily creating file in server
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();
			
			PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3AudioBucket, fileName, file);
			
			if(enablePublicReadAccess) {
				putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
				
			}
			this.amazonS3.putObject(putObjectRequest);
			//removing file from server
			file.delete();
			
			
			
			
		} catch (IOException | AmazonServiceException ex) {
			logger.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
		}
		return res;
		
	}

	@Async
	public void deleteFileFromS3Bucket(String fileName) {
		try {
			amazonS3.deleteObject(new DeleteObjectRequest(awsS3AudioBucket, fileName));
		} catch (AmazonServiceException ex) {
			logger.error("error [" + ex.getMessage() + "] occurred while deleting [" + fileName + "] ");
		}
		
	}
}
