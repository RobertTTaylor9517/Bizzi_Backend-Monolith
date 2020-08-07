package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pages")
public class Page {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column
	private String title;
	
	@Column
	private String about;
	
	@Column
	private String headerImg = "https://parksadventure.com/wp-content/uploads/2017/10/header-image-1-2.png";
	
	@Column
	private String userImg = "https://randomuser.me/api/portraits/men/1.jpg";
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "page", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Link> links;
	
	public Page() {
	}
	
	

	protected Page(UUID id, String title, String about, String headerImg, String userImg, User user, List<Link> links) {
		this.id = id;
		this.title = title;
		this.about = about;
		this.headerImg = headerImg;
		this.userImg = userImg;
		this.user = user;
		this.links = links;
	}



	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getHeaderImg() {
		return headerImg;
	}

	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}



	@Override
	public int hashCode() {
		return Objects.hash(about, headerImg, id, links, title, user, userImg);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		return Objects.equals(about, other.about) && Objects.equals(headerImg, other.headerImg)
				&& Objects.equals(id, other.id) && Objects.equals(links, other.links)
				&& Objects.equals(title, other.title) && Objects.equals(user, other.user)
				&& Objects.equals(userImg, other.userImg);
	}



	@Override
	public String toString() {
		return "Page [id=" + id + ", title=" + title + ", about=" + about + ", headerImg=" + headerImg + ", userImg="
				+ userImg + ", user=" + user + ", links=" + links + "]";
	}

	

	
	
	
	
	
}
