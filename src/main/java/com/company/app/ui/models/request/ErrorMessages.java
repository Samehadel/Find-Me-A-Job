package com.company.app.ui.models.request;

public enum ErrorMessages {

	USER_ALREADY_EXIST("Email Address Already Exist"),
	USER_NOT_FOUND("User Not Exist");

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
