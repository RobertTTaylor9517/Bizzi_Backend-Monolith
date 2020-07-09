package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Link;
import com.example.demo.entities.User;
import com.example.demo.repo.LinkRepository;
import com.example.demo.repo.UserRepository;

@RestController
public class MainController {
	
	private UserRepository userRepository;
	private LinkRepository linkRepository;
	
	@Autowired
	public MainController(UserRepository userRepository, LinkRepository linkRepository) {
		this.userRepository = userRepository;
		this.linkRepository = linkRepository;
	}

	protected MainController() {
	}
	
	//Method to create new user
	public User createUser() {
		return null;
	}
	
	//method to retreive user from database using user id
	public User getUser() {
		return null;
	}
	
	//Method to Edit user using id an information
	
	//Method to delete user using id
	
	
	
	//Method used to create new link using user id.
	public Link createLink() {
		return null;
	}
	
	//Method to delete link using link id
	
	

}
