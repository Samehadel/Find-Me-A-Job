package com.company.app.service.util.user_service_utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.app.io.entities.ConnectionEntity;
import com.company.app.io.entities.RequestEntity;
import com.company.app.io.entities.SubscriptionEntity;
import com.company.app.io.entities.UserEntity;
import com.company.app.repositery.ConnectionRepository;
import com.company.app.repositery.RequestRepository;
import com.company.app.repositery.SubscriptionRepository;

@Component
public class ProfilesServiceUtils {

	@Autowired
	SubscriptionRepository subscriptionRepo;
	
	@Autowired
	RequestRepository requestRepo; 
	
	@Autowired
	ConnectionRepository connectionRepo; 

	/*
		This method
	 */
	public Set<UserEntity> retrieveUsersBySubscriptions(List<SubscriptionEntity> subscriptions, long userId){
		
		// Step1: Extract similar subscriptions
		List<SubscriptionEntity> similarSubscriptions = retrieveSimilarSubscriptions(subscriptions, userId);
		
		// Store final users for return
		Set<UserEntity> users = new HashSet<>();

		// Step2: Extract users from similar subscriptions
		for(SubscriptionEntity sub: similarSubscriptions) { 
			users.add(sub.getUser());
		}
	
		// Step3: Exclude users in connection list of the user
		users = excludeConnectedProfiles(users, userId);
		
		// Step4: Exclude users in requested list of the user
		users = excludeRequestedProfiles(users, userId);
		
		return users;
	}
	
	private List<SubscriptionEntity> retrieveSimilarSubscriptions(List<SubscriptionEntity> subscriptions, long userId){
		List<Integer> keywords = new ArrayList<>();
		
		for(SubscriptionEntity sub: subscriptions) 
			keywords.add(sub.getKeyword().getId());
		
		List<SubscriptionEntity> subs = subscriptionRepo.findSimilarSubscriptions(keywords, userId);
		
		return subs; 
		
	}
	
	private Set<UserEntity> excludeConnectedProfiles(Set<UserEntity> users, long id){
		
		List<ConnectionEntity> userConnections = connectionRepo.findByUserId(id);
		List<UserEntity> excludedUsers = new ArrayList<UserEntity>();
		
		for(int i = 0; i < userConnections.size(); i++) {
			ConnectionEntity conn = userConnections.get(i);
			
			if(conn.getFirstUser().getId() != id)
				excludedUsers.add(conn.getFirstUser());
			else
				excludedUsers.add(conn.getSecondUser());
		}
		
		for(int i = 0; i < excludedUsers.size(); i++)
			users.remove(excludedUsers.get(i));
			
		
		return users;
	}
	
	private Set<UserEntity> excludeRequestedProfiles(Set<UserEntity> users, long id){
		
		List<RequestEntity> userRequests = requestRepo.findRequestByUserId(id);
		List<UserEntity> excludedUsers = new ArrayList<UserEntity>();
		
		for(int i = 0; i < userRequests.size(); i++) {
			RequestEntity req = userRequests.get(i);
			
			if(req.getReciever().getId() != id)
				excludedUsers.add(req.getReciever());
			else
				excludedUsers.add(req.getSender());
		}
		
		for(int i = 0; i < excludedUsers.size(); i++)
			users.remove(excludedUsers.get(i));
			
		
		return users;
	}
}
