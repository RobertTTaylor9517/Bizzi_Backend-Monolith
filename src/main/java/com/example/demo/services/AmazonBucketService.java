package com.example.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.s3.AmazonS3ClientService;

@Service
public class AmazonBucketService {
	
	@Autowired
	private AmazonS3ClientService amazonS3ClientService;
	
	public Map<String, String> uploadFile(MultipartFile file){
		String link = this.amazonS3ClientService.uploadFileToS3Bucket(file, true);
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("link", link);
		
		return response;
	}
}
