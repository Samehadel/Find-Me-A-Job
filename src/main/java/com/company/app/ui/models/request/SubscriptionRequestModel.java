package com.company.app.ui.models.request;

public class SubscriptionRequestModel {
	
	private long userId;
	private int keywordId;
	
	public SubscriptionRequestModel() {}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}
	
}
