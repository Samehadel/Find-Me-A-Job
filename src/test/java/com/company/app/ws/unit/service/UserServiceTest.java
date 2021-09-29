package com.company.app.ws.unit.service;

import com.company.app.io.entities.UserEntity;
import com.company.app.io.entities.UserRole;
import com.company.app.repositery.UserAuthorityRepository;
import com.company.app.repositery.UserDetailsRepository;
import com.company.app.repositery.UserRepository;
import com.company.app.service.IUserService;
import com.company.app.service.implementation.UserServiceImplementation;
import com.company.app.shared.Utils;
import com.company.app.shared.dto.UserDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    UserRepository userRepo;

    @Mock
    UserAuthorityRepository authRepo;

    @Mock
    UserDetailsRepository detailsRepo;

    @Mock
    BCryptPasswordEncoder encoder;

    @Mock
    Utils utils;

    @InjectMocks
    private IUserService userService;

    @Before
    public void init() {
        this.userService = new UserServiceImplementation();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUser_happy_path_test() throws Exception {
        UserDto userDto = createDto();

        // Mocking Stage 1
        when(userRepo.findByUserName(anyString())).thenReturn(null);
        when(userRepo.save(any())).thenReturn(createUserEntity(userDto));
        when(encoder.encode(anyString())).thenReturn("encryptedPassword");
        when(utils.generateUserId(anyInt())).thenReturn("0123456789");

        UserDto dto = userService.createUser(userDto);

        // Assertion Stage 1
        Assert.assertEquals("encryptedPassword", dto.getEncryptedPassword());
        Assert.assertEquals(10, dto.getVirtualUserId().length());
        Assert.assertEquals(dto.getAuthority().getRole(), UserRole.ROLE_USER.name());
    }

    @Test
    public void createUser_unhappy_path_test() throws Exception {
        UserDto userDto = createDto();

        // Mocking Stage
        when(userRepo.findByUserName(anyString())).thenReturn(new UserEntity()); // Mocking an exist username

        // Assertion Stage
        try {
            userService.createUser(userDto);
            Assert.assertEquals(0, 1);
        } catch (Exception ex) {
            Assert.assertEquals(0, 0);
        }
    }

    @Test
    public void retrieveUser_happy_path_test() throws Exception {
        UserDto userDto = createDto();

        // Mocking Stage
        when(userRepo.findByUserName(anyString())).thenReturn(createUserEntity(userDto));
        when(userRepo.findById(anyLong())).thenReturn(createUserEntity(userDto));

        UserDto backUserDto = userService.retrieveUser("username");
        UserEntity backUserEntity = userService.retrieveUser(userDto.getId());

        // Assertion Stage
        Assert.assertNotNull(backUserDto);
        Assert.assertNotNull(backUserEntity);
    }

    @Test
    public void retrieveUser_unhappy_path_test() throws Exception {
        // Mocking Stage
        when(userRepo.findByUserName(anyString())).thenReturn(null);
        when(userRepo.findById(anyLong())).thenReturn(null);

        // Assertion Stage
        try {
            UserDto backUserDto = userService.retrieveUser("username");
            Assert.assertEquals(0, 1);
        } catch (UsernameNotFoundException ex) {
            Assert.assertEquals(0, 0);
        }

        try {
            UserEntity backUserEntity = userService.retrieveUser(1l);
            Assert.assertEquals(0, 1);
        }catch (Exception ex){
            Assert.assertEquals(0, 0);
        }
    }

    private UserDto createDto() {
        UserDto dto = new UserDto();

        dto.setId(1l);
        dto.setFirstName("fName");
        dto.setLastName("lName");
        dto.setUserName("username");
        dto.setPassword("password");

        return dto;
    }

    private UserEntity createUserEntity(UserDto dto) {
        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(dto, userEntity);

        return userEntity;
    }
}
