package com.company.app.io.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class SubscriptionEntity {

	// Entity attributes

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriptions_generator")
	@SequenceGenerator(name = "subscriptions_generator", sequenceName = "subscriptions_sequence", allocationSize = 1)
	private long id;

	// Relationships

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "keyword_id")
	private KeywordEntity keyword;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private UserEntity user;

	// Default Constructor
	public SubscriptionEntity() {}

	// Setters & Getters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public KeywordEntity getKeyword() {
		return keyword;
	}

	public void setKeyword(KeywordEntity keyword) {
		this.keyword = keyword;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
