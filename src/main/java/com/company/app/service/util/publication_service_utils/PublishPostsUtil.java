package com.company.app.service.util.publication_service_utils;

import com.company.app.io.entities.PublicationEntity;
import com.company.app.shared.dto.PublicationDto;

import java.util.List;

/**
 * This Util class uses the template method design pattern to follow some fixed steps
 * to send publications to the interested connections of a specific user (sender)
 */

public abstract class PublishPostsUtil {

    public void publish(PublicationDto publicationDto) {

        // STEP 1: Extract user's connections and prepare the required entities
        prepareEntities(publicationDto);

        /*  STEP 2: Extract the matched connections who already subscribed to the pre-assigned
            keywords by the sender and prepare the publication entities for the next step
            to be saved in the database
        */
        List<PublicationEntity> publications = buildPublications(publicationDto);

        //  STEP 3: Save the built publications to the database
        savePublications(publications);
    }

    protected abstract void prepareEntities(PublicationDto publicationDto);

    protected abstract List<PublicationEntity> buildPublications(PublicationDto publicationDto);

    protected abstract void savePublications(List<PublicationEntity> publications);
}
