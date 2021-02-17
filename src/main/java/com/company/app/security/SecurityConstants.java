package com.company.app.security;

public class SecurityConstants {
	
	//public static final String PASSWORD_RESET_REQUEST_URL = "/users/password-reset-request";
	//public static final String VERIFICATION_EMAIL_URL = "/users/email-verification";
	//	public static final long PASSWORD_RESET_EXPIRATION_TIME = 3600000; // 1 hour
	//public static final String PASSWORD_RESET_URL = "/users/password-reset";
	//public static final String H2_CONSOLE = "/h2-console/**";
	
	public static final long EXPIRATION_TIME = 1800000; 
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users/signup";
	public static final String TOKEN_SECRET = "dfods2sfr2jji" ;
	

}
