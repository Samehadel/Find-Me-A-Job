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
@Table(name = "requests")
public class RequestEntity {

	// Entity attributes

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	
	// Relationships

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, 
							CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "sender_id")
	private UserEntity sender;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "reciever_id")
	private UserEntity reciever;

	
	
	// Default Constructor
	public RequestEntity() {}

	
	// Setters & Getters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getSender() {
		return sender;
	}

	public void setSender(UserEntity sender) {
		this.sender = sender;
	}

	public UserEntity getReciever() {
		return reciever;
	}

	public void setReciever(UserEntity reciever) {
		this.reciever = reciever;
	}
}
