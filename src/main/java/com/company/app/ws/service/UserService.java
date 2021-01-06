package com.company.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.company.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	public UserDto createUser(UserDto userDto);
	
	public UserDto getUser(String userName);
	
}
