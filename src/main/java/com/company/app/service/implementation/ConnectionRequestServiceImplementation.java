package com.company.app.service.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.io.entities.ConnectionEntity;
import com.company.app.io.entities.RequestEntity;
import com.company.app.io.entities.UserEntity;
import com.company.app.repositery.ConnectionRepository;
import com.company.app.repositery.RequestRepository;
import com.company.app.service.IConnectionRequestService;
import com.company.app.service.IUserService;
import com.company.app.shared.dto.RequestDto;

@Service
public class ConnectionRequestServiceImplementation implements IConnectionRequestService {

	@Autowired
	IUserService userService; 
	
	@Autowired
	RequestRepository requestRepo; 
	
	@Autowired
	ConnectionRepository connectionRepo;
	
	@Override
	public RequestDto createConnectionRequest(RequestDto requestDto) throws Exception {
		
		RequestEntity newRequest = new RequestEntity();
		
		//Retrieve the two users from database
		UserEntity sender = userService.retrieveUser(requestDto.getSenderId());
		UserEntity receiver = userService.retrieveUser(requestDto.getRecieverId());
		
		//Assign relationships
		sender.addSentRequest(newRequest);
		receiver.addRecievedRequest(newRequest);
		
		newRequest.setSender(sender);
		newRequest.setReciever(receiver);
		
		//Save the request
		RequestEntity backRequest = requestRepo.save(newRequest);
		
		//Copy properties back
		BeanUtils.copyProperties(backRequest, requestDto);
		
		return requestDto;
	}

	@Override
	public boolean acceptConnectionRequest(long id) {
		
		try {
		//Prepare connection object
		ConnectionEntity connection = new ConnectionEntity();
		
		//Get the request
		RequestEntity request = requestRepo.findById(id);
		
		//Get the associated users
		UserEntity sender = userService.retrieveUser(request.getSender().getId());
		UserEntity reciever = userService.retrieveUser(request.getReciever().getId());
		
		connection.setFirstUser(sender);
		connection.setSecondUser(reciever);
		
		// Delete the request from the request table
		requestRepo.delete(request);
		
		// Save the connection in connections table
		connectionRepo.save(connection);
		
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public void rejectConnectionRequest(long id) {
		requestRepo.deleteById(id);
	}

}
