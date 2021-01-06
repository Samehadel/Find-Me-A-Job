package com.company.app.ws.ui.model.response;

public class UserRest {

	private int userId;
	private String firstName;
	private String lastName;
	private String authority;
	private String jwt;
	
	public UserRest() {}
	public UserRest(int userId, String jwt) {
		super();
		this.userId = userId;
		this.jwt = jwt;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}
