package com.example.demo.entities;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	private String email;
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Page> pages;
	

	public User() {
	}
	

	protected User(UUID id, String email, String password, List<Page> pages) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.pages = pages;
	}



	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, pages, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(pages, other.pages)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", pages=" + pages + "]";
	}
	
	
}
