package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Link;
import com.example.demo.entities.LinkType;
import com.example.demo.entities.Page;
import com.example.demo.repo.LinkRepository;
import com.example.demo.repo.PageRepository;
import com.example.demo.services.LinkService;
import com.example.demo.services.PageService;

@RestController
@RequestMapping("/api/")
public class MainController {
	
	private PageRepository pageRepository;
	private LinkRepository linkRepository;
	
	@Autowired
	public MainController(PageRepository pageRepository, LinkRepository linkRepository) {
		this.pageRepository = pageRepository;
		this.linkRepository = linkRepository;
	}

	protected MainController() {
	}
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private LinkService linkService;
	
	
	//Method to create new user page
	@PostMapping("user")
	@ResponseStatus(HttpStatus.CREATED)
	public Page createPage(@RequestParam("title") String title, @RequestParam("about") String about, @RequestParam("headerImg") String headerImg, @RequestParam("userImg") String userImg) {
		Page newUser = pageService.createPage(title, about, headerImg, userImg);
		return newUser; 
	}
	//method to retreive page from database using page id
	@GetMapping(value = "user/{id}")
	public Page getUser(@PathVariable UUID id) {
		Page page = pageService.getPage(id);
		
		return page;
	}
	
	//Method to Edit user using id an information
	
	//Method to delete user using id
	
	
	
	//Method used to create new link using user id.
	@PostMapping("link")
	@ResponseStatus(HttpStatus.CREATED)
	public Link createLink(@RequestParam("user_id") UUID userId, @RequestParam("name") String name, @RequestParam("type") LinkType type, @RequestParam("link") String link) {
		Link newLink = linkService.createLink(userId, name, type, link);
		
		return newLink;
	}
	
	//Method to delete link using link id
	
	//Method to get inks for specific user
	@GetMapping(value = "user/{id}/links")
	public List<Link> userLink(@PathVariable UUID id){
		List<Link>linkSet = linkService.getUserLinks(id);
		
		return linkSet;
	}
	
	

}
