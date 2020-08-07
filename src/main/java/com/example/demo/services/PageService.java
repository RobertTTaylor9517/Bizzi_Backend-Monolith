package com.example.demo.services;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Page;
import com.example.demo.entities.User;
import com.example.demo.repo.PageRepository;
import com.example.demo.repo.UserRepository;

@Service
public class PageService {
	
	@Autowired
	private PageRepository pageRepository;
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	AmazonBucketService amazonBucketService;
	
	public UUID createPage(UUID userId, String title, String about, String headerImg, String userImg) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User not found"));
		
		Page newPage = new Page();
		
		newPage.setTitle(title);
		newPage.setAbout(about);
		newPage.setUser(user);
		
		Page page = pageRepository.save(newPage);
		return page.getId();
	}
	
	public String updatePagePort(UUID id, MultipartFile file) {
		Page page = pageRepository.getOne(id);
		Map<String, String> ret = this.amazonBucketService.uploadFile(file);
		
		page.setUserImg(ret.get("link"));
		pageRepository.save(page);
		
		return ret.get("link");
	}
	
	public String updatePageHead(UUID id, MultipartFile file) {
		Page page = pageRepository.getOne(id);
		Map<String, String> ret = this.amazonBucketService.uploadFile(file);
		
		page.setHeaderImg(ret.get("link"));
		pageRepository.save(page);
		
		return ret.get("link");
	}
	
	public Page getPage(UUID id) {
		Page page = pageRepository.findById(id).orElseThrow(()->
		new NoSuchElementException("Page not found..."));
		
		return page;
	}
	
	
}
