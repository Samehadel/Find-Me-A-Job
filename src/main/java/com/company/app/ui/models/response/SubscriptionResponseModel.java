package com.company.app.ui.models.response;

public class SubscriptionResponseModel {
	
	// Subscription id
	private long id;
	
	// Keyword description
	private String keywordDescription;
	
	public SubscriptionResponseModel() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKeywordDescription() {
		return keywordDescription;
	}

	public void setKeywordDescription(String keywordDescription) {
		this.keywordDescription = keywordDescription;
	}
	
}
