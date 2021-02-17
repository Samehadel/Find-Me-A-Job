package com.company.app.service.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	
	@Override
	public List<PublicationResponseModel> retrievePublications(long userId) {

		List<PublicationResponseModel> publications = new ArrayList<>();
		
		//Repository use
		List<PublicationEntity> publicationEntites = publicationRepo.findByRecieverId(userId);
		
		for(PublicationEntity entity: publicationEntites) {
			PublicationResponseModel model = new PublicationResponseModel();
			
			//Copy relevant attributes
			BeanUtils.copyProperties(entity, model);
			
			//Append publications
			publications.add(model);
		}
		
		return publications;
	}

	@Override
	public boolean publish(long userId, PublicationDto publicationDto) {
		
		//Retrieve user's connections
		List<ConnectionEntity> userConnections = connectionRepo.findByUserId(userId);
		List<Integer> keywords = publicationDto.getKeywords();
		UserEntity sender = new UserEntity();
		
		//Assign sender 
		if(!userConnections.isEmpty()) {
			ConnectionEntity tempConn = userConnections.get(0);
			
			if(tempConn.getFirstUser().getId() == userId)
				sender = tempConn.getFirstUser();
			else
				sender = tempConn.getSecondUser();
			
		}
		 
		//Loop over connections
		for(ConnectionEntity conn: userConnections) {
			boolean hasCommon = false;
			UserEntity reciever = conn.getFirstUser();
	
			if(reciever.getId() == userId) 
				reciever = conn.getSecondUser();
			
			
			/*Check user's subscriptions*/
			
			//Retrieve user's subscriptions
			List<SubscriptionEntity> recieverSubscriptions = reciever.getSubscriptions();
			
			//Loop over subscriptions
			for(SubscriptionEntity sub: recieverSubscriptions) {
				
				//Check if sub match any of keywords
				for(int id: keywords) {
					if(sub.getKeyword().getId() == id) {
						//Prepare publication
						PublicationEntity publication = new PublicationEntity();
						publication.setReciever(reciever);
						publication.setSender(sender);
						
						//Assign Relationships
						reciever.addRecievedPublication(publication);
						reciever.addSentPublication(publication);
						
						BeanUtils.copyProperties(publicationDto, publication);
						
						//Use Repository to save one publication
						publicationRepo.save(publication);
						
						//To escape from assigned user
						hasCommon = true;
						break;
					}
				}
				
				//Return to main loop
				if(hasCommon) 
					break;
				
			}
		}

		return false;
	}

}
