package com.company.app.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.service.ISubscriptionService;
import com.company.app.shared.dto.SubscriptionDto;
import com.company.app.ui.models.request.SubscriptionRequestModel;
import com.company.app.ui.models.response.SubscriptionResponseModel;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionsController {

	@Autowired
	private ISubscriptionService subscriptionService; 
	
	
	@PostMapping("/subscribe")
	public ResponseEntity subscribe(@RequestBody SubscriptionRequestModel subscriptionBody) {
		boolean check = subscriptionService.createSubscription(subscriptionBody.getUserId(), subscriptionBody.getKeywordId());

		return 	check ? ResponseEntity.status(HttpStatus.OK).build() :
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity accessSubscriptions(@PathVariable long userId){
		List<SubscriptionResponseModel> subscriptions = new ArrayList<>();
		List<SubscriptionDto> subscriptionsDto = subscriptionService.getSubscriptions(userId);
		
		for(SubscriptionDto dto: subscriptionsDto) {
			SubscriptionResponseModel responseModel = new SubscriptionResponseModel();
			
			// Copy values from DTO to response model
			BeanUtils.copyProperties(dto, responseModel);
			
			subscriptions.add(responseModel);
		}

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(subscriptions);
	}
	
	@DeleteMapping("/drop/{subscriptionId}")
	public ResponseEntity removeSubscribedKeywords(@PathVariable long subscriptionId) {
		subscriptionService.dropSubscription(subscriptionId);

		return ResponseEntity
				.status(HttpStatus.OK)
				.build();
	}
}
