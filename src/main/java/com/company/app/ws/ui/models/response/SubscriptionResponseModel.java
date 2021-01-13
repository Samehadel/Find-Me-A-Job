package com.company.app.ws.ui.models.response;

public class SubscriptionResponseModel {
	
	// Subscription id
	private long id;
	
	// Keyword description
	private String keyword;
	
	public SubscriptionResponseModel() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
