package com.company.app.service.util.publication_service_utils;

import com.company.app.io.entities.ConnectionEntity;
import com.company.app.io.entities.PublicationEntity;
import com.company.app.io.entities.SubscriptionEntity;
import com.company.app.io.entities.UserEntity;
import com.company.app.repositery.ConnectionRepository;
import com.company.app.repositery.PublicationsRepository;
import com.company.app.shared.dto.PublicationDto;
import com.company.app.ui.models.response.PublicationResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishPostsUtilImplementation extends PublishPostsUtil {

    @Autowired
    private PublicationsRepository publicationRepo;

    @Autowired
    private ConnectionRepository connectionRepo;

    private UserEntity sender = new UserEntity();
    private List<ConnectionEntity> userConnections;
    private List<Integer> keywords;


    @Override
    protected void prepareEntities(PublicationDto publicationDto) {
        long userId = publicationDto.getSenderId();

        // Extract user's connections
        userConnections = connectionRepo.findByUserId(userId);
        keywords = publicationDto.getKeywords();

        // Assign sender
        if (!userConnections.isEmpty()) {
            ConnectionEntity tempConn = userConnections.get(0);

            if (tempConn.getFirstUser().getId() == userId)
                sender = tempConn.getFirstUser();
            else
                sender = tempConn.getSecondUser();

        }
    }

    @Override
    protected List<PublicationEntity> buildPublications(PublicationDto publicationDto) {
        List<PublicationEntity> publications = new ArrayList<>();

        // Loop over connections
        for (ConnectionEntity conn : userConnections) {
            boolean hasCommon = false;

            // Look for the receiver whether is first or second in each connection
            UserEntity receiver = conn.getFirstUser();
            if (receiver.getId() == publicationDto.getSenderId())
                receiver = conn.getSecondUser();


            /* Check user's subscriptions */

            // Retrieve receiver's subscriptions
            List<SubscriptionEntity> receiverSubscriptions = receiver.getSubscriptions();

            // Loop over the receiver's subscriptions
            for (SubscriptionEntity sub : receiverSubscriptions) {

                // Check if subscription's keyword match any of the given keywords
                for (int id : keywords) {
                    if (sub.getKeyword().getId() == id) { // This receiver has subscribed for this keyword before

                        // Prepare publication
                        PublicationEntity publication = new PublicationEntity();
                        publication.setReciever(receiver);
                        publication.setSender(sender);

                        // Assign Relationships
                        receiver.addRecievedPublication(publication);
                        receiver.addSentPublication(publication);

                        BeanUtils.copyProperties(publicationDto, publication);

                        // Use Repository to save one publication
                        publications.add(publication);

                        // To escape from assigned user
                        hasCommon = true;
                        break;
                    }
                }

                // Return to main loop
                if (hasCommon)
                    break;

            }
        }
        return publications;
    }

    @Override
    protected void savePublications(List<PublicationEntity> publications) {
        for(PublicationEntity publication: publications)
            publicationRepo.save(publication);

    }
}
