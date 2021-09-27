package com.company.app.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.io.entities.KeywordEntity;
import com.company.app.io.entities.SubscriptionEntity;
import com.company.app.io.entities.UserEntity;
import com.company.app.repositery.SubscriptionRepository;
import com.company.app.service.IKeywordService;
import com.company.app.service.ISubscriptionService;
import com.company.app.service.IUserService;
import com.company.app.shared.dto.SubscriptionDto;

@Service
public class SubscriptionServiceImplementation implements ISubscriptionService {

	@Autowired
	IUserService userService; 
	
	@Autowired
	IKeywordService keywordService;
	
	@Autowired
	SubscriptionRepository subscriptionRepo; 
	
	
	@Override
	public boolean createSubscription(long userId, int keywordId) throws Exception {

		//Retrieve user
		UserEntity user = userService.retrieveUser(userId);
		
		//Retrieve keyword
		KeywordEntity keyword = keywordService.getKeywordById(keywordId);
		
		
		//create subscription
		SubscriptionEntity subscription = new SubscriptionEntity();
		
		/*Assign Relationships*/
		
		//Assign relationship between subscription and user (SUBSCRIPTION SIDE)
		subscription.setUser(user);
		
		//Assign relationship between subscription and keyword (SUBSCRIPTION SIDE)
		subscription.setKeyword(keyword);
		
		//Assign relationship between user and subscription (USER SIDE)
		user.addSubscription(subscription);
		
		//Assign relationship between keyword and subscription (KEYWORD SIDE)
		keyword.addSubscription(subscription);
		
		//Save subscription and check for nullness
		if(subscriptionRepo.save(subscription) != null) return true;
		
		return false;
	}

	@Override
	public List<SubscriptionDto> getSubscriptions(long userId) {

		List<SubscriptionDto> subscriptionsDto = new ArrayList<>();
				
		//Use repository
		List<SubscriptionEntity> subscriptions = subscriptionRepo.findAllByUserId(userId);
		
		for(SubscriptionEntity sub: subscriptions) {
			SubscriptionDto dto = new SubscriptionDto();
			
			//Copy data from entity to DTO
			dto.setId(sub.getId());
			dto.setKeywordDescription(sub.getKeyword().getDescription());
			
			subscriptionsDto.add(dto);
		}
		return subscriptionsDto;
	}

	@Override
	public void dropSubscription(long subscriptionId) {
		
		//Create new subscription to be deleted
		SubscriptionEntity subscription = new SubscriptionEntity();
		
		//Assign id to be dropped
		subscription.setId(subscriptionId);
		
		//Drop subscription
		subscriptionRepo.delete(subscription);
		
	}

}
