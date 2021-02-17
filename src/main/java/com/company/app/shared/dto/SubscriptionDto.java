package com.company.app.shared.dto;

public class SubscriptionDto {

	private long id;
	private int keywordId;
	private long userId;
	private String keywordDescription;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getKeywordId() {
		return keywordId;
	}
	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getKeywordDescription() {
		return keywordDescription;
	}
	public void setKeywordDescription(String keywordDescription) {
		this.keywordDescription = keywordDescription;
	}
	
}
