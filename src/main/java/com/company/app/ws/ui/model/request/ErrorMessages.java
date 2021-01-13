package com.company.app.ws.ui.model.request;

public enum ErrorMessages {

	USER_ALREADY_EXIST("Email Address Already Exist");
	
	private String errorMessage; 
	
	private ErrorMessages(String error) {
		this.errorMessage = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
