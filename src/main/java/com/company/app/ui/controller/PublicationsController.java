package com.company.app.ui.controller;

import com.company.app.service.IPublicationsService;
import com.company.app.shared.dto.PublicationDto;
import com.company.app.ui.models.request.PublicationRequestModel;
import com.company.app.ui.models.response.PublicationResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publications")
public class PublicationsController {

	@Autowired
	IPublicationsService publicationService; 
	
	
	@PostMapping("/publish/{userId}")
	public ResponseEntity publishJobPost(@RequestBody PublicationRequestModel publication, @PathVariable long userId){
		PublicationDto dto = new PublicationDto();
		
		BeanUtils.copyProperties(publication, dto);
		
		publicationService.publish(userId, dto);

		return ResponseEntity
				.status(HttpStatus.OK)
				.build();
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity accessInboxPublications(@PathVariable long userId){
		//Use of service
		List<PublicationResponseModel> publications = publicationService.retrievePublications(userId);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(publications);
	}
	
}
