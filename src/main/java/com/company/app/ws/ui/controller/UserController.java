package com.company.app.ws.ui.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.ws.security.SecurityConstants;
import com.company.app.ws.service.UserService;
import com.company.app.ws.shared.dto.UserDto;
import com.company.app.ws.ui.model.request.LoginRequest;
import com.company.app.ws.ui.model.request.SignupRequest;
import com.company.app.ws.ui.model.response.UserRest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	public UserRest createUser(@RequestBody SignupRequest userDetails) {
		UserRest returnObj = new UserRest();

		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createdUserEntity = userService.createUser(userDto);

		BeanUtils.copyProperties(createdUserEntity, returnObj);

		return returnObj;
	}

	@PostMapping("/signin")
	public UserRest authenticateUser(@RequestBody LoginRequest userBody, HttpServletResponse res) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userBody.getUserName(), userBody.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String userName = ((User) authentication.getPrincipal()).getUsername();

		String token = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();

		UserDto userDto = userService.getUser(userName);

		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("UserID", String.valueOf(userDto.getId()));

		return new UserRest((int) userDto.getId(), SecurityConstants.TOKEN_PREFIX + token);
	}
	
	@GetMapping("/test")
	public boolean isAuthenticated() {
		return false;
	}
}
