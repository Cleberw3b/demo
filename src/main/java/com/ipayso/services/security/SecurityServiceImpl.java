package com.ipayso.services.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * SecurityServiceImpl.class -> This Service offers a SecurityService implementation to make some security features.
 * 								This class provides ways to authenticate users and get the user logged on the system. 
 * @author Cleber Oliveira
 * @version 1.0
 * @see SecurityService
 */
@Service
public class SecurityServiceImpl implements SecurityService{
	
    private AuthenticationManager authenticationManager;
    
    /**
	 * Injects AuthenticationManager to provide a manager to organize the authentication
	 * @see AuthenticationManager
	 */
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager){
    	this.authenticationManager = authenticationManager;
    }
    
    private UserDetailsService userDetailsService;
    
    /**
	 * Injects UserDetailsService in order to use its methods
	 * @see UserDetailsService
	 */
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService){
    	this.userDetailsService = userDetailsService;
    }

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    
    /**
	 * This method implements findLoggedInUsername to get the UserDetails which in currently logged into the system
	 * @see SecurityContextHolder
	 * @see UserDetails
	 */
    @Override
    public String findLoggedInUsername() {
    	SecurityContext context = SecurityContextHolder.getContext();
    	Authentication authentication = context.getAuthentication();
    	Object userDetails = authentication.getPrincipal();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
        return null;
    }
    
    /**
	 * This method implements autologin to log an User using the e-mail and password, an UserDetailsService load the UserDetails them an
	 * authentication Manager will authenticate the UserDetails as a Token, if logged successfully the method will return true otherwise
	 * will be false.
	 * @see UserDetailsService
	 * @see UsernamePasswordAuthenticationToken
	 * @see AuthenticationManager
	 */
    @Override
    public boolean autologin(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("%s logged in successfully!", email));
            return true;
        } else {
        	return false;
        }
    }
}