package com.ipayso.services.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * SecurityService.class -> This interface assign all the methods an SecurityService should implement
 * @author Cleber Oliveira
 * @version 1.0
 */
public interface SecurityService {
	
    /**
	 * This method implements findLoggedInUsername to get the UserDetails which in currently logged into the system
	 * @see SecurityContextHolder
	 * @see UserDetails
	 */
	String findLoggedInUsername();

    /**
	 * This method implements auto login to log an User using the e-mail and password, an UserDetailsService load the UserDetails them an
	 * authentication Manager will authenticate the UserDetails as a Token, if logged successfully the method will return true otherwise
	 * will be false.
	 * @see UserDetailsService
	 * @see UsernamePasswordAuthenticationToken
	 * @see AuthenticationManager
	 */
	boolean autologin(String username, String password);
   
}
