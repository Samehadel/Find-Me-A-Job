package com.company.app.ui.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.service.IPublicationsService;
import com.company.app.shared.dto.PublicationDto;
import com.company.app.ui.models.request.PublicationRequestModel;
import com.company.app.ui.models.response.PublicationResponseModel;

@RestController
@RequestMapping("/publications")
public class PublicationsController {

	@Autowired
	IPublicationsService publicationService; 
	
	
	@PostMapping("/publish/{userId}")
	public void publishJobPost(@RequestBody PublicationRequestModel publication, @PathVariable long userId){
		PublicationDto dto = new PublicationDto();
		
		BeanUtils.copyProperties(publication, dto);
		
		publicationService.publish(userId, dto);
	}
	
	@GetMapping("/{userId}")
	public List<PublicationResponseModel> accessInboxPublications(@PathVariable long userId){
		//Use of service
		List<PublicationResponseModel> publications = publicationService.retrievePublications(userId);
		
		return publications;
	}
	
}
