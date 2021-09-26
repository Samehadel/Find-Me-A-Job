package com.company.app.ui.controller;

import com.company.app.service.IConnectionRequestService;
import com.company.app.shared.dto.RequestDto;
import com.company.app.ui.models.request.ConnectionRequestModel;
import com.company.app.ui.models.response.ConnectionResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/connections")
public class ConnectionsController {

	@Autowired
	IConnectionRequestService requestService;

	/**
	 * This endpoint is used to send a connection request from one user to another
	 * @param connectionRequest is the connection request model to hold the sender id and the receiver id
	 * @return a connection response model that holds the request information e.g. id
	 */
	@PostMapping("/request")
	public ConnectionResponseModel sendConnectionRequest(@RequestBody ConnectionRequestModel connectionRequest,
														 HttpServletResponse res) {
		
		// Instantiate the response model
		ConnectionResponseModel responseModel = new ConnectionResponseModel();
		
		// Prepare the DTO for service usage
		RequestDto requestDto = new RequestDto();
		
		// Copy properties from request object to DTO
		BeanUtils.copyProperties(connectionRequest, requestDto);

		// Use the service to create a connection request
		RequestDto serviceBackDto = requestService.createConnectionRequest(requestDto);
		
		// Copy properties back to response model
		BeanUtils.copyProperties(serviceBackDto, responseModel);
		
		if(responseModel.getId() == 0)
			res.setStatus(500);
		
		return responseModel;
	}

	/**
	 * This endpoint is used by a user(receiver) to accept a connection request
	 * @param requestId is the connection request id
	 */
	@PutMapping("/requests/accept/{requestId}")
	public void acceptConnectionRequest(@PathVariable long requestId, HttpServletResponse res) {
		
		boolean check = requestService.acceptConnectionRequest(requestId);
		
		if(!check) res.setStatus(500);
		
	}

	/**
	 * This endpoint is used by a user(receiver) to reject a connection request
	 * @param requestId is the connection request id
	 */
	@DeleteMapping("/requests/reject/{requestId}")
	public void rejectConnectionRequest(@PathVariable long requestId, HttpServletResponse res) {
		requestService.rejectConnectionRequest(requestId);

		res.setStatus(HttpStatus.OK.value());
	}
}
