package com.example.demo.services;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Page createPage(UUID userId, String title, String about, String headerImg, String userImg) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User not found"));
		
		Page newPage = new Page();
		
		newPage.setTitle(title);
		newPage.setAbout(about);
		newPage.setHeaderImg(headerImg);
		newPage.setUserImg(userImg);
		newPage.setUser(user);
		
		return pageRepository.save(newPage);
	}
	
	public Page getPage(UUID id) {
		Page page = pageRepository.findById(id).orElseThrow(()->
		new NoSuchElementException("Page not found..."));
		
		return page;
	}
	
	
}
