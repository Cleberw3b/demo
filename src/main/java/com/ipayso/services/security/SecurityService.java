package com.ipayso.services.security;

/**
 * SecurityService.class -> This interface assign all the methods an SecurityService should implement
 * @author Cleber Oliveira
 * @version 1.0
 */
public interface SecurityService {
	
	String findLoggedInUsername();

	boolean autologin(String username, String password);
   
}
