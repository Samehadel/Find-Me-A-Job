package com.company.app.service;

import java.util.List;

import com.company.app.shared.dto.PublicationDto;
import com.company.app.ui.models.response.PublicationResponseModel;

public interface IPublicationsService {

	public List<PublicationResponseModel> retrievePublications(long userId);
	public void publish(PublicationDto publicationDto);
	
}
