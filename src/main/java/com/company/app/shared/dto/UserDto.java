package com.company.app.shared.dto;

import com.company.app.io.entities.UserAuthorityEntity;

import java.io.Serializable;

public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String encryptedPassword;
	private UserAuthorityEntity authority;
	private String virtualUserId;
	private boolean emailVerificationStatus = false;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public UserAuthorityEntity getAuthority() {
		return authority;
	}

	public void setAuthority(UserAuthorityEntity authority) {
		this.authority = authority;
	}

	public String getVirtualUserId() {
		return virtualUserId;
	}
	public void setVirtualUserId(String virtualUserId) {
		this.virtualUserId = virtualUserId;
	}
	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	
}
