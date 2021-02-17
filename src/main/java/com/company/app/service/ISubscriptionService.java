package com.company.app.service;

import java.util.List;

import com.company.app.shared.dto.SubscriptionDto;

public interface ISubscriptionService {

	public boolean createSubscription(long userId, int keywordId);
	
	public List<SubscriptionDto> getSubscriptions(long userId);
	
	public void dropSubscription(long subscriptionId);
}
