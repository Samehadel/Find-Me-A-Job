package com.company.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.company.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	public UserDto createUser(UserDto userDto) throws Exception;
	
	public UserDto retrieveUser(String userName);
	
}
