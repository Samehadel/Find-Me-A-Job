package com.company.app.ws.io.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "connections")
public class ConnectionEntity {

	// Entity attributes

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	
	// Relationships
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "first_user_id")
	private UserEntity firstUser;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "second_user_id")
	private UserEntity secondUser;

	// Default Constructor
	public ConnectionEntity() {}

	
	// Setters & Getters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getFirstUser() {
		return firstUser;
	}

	public void setFirstUser(UserEntity firstUser) {
		this.firstUser = firstUser;
	}

	public UserEntity getSecondUser() {
		return secondUser;
	}

	public void setSecondUser(UserEntity secondUser) {
		this.secondUser = secondUser;
	}
}
