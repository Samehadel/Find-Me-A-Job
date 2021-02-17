package com.company.app.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.company.app.SpringApplicationContext;
import com.company.app.service.IUserService;
import com.company.app.shared.dto.UserDto;
import com.company.app.ui.models.request.LoginRequestModel;
import com.company.app.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	private AuthenticationManager authManager;

	public AuthenticationFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		try {
			LoginRequestModel creds = new ObjectMapper()
											.readValue(request.getInputStream(), LoginRequestModel.class);
			
			
			return authManager.authenticate(
                    		new UsernamePasswordAuthenticationToken(
                            creds.getUserName(),
                            creds.getPassword(),
                            new ArrayList<>()));
                    
		}catch(BadCredentialsException ex) {
			
			// Respond status Unauthorized
			response.setStatus(401);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			// Internal server error
			response.setStatus(500);
		}
		
		return null;
	}
	
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
    	JwtUtils jwtUtils = new JwtUtils();
 
        String userName = ((User) auth.getPrincipal()).getUsername();  
        
        String token = jwtUtils.getJWT(userName);
        
        IUserService userService = (IUserService)SpringApplicationContext.getBean("userServiceImplementation");
        UserDto userDto = userService.retrieveUser(userName);
        
     
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        res.addHeader("virtualUserId", userDto.getVirtualUserId());

    }  
    
}
