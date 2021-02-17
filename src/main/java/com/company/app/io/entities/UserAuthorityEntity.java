package com.company.app.io.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "user_authorities")
public class UserAuthorityEntity {

	//Entity attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_generator")
	@SequenceGenerator(name = "auth_generator", sequenceName = "auth_sequence", allocationSize = 1)
	private long id;
	
	@Column(name = "role", nullable = false, length = 50)
	private String role;

	//Relationships
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	//Default Constructor
	public UserAuthorityEntity() {}
	
	
	public UserAuthorityEntity(String role) {
		super();
		this.role = role;
	}


	//Setters & Getters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	} 	
}
