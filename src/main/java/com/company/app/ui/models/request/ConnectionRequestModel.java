package com.company.app.ui.models.request;

public class ConnectionRequestModel {

	private long senderId;
	private long receiverId;
	
	ConnectionRequestModel(){}

	public ConnectionRequestModel(long senderId, long receiverId) {
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

}
