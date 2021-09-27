package com.company.app.ui.models.request;

import java.util.List;

public class PublicationRequestModel {
	
	private String content;
	private String link;
	private List<Integer> keywords;
	
	public PublicationRequestModel(){}

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
