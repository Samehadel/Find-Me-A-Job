package com.company.app.ws.ui.models.request;

public class ConnectionRequestModel {

	private long senderId;
	private long recieverId;
	
	ConnectionRequestModel(){}

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
