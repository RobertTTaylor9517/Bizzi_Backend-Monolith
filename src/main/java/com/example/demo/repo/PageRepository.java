package com.example.demo.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Page;

public interface PageRepository extends JpaRepository<Page, UUID>{
	
}
