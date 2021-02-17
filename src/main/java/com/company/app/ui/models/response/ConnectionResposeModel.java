package com.company.app.ui.models.response;

public class ConnectionResposeModel {

	private long id;
	private long senderId;
	private long recieverId;
	
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
	public long getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(long recieverId) {
		this.recieverId = recieverId;
	}
	
}
