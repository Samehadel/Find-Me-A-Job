package com.company.app.ws.io.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -6671347407969225029L;

	// Entity attributes

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "global_sequences", initialValue = 1)
	private long id;

	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Column(name = "user_name", nullable = false, length = 150)
	private String userName;

	@Column(name = "encrypted_password", nullable = false, length = 500)
	private String encryptedPassword;

	@Column(name = "email_verification_status", nullable = false)
	private boolean emailVerificationStatus = false;

	@Column(name = "virtual_user_id", nullable = false, length = 255)
	private String virtualUserId;

	
	// Relationships

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL) // 1- with 'user_authorities'
	private UserAuthorityEntity authority;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL) // 2- with 'user_details'
	private UserDetailsEntity userDetails;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // 3- with 'subscriptions'
	private List<SubscriptionEntity> subscriptions;

	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL) // 4- with 'requests'
	private List<RequestEntity> sentRequests;

	@OneToMany(mappedBy = "reciever", cascade = CascadeType.ALL) // 5- with 'requests'
	private List<RequestEntity> recievedRequests;

	@OneToMany(mappedBy = "firstUser", cascade = CascadeType.ALL) // 6- with 'connections'
	private List<ConnectionEntity> sentConnections;

	@OneToMany(mappedBy = "secondUser", cascade = CascadeType.ALL) // 7- with 'connections'
	private List<ConnectionEntity> recievedConnections;

	
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL) // 6- with 'publications'
	private List<PublicationEntity> sentPublications;

	@OneToMany(mappedBy = "reciever", cascade = CascadeType.ALL) // 7- with 'publications'
	private List<PublicationEntity> recievedPublications;
	
	
	// Default Constructor
	public UserEntity() {}

	// Setters & Getters


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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public String getVirtualUserId() {
		return virtualUserId;
	}

	public void setVirtualUserId(String virtualUserId) {
		this.virtualUserId = virtualUserId;
	}

	public UserAuthorityEntity getAuthority() {
		return authority;
	}

	public void setAuthority(UserAuthorityEntity authority) {
		this.authority = authority;
	}

	public UserDetailsEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsEntity userDetails) {
		this.userDetails = userDetails;
	}

	public List<SubscriptionEntity> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionEntity> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public List<RequestEntity> getSentRequests() {
		return sentRequests;
	}

	public void setSentRequests(List<RequestEntity> sentRequests) {
		this.sentRequests = sentRequests;
	}

	public List<RequestEntity> getRecievedRequests() {
		return recievedRequests;
	}

	public void setRecievedRequests(List<RequestEntity> recievedRequests) {
		this.recievedRequests = recievedRequests;
	}

	public List<ConnectionEntity> getSentConnections() {
		return sentConnections;
	}

	public void setSentConnections(List<ConnectionEntity> sentConnections) {
		this.sentConnections = sentConnections;
	}

	public List<ConnectionEntity> getRecievedConnections() {
		return recievedConnections;
	}

	public void setRecievedConnections(List<ConnectionEntity> recievedConnections) {
		this.recievedConnections = recievedConnections;
	}

	public List<PublicationEntity> getSentPublications() {
		return sentPublications;
	}

	public void setSentPublications(List<PublicationEntity> sentPublications) {
		this.sentPublications = sentPublications;
	}

	public List<PublicationEntity> getRecievedPublications() {
		return recievedPublications;
	}

	public void setRecievedPublications(List<PublicationEntity> recievedPublications) {
		this.recievedPublications = recievedPublications;
	}
	
	
	
	// Add subscription to the user
	public void addSubscription(SubscriptionEntity sub) {
		if (subscriptions == null)
			subscriptions = new ArrayList<>();

		subscriptions.add(sub);
	}

	// Add sent request to the user
	public void addSentRequest(RequestEntity request) {
		if (sentRequests == null)
			sentRequests = new ArrayList<>();

		sentRequests.add(request);
	}

	// Add recieved request to the user
	public void addRecievedRequest(RequestEntity request) {
		if (recievedRequests == null)
			recievedRequests = new ArrayList<>();

		recievedRequests.add(request);
	}

	// Add sent connection to the user
	public void addSentConnection(ConnectionEntity connection) {
		if (sentConnections == null)
			sentConnections = new ArrayList<>();

		sentConnections.add(connection);
	}

	// Add recieved connection to the user
	public void addRecievedConnection(ConnectionEntity connection) {
		if (recievedConnections == null)
			recievedConnections = new ArrayList<>();

		recievedConnections.add(connection);
	}

	// Add sent connection to the user
	public void addSentPublication(PublicationEntity publication) {
		if (sentPublications == null)
			sentPublications = new ArrayList<>();

		sentPublications.add(publication);
	}

	// Add recieved connection to the user
	public void addRecievedPublication(PublicationEntity publication) {
		if (recievedPublications == null)
			recievedPublications = new ArrayList<>();

		recievedPublications.add(publication);
	}
}
