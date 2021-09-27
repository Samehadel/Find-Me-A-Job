package com.company.app.ws.integration;

import com.company.app.io.entities.UserEntity;
import com.company.app.security.SecurityConstants;
import com.company.app.ui.controller.UsersController;
import com.company.app.ui.models.request.SignupRequestModel;
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
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UsersController usersController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void create_user_test_happy_path() throws Exception {
        SignupRequestModel signupRequestModel = new SignupRequestModel("Sameh", "Adel", "email", "pass");

        ResponseEntity<UserResponseModel> responseEntity = testRestTemplate.postForEntity("/users/signup", signupRequestModel, UserResponseModel.class);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getHeaders().get(SecurityConstants.HEADER_STRING));
        Assert.assertNotNull(responseEntity.getHeaders().get("virtualUserId"));

        Assert.assertEquals(responseEntity.getBody().getFirstName(), signupRequestModel.getFirstName());
        Assert.assertEquals(responseEntity.getBody().getLastName(), signupRequestModel.getLastName());
        Assert.assertEquals(responseEntity.getBody().getUserName(), signupRequestModel.getUserName());
    }

    @Test
    public void create_user_test_unhappy_path() throws Exception {
        String username = "username";

        signupUser(username); // To make the user already exist

        SignupRequestModel signupRequestModel = new SignupRequestModel("fName", "lName", username, "pass");

        ResponseEntity<UserResponseModel> responseEntity = testRestTemplate.postForEntity("/users/signup", signupRequestModel, UserResponseModel.class);

        Assert.assertNotEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    private void signupUser(String username){
        SignupRequestModel signupRequestModel = new SignupRequestModel("fName", "lName", username, "pass");

        ResponseEntity<UserResponseModel> responseEntity = testRestTemplate.postForEntity("/users/signup", signupRequestModel, UserResponseModel.class);
    }
}
