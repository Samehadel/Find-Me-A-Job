package com.company.app.io.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "publications")
public class PublicationEntity {

	//Entity attributes

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publications_generator")
	@SequenceGenerator(name = "publications_generator", sequenceName = "publications_sequence", allocationSize = 1)
	private long id;

	@Column(name = "content", nullable = true, length = 500)
	private String content;
	
	@Column(name = "link", nullable = true)
	private String link;
	
	
	//Relationships
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "sender_id")
	private UserEntity sender; 
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "reciever_id")
	private UserEntity reciever; 


	//Default Constructor
	public PublicationEntity() {}


	//Setters & Getters

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
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
