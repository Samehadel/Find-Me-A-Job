package com.company.app.shared.dto;

import java.util.List;

public class PublicationDto {
	
	private long id;
	private long senderId;
	private String content;
	private String link;
	private List<Integer> keywords;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSenderId() {
		return senderId;
	}
	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<Integer> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<Integer> keywords) {
		this.keywords = keywords;
	}
	
}
