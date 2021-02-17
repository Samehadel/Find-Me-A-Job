package com.company.app.io.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "keywords")
public class KeywordEntity {

	//Entity attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "keywords_generator")
	@SequenceGenerator(name = "keywords_generator", sequenceName = "keywords_sequence", allocationSize = 1)
	private int id;

	@Column(name = "description", nullable = false, length = 30)
	private String description;


	
	//Relationships
	
	@OneToMany(mappedBy = "keyword", cascade = CascadeType.ALL)
	private List<SubscriptionEntity> subscriptions; 
	
	//Default Constructor
	public KeywordEntity() {}

	
	
	//Setters & Getters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SubscriptionEntity> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionEntity> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
	
	
	//Add subscriptions to the keyword
	public void addSubscription(SubscriptionEntity sub) {
		if(subscriptions == null)
			subscriptions = new ArrayList<>();
		
		subscriptions.add(sub);
	}

}
