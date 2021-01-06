package com.company.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -6671347407969225029L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "user_name", nullable = false, length = 120)
	private String userName;
		
	@Column(nullable = false)
	private String encryptedPassword; 
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private UserAuthorityEntity authorities;
	
	
	@Column(nullable = false)
	private boolean emailVerificationStatus = false;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public UserAuthorityEntity getAuthorities() {
		return authorities;
	}

	public void setAuthorities(UserAuthorityEntity authorities) {
		this.authorities = authorities;
	}
	
}
