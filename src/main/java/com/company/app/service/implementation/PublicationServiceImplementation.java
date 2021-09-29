package com.company.app.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.company.app.service.util.publication_service_utils.PublishPostsUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.app.io.entities.ConnectionEntity;
import com.company.app.io.entities.PublicationEntity;
import com.company.app.io.entities.SubscriptionEntity;
import com.company.app.io.entities.UserEntity;
import com.company.app.repositery.ConnectionRepository;
import com.company.app.repositery.PublicationsRepository;
import com.company.app.service.IPublicationsService;
import com.company.app.shared.dto.PublicationDto;
import com.company.app.ui.models.response.PublicationResponseModel;

@Service
public class PublicationServiceImplementation implements IPublicationsService {

	@Autowired 
	private PublicationsRepository publicationRepo; 
	
	@Autowired
	private ConnectionRepository connectionRepo;

	@Autowired
	private PublishPostsUtil publishPostsUtil;

	@Override
	public List<PublicationResponseModel> retrievePublications(long userId) {

		List<PublicationResponseModel> publications = new ArrayList<>();
		
		//Repository use
		List<PublicationEntity> publicationEntities = publicationRepo.findByRecieverId(userId);
		
		for(PublicationEntity entity: publicationEntities) {
			PublicationResponseModel model = new PublicationResponseModel();
			
			//Copy relevant attributes
			BeanUtils.copyProperties(entity, model);
			
			//Append publications
			publications.add(model);
		}
		
		return publications;
	}

	@Override
	public void publish(PublicationDto publicationDto) {

		// Delegate the whole function to the utility class "PublishPostsUtil"
		publishPostsUtil.publish(publicationDto);
	}

}
