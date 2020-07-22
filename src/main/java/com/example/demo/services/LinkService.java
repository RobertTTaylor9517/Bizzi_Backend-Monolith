package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Link;
import com.example.demo.entities.LinkType;
import com.example.demo.entities.Page;
import com.example.demo.repo.LinkRepository;
import com.example.demo.repo.PageRepository;

@Service
public class LinkService {
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private PageRepository pageRepository;
	
	public Link createLink(UUID userId, String name, LinkType type, String link) {
		Page page = pageRepository.findById(userId)
		.orElseThrow(() -> new NoSuchElementException("User not found"));
		
		Link newLink = new Link();
		
		newLink.setName(name);
		newLink.setType(type);
		newLink.setLink(link);
		newLink.setPage(page);
		
		return linkRepository.save(newLink);
		
		
	}
	
	public List<Link> getUserLinks(UUID userId){
		Page page = pageRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User not found"));
		
		return page.getLinks();
	}
}
