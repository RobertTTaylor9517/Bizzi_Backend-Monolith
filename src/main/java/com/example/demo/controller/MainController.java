package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Link;
import com.example.demo.entities.LinkType;
import com.example.demo.entities.Page;
import com.example.demo.entities.User;
import com.example.demo.repo.LinkRepository;
import com.example.demo.repo.PageRepository;
import com.example.demo.services.LinkService;
import com.example.demo.services.PageService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/")
public class MainController {
	
//	private PageRepository pageRepository;
//	private LinkRepository linkRepository;
//	
//	@Autowired
//	public MainController(PageRepository pageRepository, LinkRepository linkRepository) {
//		this.pageRepository = pageRepository;
//		this.linkRepository = linkRepository;
//	}
//
//	protected MainController() {
//	}
	
	
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private UserService userService;
	
	
	//Method to create new user page
	@PostMapping("page")
	@ResponseStatus(HttpStatus.CREATED)
	public Object createPage(@RequestBody HashMap<String, String> page) {
		UUID userId = UUID.fromString(page.get("user_id"));
		UUID pageId = pageService.createPage(userId, page.get("title"), page.get("about"), page.get("headerImg"), page.get("userImg"));
		return pageId; 
	}
	//method to retreive page from database using page id
	@GetMapping(value = "page/{id}")
	public Object getUser(@PathVariable UUID id) {
		Object page = pageService.getPage(id);
		
		return page;
	}
	
	//Method to Edit user using id an information
	
	//Method to delete user using id
	
	
	
	//Method used to create new link using user id.
	@PostMapping("link")
	@ResponseStatus(HttpStatus.CREATED)
	public Link createLink(@RequestParam("page_id") UUID pageId, @RequestParam("name") String name, @RequestParam("type") LinkType type, @RequestParam("link") String link) {
		Link newLink = linkService.createLink(pageId, name, type, link);
		
		return newLink;
	}
	
	//Method to delete link using link id
	
	//Method to get links for specific user
	@GetMapping(value = "page/{id}/links")
	public List<Link> userLink(@PathVariable UUID id){
		List<Link>linkSet = linkService.getUserLinks(id);
		
		return linkSet;
	}
	
	@PostMapping(value = "login", produces = "application/json; charset=UTF-8")
	public Object login(@RequestBody User user) {
		Object token = userService.login(user.getEmail(), user.getPassword());
		
		
		return token;
		
	}
	
	@PostMapping(value = "signup", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Object signup(@RequestBody User user) {
		HashMap<String, Object> token = userService.signup(user.getEmail(), user.getPassword());
		
		return token;
	}
	

}
