package com.company.app.ws.unit;

import com.company.app.security.SecurityConstants;
import com.company.app.service.IUserService;
import com.company.app.shared.dto.UserDto;
import com.company.app.ui.controller.UsersController;
import com.company.app.ui.models.request.SignupRequestModel;
import com.company.app.utils.JwtUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UsersControllerTest {
    @Mock
    private IUserService userService;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UsersController usersController;

    @Before
    public void init(){
        this.usersController = new UsersController();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_user_test_happy_path() throws Exception {
        SignupRequestModel signupRequestModel = new SignupRequestModel("Sameh", "Adel",
                "email", "pass");

        when(userService.createUser(any())).thenReturn(new UserDto());
        ResponseEntity responseEntity = usersController.createUser(signupRequestModel);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(responseEntity.getHeaders().get(SecurityConstants.HEADER_STRING));
        Assert.assertNotNull(responseEntity.getHeaders().get("virtualUserId"));


    }
}

//Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYWlsMThAb3V0bG9vay5jb20iLCJleHAiOjE2MzI2NTg2NjZ9.8JLcSCGLpwSCgrxMFzVfNbPI-l5qx_NEs-hzNZytbsDre-3dWywFfesiFufW3zi5iC1o6lK5C_s6871RUUYgdQ
