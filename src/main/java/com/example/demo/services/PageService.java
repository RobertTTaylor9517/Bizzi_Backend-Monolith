package com.example.demo.services;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Page;
import com.example.demo.repo.PageRepository;

@Service
public class PageService {
	
	@Autowired
	private PageRepository pageRepository;
	
	public Page createPage(String title, String about, String headerImg, String userImg) {
		Page newPage = new Page();
		
		newPage.setTitle(title);
		newPage.setAbout(about);
		newPage.setHeaderImg(headerImg);
		newPage.setUserImg(userImg);
		
		return pageRepository.save(newPage);
	}
	
	public Page getPage(UUID id) {
		Page page = pageRepository.findById(id).orElseThrow(()->
		new NoSuchElementException("Page not found..."));
		
		return page;
	}
	
	
}
