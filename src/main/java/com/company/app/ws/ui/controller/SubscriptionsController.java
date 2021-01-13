package com.company.app.ws.ui.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.ws.ui.models.request.SubscriptionRequestModel;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionsController {

	@PostMapping("/subscribe")
	public void subscribe(@RequestBody SubscriptionRequestModel subscriptionbody) {
		
	}
	
	@GetMapping("/{userId}")
	public List<SubscriptionRequestModel> accessSubscriptions(@PathVariable long userId){
		
		return null;
	}
	
	@DeleteMapping("/{keywordId}/{userId}")
	public void removeSubscribedKeywords(@PathVariable int keywordId, @PathVariable long userId) {
		
	}
}
