package com.ipayso.services.security;

public interface SecurityService {
	
	String findLoggedInUsername();

	boolean autologin(String username, String password);
   
}
