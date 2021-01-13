package com.company.app.ws.ui.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.ws.ui.model.request.ConnectionRequestModel;

@RestController
@RequestMapping("/connections")
public class ConnectionController {

	@PostMapping("/request")
	public ConnectionRequestModel sendConnectionRequest(@RequestBody ConnectionRequestModel connectionRequest) {
		return connectionRequest;
	}
	
	@PutMapping("/requests/accept/{requestId}")
	public String acceptConnectionRequest(@PathVariable long requestId) {
		return "Request Number " + requestId + " Accepted";
	}
	
	@PutMapping("/requests/reject/{requestId}")
	public String rejectConnectionRequest(@PathVariable long requestId) {
		return "Request Number " + requestId + " Rejected";
	}
}
