package com.company.app.ui.controller;

import com.company.app.service.IProfilesService;
import com.company.app.ui.models.request.ProfileDetailsRequestModel;
import com.company.app.ui.models.response.ProfileResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {

	@Autowired
	IProfilesService profilesService; 
	

	/**

			* This endpoint is used to show similar profiles for a specific user.
			* @param userId is the id of the target user to find similar profiles.
			* @return A list of similar profiles returned using a response model called ProfileResponseModel
	 */

	@GetMapping("by/{userId}")
	public ResponseEntity displayProfiles(@PathVariable long userId) throws Exception {

		// Use of the service
		List<ProfileResponseModel> users = profilesService.retrieveSimilarUsers(userId);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(users);
	}
	
	@PutMapping("/details/{userId}")
	public void editAccountInfo(@RequestBody ProfileDetailsRequestModel profileDetails,
								@PathVariable long userId){
		
	}
}
