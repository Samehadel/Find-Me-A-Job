package com.company.app.ws.ui.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.ws.security.SecurityConstants;
import com.company.app.ws.service.UserService;
import com.company.app.ws.shared.dto.UserDto;
import com.company.app.ws.ui.models.request.SignupRequestModel;
import com.company.app.ws.ui.models.response.UserResponseModel;
import com.company.app.ws.utils.JwtUtils;


@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	
	
	@PostMapping("/signup")
	public UserResponseModel createUser(@RequestBody SignupRequestModel userDetails, HttpServletResponse res) throws Exception {
		
		// Initialize required objects
		UserResponseModel returnObj = new UserResponseModel();
		UserDto userDto = new UserDto();

		// Copy values From request body to the dto object
		BeanUtils.copyProperties(userDetails, userDto);

		// Use the user service
		UserDto createdUserEntity = userService.createUser(userDto);

		BeanUtils.copyProperties(createdUserEntity, returnObj);
		
		// Add JWT and virtualUserId
		 res.addHeader(SecurityConstants.HEADER_STRING,
				 		SecurityConstants.TOKEN_PREFIX + 
				 			jwtUtils.getJWT(userDetails.getUserName(), userDetails.getPassword()));
		 
	     res.addHeader("virtualUserId", createdUserEntity.getVirtualUserId());
	        
		return returnObj;
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
