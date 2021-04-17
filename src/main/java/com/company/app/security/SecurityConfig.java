package com.company.app.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.company.app.service.IUserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	private IUserService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private static final String[] AUTH_WHITELIST = {

            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/signin",
            SecurityConstants.SIGN_UP_URL
    };
	
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable()
			.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
			.and().authorizeRequests().anyRequest().authenticated()
			.and()
			.addFilter(new AuthenticationFilter(authenticationManager()))
			.addFilter(new AuthorizationFilter(authenticationManager()));

	}

	@Override
	protected void configure(AuthenticationManagerBuilder authManager) throws Exception {
		authManager.userDetailsService(userDetailsService).passwordEncoder(encoder);

	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
   
}
