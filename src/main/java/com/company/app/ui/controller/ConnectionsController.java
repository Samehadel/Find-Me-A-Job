package com.company.app.ui.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.service.IRequestService;
import com.company.app.shared.dto.RequestDto;
import com.company.app.ui.models.request.ConnectionRequestModel;
import com.company.app.ui.models.response.ConnectionResposeModel;

@RestController
@RequestMapping("/connections")
public class ConnectionsController {

	@Autowired
	IRequestService requestService;
	
	
	@PostMapping("/request")
	public ConnectionResposeModel sendConnectionRequest(@RequestBody ConnectionRequestModel connectionRequest, HttpServletResponse res) {
		
		//Instantiate the response model
		ConnectionResposeModel responseModel = new ConnectionResposeModel();
		
		//Prepare DTO for service usage
		RequestDto requestDto = new RequestDto();
		
		//Copy properties from request object to DTO
		BeanUtils.copyProperties(connectionRequest, requestDto);
		
		RequestDto backDto = requestService.createConnectionRequest(requestDto);
		
		//Copy properties back to response model
		BeanUtils.copyProperties(backDto, responseModel);
		
		if(responseModel.getId() == 0) res.setStatus(500);
		
		return responseModel;
		
	}
	
	@PutMapping("/requests/accept/{requestId}")
	public void acceptConnectionRequest(@PathVariable long requestId, HttpServletResponse res) {
		
		boolean check = requestService.acceptConnectionRequest(requestId);
		
		if(!check) res.setStatus(500);
		
	}
	
	@DeleteMapping("/requests/reject/{requestId}")
	public void rejectConnectionRequest(@PathVariable long requestId) {
		requestService.rejectConnectionRequest(requestId);
	}
}
