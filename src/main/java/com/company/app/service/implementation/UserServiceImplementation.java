package com.company.app.service.implementation;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.app.io.entities.UserAuthorityEntity;
import com.company.app.io.entities.UserDetailsEntity;
import com.company.app.io.entities.UserEntity;
import com.company.app.io.entities.UserRole;
import com.company.app.repositery.UserAuthorityRepository;
import com.company.app.repositery.UserDetailsRepository;
import com.company.app.repositery.UserRepository;
import com.company.app.service.IUserService;
import com.company.app.shared.Utils;
import com.company.app.shared.dto.UserDto;
import com.company.app.ui.models.request.ErrorMessages;

@Service
public class UserServiceImplementation implements IUserService {

	@Autowired
	UserRepository userRepo; 
	
	@Autowired
	UserAuthorityRepository authRepo;
	
	@Autowired
	UserDetailsRepository detailsRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder; 
	
	@Autowired
	Utils utils;
	
	@Override
	public UserDto createUser(UserDto userDto) throws Exception{

		// Check If Email Already Exist
		if(userRepo.findByUserName(userDto.getUserName()) != null) 
			throw new Exception(ErrorMessages.USER_ALREADY_EXIST.getErrorMessage());
		
		// Prepare Required objects
		UserDto returnDto = new UserDto();
		
		UserEntity user = new UserEntity();
		UserAuthorityEntity authorities = new UserAuthorityEntity(UserRole.ROLE_USER.name());
		UserDetailsEntity userDetails = new UserDetailsEntity();
		
		// Copy Values From DTO To User Entity
		BeanUtils.copyProperties(userDto, user);
		
		// Relationship Exchange
		user.setAuthority(authorities);
		authorities.setUser(user);
		
		user.setUserDetails(userDetails);
		userDetails.setUser(user);
		

		// Encrypt User Password - Then -> Generate Random User Id
		user.setEncryptedPassword(encoder.encode(userDto.getPassword()));
		user.setVirtualUserId(utils.generateUserId(10));
		
		// Use Repository To Save The User And Its Role 
		userRepo.save(user);
		authRepo.save(authorities);
		detailsRepo.save(userDetails);
		
		// Copy Values From Result Entity to The Return Object
		BeanUtils.copyProperties(user, returnDto);
		
		return returnDto;
	}

	@Override
	public UserDto retrieveUser(String userName) {
		UserEntity userEntity = userRepo.findByUserName(userName);
		UserDto returnValue = new UserDto();

		if (userEntity == null)
			throw new UsernameNotFoundException(userName);

		BeanUtils.copyProperties(userEntity, returnValue);
 
		return returnValue;
	}
	
	
	@Override
	public UserEntity retrieveUser(long id) throws Exception {
		UserEntity userEntity = userRepo.findById(id);

		if (userEntity == null)
			throw new Exception(ErrorMessages.USER_NOT_FOUND.getErrorMessage());

		return userEntity;
	}

	
	
	@Override
	public UserDto updateUser(UserEntity user) {

		UserDto userDto = new UserDto();
		UserEntity backUser = userRepo.save(user);
		
		BeanUtils.copyProperties(backUser, userDto);
		
		return userDto;
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
