package com.company.app.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.service.IProfilesService;
import com.company.app.ui.models.request.ProfileDetailsRequestModel;
import com.company.app.ui.models.response.ProfileResponseModel;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {

	@Autowired
	IProfilesService profilesService; 
	
	
	@GetMapping("by/{userId}")
	public List<ProfileResponseModel> displayProfiles(@PathVariable long userId){
		
		//Use of the service
		List<ProfileResponseModel> users = profilesService.retrieveSimilarUsers(userId);
		
		
		return users;
	}
	
	@PutMapping("/details/{userId}")
	public void editAccountInfo(@RequestBody ProfileDetailsRequestModel profileDetails,
								@PathVariable long userId){
		
	}
}
