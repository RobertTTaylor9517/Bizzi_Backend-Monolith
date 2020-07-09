package com.example.demo.entities;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "links")
public class Link {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column
	private String name;
	
	@Column
	private String link;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column
	@Enumerated
	private LinkType type;

	protected Link() {
	}

	protected Link(UUID id, String name, String link, User user, LinkType type) {
		this.id = id;
		this.name = name;
		this.link = link;
		this.user = user;
		this.type = type;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LinkType getType() {
		return type;
	}

	public void setType(LinkType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, link, name, type, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		return Objects.equals(id, other.id) && Objects.equals(link, other.link) && Objects.equals(name, other.name)
				&& type == other.type && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", name=" + name + ", link=" + link + ", user=" + user + ", type=" + type + "]";
	}
	
	
	
}
