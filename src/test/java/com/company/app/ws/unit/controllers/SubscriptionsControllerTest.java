package com.company.app.ws.unit.controllers;

import com.company.app.service.ISubscriptionService;
import com.company.app.shared.dto.SubscriptionDto;
import com.company.app.ui.controller.SubscriptionsController;
import com.company.app.ui.models.request.SubscriptionRequestModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class SubscriptionsControllerTest {

    @Mock
    private ISubscriptionService subscriptionService;

    @InjectMocks
    private SubscriptionsController subscriptionsController;

    @Before
    public void init(){
        this.subscriptionsController = new SubscriptionsController();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void subscribe_happy_path_test() throws Exception {
        SubscriptionRequestModel subscriptionRequestModel = new SubscriptionRequestModel(1l, 1);

        // Mocking Stage 1
        when(subscriptionService.createSubscription(anyLong(), anyInt())).thenReturn(true);

        ResponseEntity responseEntity = subscriptionsController.subscribe(subscriptionRequestModel);

        // Assertion Stage 1
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);


        // Mocking Stage 2
        when(subscriptionService.createSubscription(anyLong(), anyInt())).thenReturn(false);

        responseEntity = subscriptionsController.subscribe(subscriptionRequestModel);

        // Assertion Stage 2
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void accessSubscriptions_happy_path(){

        // Mocking Stage
        when(subscriptionService.getSubscriptions(anyLong())).thenReturn(new ArrayList<SubscriptionDto>());

        ResponseEntity responseEntity = subscriptionsController.accessSubscriptions(1l);

        // Assertion Stage
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getBody());
    }

    @Test
    public void removeSubscribedKeywords_happy_path(){

        ResponseEntity responseEntity = subscriptionsController.accessSubscriptions(1l);

        // Assertion Stage
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
