package com.example.demo.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Link;

public interface LinkRepository extends JpaRepository<Link, UUID> {

}
