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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id")
	private Page page;
	
	@Column
	@Enumerated
	private LinkType type;

	public Link() {
	}

	protected Link(UUID id, String name, String link, Page page, LinkType type) {
		this.id = id;
		this.name = name;
		this.link = link;
		this.page = page;
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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public LinkType getType() {
		return type;
	}

	public void setType(LinkType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, link, name, type, page);
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
				&& type == other.type && Objects.equals(page, other.page);
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", name=" + name + ", link=" + link + ", user=" + page + ", type=" + type + "]";
	}
	
	
	
}
