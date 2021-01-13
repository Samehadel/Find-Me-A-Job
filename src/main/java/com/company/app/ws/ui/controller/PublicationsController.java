package com.company.app.ws.ui.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.ws.ui.models.request.PublicationRequestModel;
import com.company.app.ws.ui.models.response.PublicationResponseModel;

@RestController
@RequestMapping("/publications")
public class PublicationsController {

	@GetMapping("/{userId}")
	public List<PublicationResponseModel> accessInboxPublications(@PathVariable long userId){
		return null;
	}
	
	@PostMapping("/publish/{userId}")
	public void publishJobPost(@RequestBody PublicationRequestModel publication, @PathVariable long userId){
		
	}
}
