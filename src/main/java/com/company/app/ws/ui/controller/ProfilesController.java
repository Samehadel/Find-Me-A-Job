package com.company.app.ws.ui.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.ws.ui.model.request.ProfileDetailsRequestModel;
import com.company.app.ws.ui.model.response.ProfileResponseModel;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {

	@GetMapping("by/{userId}")
	public List<ProfileResponseModel> displayProfiles(@PathVariable long userId){
		
		return null;
	}
	
	@PutMapping("/details/{userId}")
	public void editAccountInfo(@RequestBody ProfileDetailsRequestModel profileDetails,
								@PathVariable long userId){
		
	}
}
