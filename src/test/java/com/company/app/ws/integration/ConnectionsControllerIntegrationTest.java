package com.company.app.ws.integration;

import com.company.app.io.entities.RequestEntity;
import com.company.app.io.entities.UserEntity;
import com.company.app.repositery.RequestRepository;
import com.company.app.repositery.UserRepository;
import com.company.app.ui.models.request.ConnectionRequestModel;
import com.company.app.ui.models.request.SignupRequestModel;
import com.company.app.ui.models.response.ConnectionResponseModel;
import com.company.app.ui.models.response.UserResponseModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConnectionsControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void send_connection_request_happy_path() {
        String[] usernames = {"username1", "username2", "username3", "username4"};
        signupUsers(usernames);

        List<UserEntity> userEntities = findUsers(usernames);

        ConnectionRequestModel connectionModel = new ConnectionRequestModel(userEntities.get(0).getId(), userEntities.get(1).getId());
        ResponseEntity<ConnectionResponseModel> response = testRestTemplate.postForEntity("/connections/request", connectionModel, ConnectionResponseModel.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

        connectionModel = new ConnectionRequestModel(userEntities.get(0).getId(), userEntities.get(2).getId());
        response = testRestTemplate.postForEntity("/connections/request", connectionModel, ConnectionResponseModel.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

        connectionModel = new ConnectionRequestModel(userEntities.get(0).getId(), userEntities.get(3).getId());
        response = testRestTemplate.postForEntity("/connections/request", connectionModel, ConnectionResponseModel.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

        connectionModel = new ConnectionRequestModel(userEntities.get(1).getId(), userEntities.get(3).getId());
        response = testRestTemplate.postForEntity("/connections/request", connectionModel, ConnectionResponseModel.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

        UserEntity user = userRepository.findById(userEntities.get(0).getId());
        Assert.assertEquals(user.getSentRequests(), 3);

        user = userRepository.findById(userEntities.get(3).getId());
        Assert.assertEquals(user.getReceivedRequests(), 2);

        List<RequestEntity> allRequests = (List)requestRepository.findAll();
        Assert.assertEquals(allRequests.size(), 4);
    }

    private void signupUsers(String[] usernames) {
        SignupRequestModel signupRequestModel;
        for (String username : usernames) {
            signupRequestModel = new SignupRequestModel("fName", "lName",
                    username, "pass");
            testRestTemplate.postForEntity("/users/signup", signupRequestModel, UserResponseModel.class);
        }
    }
    private List<UserEntity> findUsers(String [] usernames){
        List<UserEntity> users = new ArrayList<>();

        for (String username: usernames) {
            UserEntity entity = userRepository.findByUserName(username);
            users.add(entity);
        }

        return users;
    }
}
