package com.company.app.ws.unit.controllers;

import com.company.app.service.IPublicationsService;
import com.company.app.ui.controller.PublicationsController;
import com.company.app.ui.models.request.PublicationRequestModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PublicationsControllerTest {

    @Mock
    private IPublicationsService publicationsService;

    @InjectMocks
    private PublicationsController publicationsController;

    @Before
    public void init(){
        this.publicationsController = new PublicationsController();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void publishJobPost_happy_path_test(){

        ResponseEntity responseEntity = publicationsController.publishJobPost(new PublicationRequestModel(), 1l);

        // Assertion Stage
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void accessInboxPublications_happy_path_test(){

        ResponseEntity responseEntity = publicationsController.accessInboxPublications(1l);

        // Assertion Stage
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
