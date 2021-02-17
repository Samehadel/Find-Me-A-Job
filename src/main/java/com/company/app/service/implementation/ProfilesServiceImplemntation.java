package com.company.app.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.io.entities.UserEntity;
import com.company.app.service.IProfilesService;
import com.company.app.service.ISubscriptionService;
import com.company.app.service.IUserService;
import com.company.app.ui.models.response.ProfileResponseModel;
import com.company.app.utils.ProfilesServiceUtils;

@Service
public class ProfilesServiceImplemntation implements IProfilesService{

	@Autowired
	ISubscriptionService subscriptionsService; 
	
	@Autowired 
	IUserService userService; 
	
	@Autowired 
	ProfilesServiceUtils profilesServiceUtils;
	
	@Override
	public List<ProfileResponseModel> retrieveSimilarUsers(long id) {
		
		//Final users for return
		List<ProfileResponseModel> userModels = new ArrayList<>();
		
		//Retrieve user's subscriptions
		UserEntity user = userService.retrieveUser(id);
		
		Set<UserEntity> similarUsers = profilesServiceUtils.retrieveUsersBySubscriptions(user.getSubscriptions(), user.getId());
		
		for(UserEntity u: similarUsers) {
			ProfileResponseModel model = new ProfileResponseModel();
			
			//Copy related values to Dto
			BeanUtils.copyProperties(u, model);
			
			userModels.add(model);
		}
		return userModels;
	}

}
