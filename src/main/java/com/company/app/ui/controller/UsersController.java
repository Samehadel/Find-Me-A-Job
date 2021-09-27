package com.company.app.ui.controller;

import com.company.app.security.SecurityConstants;
import com.company.app.service.IUserService;
import com.company.app.shared.dto.UserDto;
import com.company.app.ui.models.request.SignupRequestModel;
import com.company.app.ui.models.response.UserResponseModel;
import com.company.app.utils.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	IUserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	
	
	@PostMapping("/signup")
	public ResponseEntity<UserResponseModel> createUser(@RequestBody SignupRequestModel userDetails) throws Exception {
		
		// Initialize required objects
		UserResponseModel returnObj = new UserResponseModel();
		UserDto userDto = new UserDto();

		// Copy values From request body to the dto object
		BeanUtils.copyProperties(userDetails, userDto);

		// Use the user service
		UserDto createdUserEntity = userService.createUser(userDto);

		BeanUtils.copyProperties(createdUserEntity, returnObj);

		// Add JWT and virtualUserId then return the response entity
		return ResponseEntity.ok()
				.header(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX +
						jwtUtils.getJWT(userDetails.getUserName(), userDetails.getPassword()))
				.header("virtualUserId", createdUserEntity.getVirtualUserId())
				.build();
	}
	
	@PutMapping("/pin/{userId}")
	public String pinUserAccess(@PathVariable long userId) {
		return userId + " Pined";
	}
	
	@GetMapping("/test")
	public boolean isAuthenticated() {
		return true;
	}
}
