package com.company.app.ws.service.implementation;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.app.ws.io.entity.UserAuthorityEntity;
import com.company.app.ws.io.entity.UserEntity;
import com.company.app.ws.io.entity.UserRole;
import com.company.app.ws.repositery.UserAuthorityRepository;
import com.company.app.ws.repositery.UserRepository;
import com.company.app.ws.service.UserService;
import com.company.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepo; 
	
	@Autowired
	UserAuthorityRepository authRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder; 
	
	@Override
	public UserDto createUser(UserDto userDto) {

		if(userRepo.findByUserName(userDto.getUserName()) != null) 
			throw new RuntimeException("Email Address Already Exist  " + userDto.getUserName());
		
		UserEntity user = new UserEntity();
		UserAuthorityEntity authorities = new UserAuthorityEntity(UserRole.ROLE_USER.name());
		UserDto returnDto = new UserDto();
		
		BeanUtils.copyProperties(userDto, user);
		
		//Relationship exchange
		user.setAuthorities(authorities);
		authorities.setUser(user);

		user.setEncryptedPassword(encoder.encode(userDto.getPassword()));
		user.setUserId("Test User ID");
		
		UserEntity resultEntity = userRepo.save(user);
		authRepo.save(authorities);
		
		BeanUtils.copyProperties(resultEntity, returnDto);
		returnDto.setAuthority(authorities.getAuthority());
		
		return returnDto;
	}

	@Override
	public UserDto getUser(String userName) {
		UserEntity userEntity = userRepo.findByUserName(userName);

		if (userEntity == null)
			throw new UsernameNotFoundException(userName);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
 
		return returnValue;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) {
		UserEntity user = userRepo.findByUserName(userName);
		
		if(user != null)
			return new User(user.getUserName(), user.getEncryptedPassword(), new ArrayList<>());
		else
			throw new UsernameNotFoundException(userName);
	}
	
	

}
